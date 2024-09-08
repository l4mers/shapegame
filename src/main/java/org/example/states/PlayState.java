package org.example.states;

import org.example.GamePanel;
import org.example.UtilityTool;
import org.example.shapes.*;
import org.example.shapes.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.List;


public class PlayState implements UpdateAndDraw {

    private final GamePanel gp;
    private final ShapeConstructor shapeConstructor;
    private final BufferedImage sprite;
    private final GameBoard gameBoard;
    private boolean moving = false;
    private Shape movingShape;
    private final Point originalPoint = new Point();
    private final Set<AnimationTile> shadowTiles = new HashSet<>();
    private Instant startTime;
    private boolean animation = false;
    private final Set<AnimationTile> animationSquares = new HashSet<>();
    public boolean gameOver = false;
    private int animationLaps = 0;

    private int score = 0;

    public PlayState(GamePanel gp) {
        this.gp = gp;
        gameBoard = new GameBoard(gp);
        shapeConstructor = new ShapeConstructor(gp);
        this.sprite = UtilityTool.loadSpriteSheet(gp.tileSize * 5, gp.tileSize * 4, "/tiles.png");
        shapeConstructor.setPoints(calculatePointsBelowGameBoard(shapeConstructor.getPlayableShapes().size()));
        shapeConstructor.setShapesPosition();
        movingShape = new Shape();
    }

    private BufferedImage getImageByColor(ShapeColor randomColorType) {
        return switch (randomColorType) {
            case RED -> UtilityTool.getSubImage(sprite, 4, 1, gp.tileSize, gp.tileSize);
            case YELLOW -> UtilityTool.getSubImage(sprite, 2, 1, gp.tileSize, gp.tileSize);
            case BLUE -> UtilityTool.getSubImage(sprite, 1, 1, gp.tileSize, gp.tileSize);
            default -> UtilityTool.getSubImage(sprite, 3, 1, gp.tileSize, gp.tileSize);
        };
    }

    private List<Shape> generateFittingShapes(int i) {
        List<Shape> shapes = shapeConstructor.loadRandomShapes(i);
        if (checkIfShapesFit(shapes)) {
            return shapes;
        }
        List<Shape> specialShapes = shapeConstructor.loadSpecialShapes(i - 1);
        specialShapes.add(shapes.getFirst());
        return specialShapes;
    }

    private boolean checkIfShapesFit(List<Shape> shapes) {
        for (BoardTile[] boardTiles : gameBoard.gameBoard) {
            for (BoardTile boardTile : boardTiles) {
                for (Shape shape : shapes) {
                    Shape clone = new Shape(shape);
                    clone.setPosition(boardTile.rectangle.x, boardTile.rectangle.y);
                    boolean shapeFits = true;
                    for (Tile tile : clone.tiles) {
                        boolean tileFits = false;
                        for (BoardTile[] boardTilesAgain : gameBoard.gameBoard) {
                            int allTilesChecked = 0;
                            for (BoardTile boardTileAgain : boardTilesAgain) {
                                if (tile.rectangle.intersects(boardTileAgain.rectangle)) {
                                    allTilesChecked++;
                                    if (boardTileAgain.occupied) {
                                        shapeFits = false;
                                        break;
                                    }
                                    tileFits = true;
                                }
                                if(allTilesChecked == shape.tiles.size()){
                                    break;
                                }
                            }
                            if (!shapeFits) break;
                        }
                        if (!shapeFits || !tileFits) {
                            shapeFits = false;
                            break;
                        }
                    }
                    if (shapeFits) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Point[] calculatePointsBelowGameBoard(int numberOfPoints) {
        Point[] points = new Point[numberOfPoints];
        int y = gp.tileSize * (gameBoard.gameBoard.length + 1) + gp.tileSize + gp.tileSize /2;
        int interval = gp.screenWidth / (numberOfPoints + 1) + gp.tileSize;
        for (int i = 0; i < numberOfPoints; i++) {
            int x = interval * (i + 1) - gp.tileSize * 3;
            points[i] = new Point(x, y);
        }
        return points;
    }

    private void checkIfShapeBeenClicked(){
        if(gp.inputListener.pressed && !moving){
            shapeConstructor.getPlayableShapes().forEach(shape ->{
                shape.getTiles().forEach(tile -> {
                    if (tile.rectangle.intersects(gp.inputListener.mouse)){
                        moving = true;
                        movingShape = shape;
                        originalPoint.setLocation(shape.getLeadingX(), shape.getLeadingY());
                    }
                });
            });
        }
    }

    private void updateShapePosition(){
        if (moving) {
            movingShape.setPosition(
                    gp.inputListener.mouse.x - gp.tileSize / 2,
                    gp.inputListener.mouse.y - gp.tileSize / 2
            );
        }
    }

    private void setBestHoveringTiles(){
        shadowTiles.clear();
        if(moving) {
            for (Tile tile: movingShape.tiles) {
                AnimationTile bestShadowTile = null;
                int maxIntersectionArea = 0;
                boolean isOccupied = true;

                for (BoardTile[] boardTiles : gameBoard.gameBoard){
                    for (BoardTile boardTile : boardTiles){
                        if(boardTile.rectangle.intersects(tile.rectangle)){
                            Rectangle intersection = boardTile.rectangle.intersection(tile.rectangle);
                            int intersectionArea = intersection.width * intersection.height;
                            if (intersectionArea > maxIntersectionArea) {
                                maxIntersectionArea = intersectionArea;
                                isOccupied = boardTile.occupied;
                                bestShadowTile = new AnimationTile(boardTile.rectangle, tile.color,
                                        boardTile.verticalNumber, boardTile.horizontallyNumber);
                            }
                        }
                    }
                }
                if (bestShadowTile != null && !isOccupied) {
                    if(animationTilesContainBoardTile(shadowTiles, bestShadowTile)) {
                        shadowTiles.add(bestShadowTile);
                    }
                }
            }
            if(shadowTiles.size() != movingShape.tiles.size()){
                shadowTiles.clear();
            }
        }
    }

    private boolean animationTilesContainBoardTile(Set<AnimationTile> shadowTiles, AnimationTile bestShadowTile) {
        return shadowTiles.stream().noneMatch(tile ->
                tile.verticalNumber == bestShadowTile.verticalNumber && tile.horizontallyNumber == bestShadowTile.horizontallyNumber
        );
    }

    @Override
    public void update() {

        if(!gameOver) {

            checkIfShapeBeenClicked();

            updateShapePosition();

            setBestHoveringTiles();

            if (!animation) {
                animationSquares.clear();
            }

            if (gp.inputListener.release) {
                if (moving) {
                    if (movingShape.getTiles().size() == shadowTiles.size()) {
                        boolean validMove = true;
                        for (AnimationTile animationTile : shadowTiles) {
                            if (gameBoard.gameBoard[animationTile.horizontallyNumber][animationTile.verticalNumber].occupied) {
                                validMove = false;
                                break;
                            }
                        }
                        if (validMove) {
                            Set<Integer> rows = new HashSet<>();
                            Set<Integer> col = new HashSet<>();

                            Set<BoardTile> boardTiles = new HashSet<>();

                            for (AnimationTile animationTile : shadowTiles) {
                                gameBoard.gameBoard[animationTile.horizontallyNumber][animationTile.verticalNumber]
                                        .occupied = true;
                                gameBoard.gameBoard[animationTile.horizontallyNumber][animationTile.verticalNumber]
                                        .color = animationTile.color;
                                rows.add(animationTile.horizontallyNumber);
                                col.add(animationTile.verticalNumber);
                            }

                            shapeConstructor.getPlayableShapes().remove(movingShape);
                            movingShape = null;

                            for (Integer rowIndex : rows) {
                                boolean fullRow = true;
                                for (BoardTile boardTile : gameBoard.gameBoard[rowIndex]) {
                                    if (!boardTile.occupied) {
                                        fullRow = false;
                                        break;
                                    }
                                }
                                if (fullRow) {
                                    boardTiles.addAll(Arrays.asList(gameBoard.gameBoard[rowIndex]));
                                }
                            }

                            for (Integer columnIndex : col) {
                                boolean fullColumn = true;
                                for (int rowIndex = 0; rowIndex < gameBoard.gameBoard.length; rowIndex++) {
                                    if (!gameBoard.gameBoard[rowIndex][columnIndex].occupied) {
                                        fullColumn = false;
                                        break;
                                    }
                                }
                                if (fullColumn) {
                                    for (int rowIndex = 0; rowIndex < gameBoard.gameBoard.length; rowIndex++) {
                                        boardTiles.add(gameBoard.gameBoard[rowIndex][columnIndex]);
                                    }
                                }
                            }

                            if (!boardTiles.isEmpty()) {
                                animation = true;
                                startTime = Instant.now();
                                for (BoardTile boardTile : boardTiles) {
                                    animationSquares.add(new AnimationTile(
                                            boardTile.rectangle,
                                            boardTile.color
                                    ));
                                    score++;
                                    boardTile.occupied = false;
                                }
                            }

                            if (shapeConstructor.getPlayableShapes().isEmpty()) {
                                shapeConstructor.setPlayableShapes(generateFittingShapes(3));
                                shapeConstructor.setShapesPosition();
                            } else {
                                if (!checkIfShapesFit(shapeConstructor.getPlayableShapes())) {
                                    gameOver = true;
                                }
                            }
                        } else {
                            movingShape.setPosition(originalPoint.x, originalPoint.y);
                        }
                    } else {
                        movingShape.setPosition(originalPoint.x, originalPoint.y);
                    }
                }
                moving = false;
                gp.inputListener.release = false;
            }
        } else {
            if(gp.inputListener.clicked){
                gp.inputListener.clicked = false;
                gp.stateController.setCurrentState(new HomeMenu(gp));
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.setColor(Color.LIGHT_GRAY);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));

        g2.drawString("Score: " + score, getCenterForX(g2,"Score: " + score, gp.screenWidth), gp.screenWidth - gp.tileSize);

        for (BoardTile[] tiles : gameBoard.gameBoard) {
            for (BoardTile boardTile : tiles) {
                if(boardTile.occupied) {
                    g2.drawImage(getImageByColor(boardTile.color), boardTile.rectangle.x, boardTile.rectangle.y, null);
                } else {
                    g2.drawImage(UtilityTool.getSubImage(sprite, 5, 1, gp.tileSize, gp.tileSize),
                            boardTile.rectangle.x, boardTile.rectangle.y, null);
                }
            }
        }


        if (!shadowTiles.isEmpty()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

            for (AnimationTile shadowTile : shadowTiles) {
                g2.drawImage(getImageByColor(shadowTile.color), shadowTile.rectangle.x, shadowTile.rectangle.y, null);
            }

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }

        for (int i = 0; i < (gameBoard.gameBoard.length + 1) * 4; i++) {
            int x = 0;
            int y = 0;

            if (i < gameBoard.gameBoard.length + 1) {
                // Övre kanten
                x = gameBoard.boardStartX - gp.tileSize + i * gp.tileSize;
                y = gameBoard.boardStartY - gp.tileSize;
            } else if (i < (gameBoard.gameBoard.length + 1) * 2) {
                // Högra kanten
                x = gameBoard.boardEndX;
                y = gameBoard.boardStartY - gp.tileSize + (i - (gameBoard.gameBoard.length + 1)) * gp.tileSize;
            } else if (i < (gameBoard.gameBoard.length + 1) * 3) {
                // Nedre kanten
                x = gameBoard.boardEndX - (i - (gameBoard.gameBoard.length + 1) * 2) * gp.tileSize;
                y = gameBoard.boardEndY;
            } else {
                // Vänstra kanten
                x = gameBoard.boardStartX - gp.tileSize;
                y = gameBoard.boardEndY - (i - (gameBoard.gameBoard.length + 1) * 3) * gp.tileSize;
            }

            // Rita bilden baserat på indexet
            if (i == 0) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 1, 4, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i < gameBoard.gameBoard.length + 1) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 2, 3, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i == gameBoard.gameBoard.length + 1) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 2, 4, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i < (gameBoard.gameBoard.length + 1) * 2) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 3, 3, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i == (gameBoard.gameBoard.length + 1) * 2) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 3, 4, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i < (gameBoard.gameBoard.length + 1) * 3) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 4, 3, gp.tileSize, gp.tileSize), x, y, null);
            } else if (i == (gameBoard.gameBoard.length + 1) * 3) {
                g2.drawImage(UtilityTool.getSubImage(sprite, 5, 3, gp.tileSize, gp.tileSize), x, y, null);
            } else {
                g2.drawImage(UtilityTool.getSubImage(sprite, 1, 3, gp.tileSize, gp.tileSize), x, y, null);
            }
        }

        if (animation) {
            Instant now = Instant.now();
            Duration elapsed = Duration.between(startTime, now);
            if (elapsed.getSeconds() < 1) {
                animationLaps++;
                animationSquares.forEach(square -> g2.drawImage(UtilityTool.scaleImage(
                                getImageByColor(square.color),
                                square.rectangle.width -animationLaps,
                                square.rectangle.height -animationLaps),
                        square.rectangle.x + animationLaps / 2,
                        square.rectangle.y + animationLaps / 2,
                        null));
            } else {
                animation = false;
                animationLaps = 0;
            }
        }

        shapeConstructor.getPlayableShapes().forEach(shape -> shape.getTiles().forEach(tile -> {
            g2.drawImage(getImageByColor(tile.color), tile.rectangle.x, tile.rectangle.y, null);
        }));

        if(gameOver){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));
            int y = gp.tileSize * 3;
            int x = getCenterForX(g2, "GAME OVER", gp.screenWidth);

            g2.setColor(Color.black);
            g2.drawString("GAME OVER", x+5, y+5);

            g2.setColor(Color.white);
            g2.drawString("GAME OVER", x, y);
        }
    }

    private int getCenterForX(Graphics2D g2, String text, int width){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return width/2 - length/2;
    }
}
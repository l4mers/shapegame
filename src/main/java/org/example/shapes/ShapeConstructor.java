package org.example.shapes;

import org.example.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShapeConstructor {
    private final ShapeStorage shapeStorage;
    private List<Shape> playableShapes = new ArrayList<>();
    private static final ShapeColor[] COLORS = ShapeColor.values();
    private Point[] points;

    public ShapeConstructor(GamePanel gp){
        this.shapeStorage = new ShapeStorage(gp.tileSize);
        playableShapes = loadRandomShapes(3);

    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public List<Shape> getPlayableShapes() {
        return playableShapes;
    }

    public void setPlayableShapes(List<Shape> playableShapes) {
        this.playableShapes = playableShapes;
    }

    public List<Shape> loadRandomShapes(int i) {
        return IntStream.range(0, i)
                .mapToObj(j -> {
                    Shape shape = shapeStorage.getShapes().get(new Random().nextInt(shapeStorage.getShapes().size()));
                    Shape newShape = new Shape(shape.getName(), cloneTiles(shape.getTiles()));
                    return loadRandomTileProperties(newShape);
                })
                .collect(Collectors.toList());
    }

    private Shape loadRandomTileProperties(Shape shape) {
        ShapeColor randomColorType = COLORS[new Random().nextInt(COLORS.length)];
        shape.getTiles().forEach(tile -> {
            tile.color = randomColorType;
        });
        return shape;
    }

    private List<Tile> cloneTiles(List<Tile> tiles) {
        List<Tile> newTiles = new ArrayList<>();
        for (Tile tile : tiles) {
            newTiles.add(new Tile(tile));
        }
        return newTiles;
    }

    public void setShapesPosition(){
        for (int i = 0; i < playableShapes.size(); i++) {
            playableShapes.get(i).setPosition(
                    points[i].x, points[i].y
            );
        }
    }

    public List<Shape> loadSpecialShapes(int i) {
        return IntStream.range(0, i)
                .mapToObj(j -> {
                    Shape shape = shapeStorage.getSpecialShapes().get(new Random().nextInt(shapeStorage.getSpecialShapes().size()));
                    Shape newShape = new Shape(shape.getName(), cloneTiles(shape.getTiles()));
                    return loadRandomTileProperties(newShape);
                })
                .collect(Collectors.toList());
    }
}

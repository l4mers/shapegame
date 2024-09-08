package org.example.shapes;

import org.example.GamePanel;

import java.awt.*;

public class GameBoard {
    public final BoardTile[][] gameBoard = new BoardTile[8][8];
    public int boardStartX, boardStartY, boardEndX, boardEndY;

    public GameBoard(GamePanel gp){

        boardStartX = (gp.screenWidth - (gameBoard.length * gp.tileSize)) / 2;
        boardStartY = gp.tileSize;

        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard[x].length; y++) {
                int coordinateX = boardStartX + (x * gp.tileSize);
                int coordinateY = boardStartY + (y * gp.tileSize);
                gameBoard[x][y] = new BoardTile(
                        new Rectangle(boardStartX + (x * gp.tileSize), boardStartY + (y * gp.tileSize), gp.tileSize, gp.tileSize),
                        x, y);
                if (x == gameBoard.length - 1 && y == gameBoard[x].length - 1) {
                    boardEndX = coordinateX + gp.tileSize;
                    boardEndY = coordinateY + gp.tileSize;
                }
            }
        }
    }
}

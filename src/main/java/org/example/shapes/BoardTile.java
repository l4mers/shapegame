package org.example.shapes;

import java.awt.*;

public class BoardTile {
    public Rectangle rectangle;
    public ShapeColor color;
    public boolean occupied = false;
    public int horizontallyNumber, verticalNumber;

    public BoardTile(Rectangle rectangle, int horizontallyNumber, int verticalNumber) {
        this.rectangle = rectangle;
        this.verticalNumber = verticalNumber;
        this.horizontallyNumber = horizontallyNumber;
    }
}

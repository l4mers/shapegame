package org.example.shapes;

import java.awt.*;

public class Tile {
    public Rectangle rectangle;
    public ShapeColor color;
    public int relativeX = 0;
    public int relativeY = 0;

    public Tile(Rectangle rectangle, int relativeX, int relativeY) {
        this.rectangle = rectangle;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    public Tile(Tile tile) {
        this.rectangle = new Rectangle(tile.rectangle);
        this.relativeX = tile.relativeX;
        this.relativeY = tile.relativeY;
    }

    public void setRelativeXAndY(int relativeX, int relativeY){
        this.relativeX =  relativeX;
        this.relativeY =  relativeY;
    }

    public void setPosition(int x, int y) {
        this.rectangle.setLocation(x + relativeX, y + relativeY);
    }
}

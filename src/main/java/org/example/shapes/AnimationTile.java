package org.example.shapes;

import java.awt.*;

public class AnimationTile {
    public Rectangle rectangle;
    public ShapeColor color;
    public int horizontallyNumber, verticalNumber;

    public AnimationTile(Rectangle rectangle, ShapeColor color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public AnimationTile(Rectangle rectangle, ShapeColor color, int verticalNumber, int horizontallyNumber) {
        this.rectangle = rectangle;
        this.color = color;
        this.verticalNumber = verticalNumber;
        this.horizontallyNumber = horizontallyNumber;
    }
}

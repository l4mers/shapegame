package org.example.shapes;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    public String name;
    public List<Tile> tiles;

    public Shape(String name, List<Tile> tiles) {
        this.name = name;
        this.tiles = tiles;
    }
    public Shape(Shape shape) {
        this.name = shape.name;
        this.tiles = new ArrayList<>();
        for (Tile tile : shape.tiles) {
            this.tiles.add(new Tile(tile));
        }
    }
    public Shape() {}

    public int getLeadingX(){
        return tiles.getFirst().rectangle.x;
    }
    public int getLeadingY(){
        return tiles.getFirst().rectangle.y;
    }

    public void setPosition(int x, int y) {
        if (tiles.isEmpty()) {
            return;
        }

        for (Tile tile : tiles) {
            tile.setPosition(x, y);
        }
    }

    public String getName() {
        return name;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

}

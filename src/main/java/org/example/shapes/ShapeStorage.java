package org.example.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeStorage {
    private final List<Shape> shapes = new ArrayList<>();

    private final List<Shape> specialShapes = new ArrayList<>();

    public ShapeStorage(int size){

        //Cube
        Tile cubeTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile cubeTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile cubeTile3 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile cubeTile4 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("cube", List.of(
                        cubeTile1, cubeTile2, cubeTile3, cubeTile4
                ))
        );

        //req
        Tile reqTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile reqTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile reqTile3 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile reqTile4 = new Tile(new Rectangle(0,0, size, size), size, size);
        Tile reqTile5 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile reqTile6 = new Tile(new Rectangle(0,0, size, size), size, size * 2);
        shapes.add(
                new Shape("req", List.of(
                        reqTile1, reqTile2, reqTile3, reqTile4, reqTile5, reqTile6
                ))
        );

        //req horizontal
        Tile reqhTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile reqhTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile reqhTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile reqhTile4 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile reqhTile5 = new Tile(new Rectangle(0,0, size, size), size, size);
        Tile reqhTile6 = new Tile(new Rectangle(0,0, size, size), size * 2, size);
        shapes.add(
                new Shape("req horizontal", List.of(
                        reqhTile1, reqhTile2, reqhTile3, reqhTile4, reqhTile5, reqhTile6
                ))
        );

        //big Cube
        Tile bcubeTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile bcubeTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile bcubeTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile bcubeTile4 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile bcubeTile5 = new Tile(new Rectangle(0,0, size, size), size, size);
        Tile bcubeTile6 = new Tile(new Rectangle(0,0, size, size), size * 2, size);
        Tile bcubeTile7 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile bcubeTile8 = new Tile(new Rectangle(0,0, size, size), size, size * 2);
        Tile bcubeTile9 = new Tile(new Rectangle(0,0, size, size), size * 2, size * 2);
        shapes.add(
                new Shape("big Cube", List.of(
                        bcubeTile1, bcubeTile2, bcubeTile3, bcubeTile4, bcubeTile5, bcubeTile6, bcubeTile7, bcubeTile8, bcubeTile9
                ))
        );

        //L
        Tile lTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lTile3 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile lTile4 = new Tile(new Rectangle(0,0, size, size), size, size * 2);
        shapes.add(
                new Shape("L", List.of(
                        lTile1, lTile2, lTile3, lTile4
                ))
        );

        //L up right
        Tile lurTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lurTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lurTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        Tile lurTile4 = new Tile(new Rectangle(0,0, size, size), size, size * 2);
        shapes.add(
                new Shape("L up right", List.of(
                        lurTile1, lurTile2, lurTile3, lurTile4
                ))
        );

        //L up left
        Tile lulTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lulTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lulTile3 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lulTile4 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        shapes.add(
                new Shape("L up left", List.of(
                        lulTile1, lulTile2, lulTile3, lulTile4
                ))
        );

        //L mirror
        Tile lmTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lmTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lmTile3 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile lmTile4 = new Tile(new Rectangle(0,0, size, size), -size, size * 2);
        shapes.add(
                new Shape("L mirror", List.of(
                        lmTile1, lmTile2, lmTile3, lmTile4
                ))
        );

        //L right upp
        Tile lruTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lruTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lruTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile lruTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, -size);
        shapes.add(
                new Shape("L right upp", List.of(
                        lruTile1, lruTile2, lruTile3, lruTile4
                ))
        );

        //L right down
        Tile lrdTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lrdTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lrdTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile lrdTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, size);
        shapes.add(
                new Shape("L right down", List.of(
                        lrdTile1, lrdTile2, lrdTile3, lrdTile4
                ))
        );

        //L left up
        Tile lluTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lluTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lluTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        Tile lluTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, size);
        shapes.add(
                new Shape("L left up", List.of(
                        lluTile1, lluTile2, lluTile3, lluTile4
                ))
        );

        //L left down
        Tile lldTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lldTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lldTile3 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lldTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        shapes.add(
                new Shape("L left down", List.of(
                        lldTile1, lldTile2, lldTile3, lldTile4
                ))
        );

        //L small
        Tile lsTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lsTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("L small", List.of(
                        lsTile1, lsTile2, lsTile3
                ))
        );

        //L small mirror
        Tile lsmTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsmTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lsmTile3 = new Tile(new Rectangle(0,0, size, size), -size, size);
        shapes.add(
                new Shape("L small mirror", List.of(
                        lsmTile1, lsmTile2, lsmTile3
                ))
        );

        //L small right up
        Tile lsruTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsruTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lsruTile3 = new Tile(new Rectangle(0,0, size, size), size, -size);
        shapes.add(
                new Shape("L small right up", List.of(
                        lsruTile1, lsruTile2, lsruTile3
                ))
        );

        //L small right down
        Tile lsrdTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsrdTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lsrdTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("L small right down", List.of(
                        lsrdTile1, lsrdTile2, lsrdTile3
                ))
        );

        //L small left down
        Tile lsldTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsldTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lsldTile3 = new Tile(new Rectangle(0,0, size, size), 0, size);
        shapes.add(
                new Shape("L small left down", List.of(
                        lsldTile1, lsldTile2, lsldTile3
                ))
        );

        //L small left up
        Tile lsluTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsluTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile lsluTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("L small left up", List.of(
                        lsluTile1, lsluTile2, lsluTile3
                ))
        );

        //L small up left
        Tile lsulTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsulTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lsulTile3 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("L small up left", List.of(
                        lsulTile1, lsulTile2, lsulTile3
                ))
        );

        //L small up right
        Tile lsurTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile lsurTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile lsurTile3 = new Tile(new Rectangle(0,0, size, size), 0, size);
        shapes.add(
                new Shape("L small up right", List.of(
                        lsurTile1, lsurTile2, lsurTile3
                ))
        );

        //I
        Tile iTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile iTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        shapes.add(
                new Shape("I", List.of(
                        iTile1, iTile2
                ))
        );

        //I horizontal
        Tile ihTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ihTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        shapes.add(
                new Shape("I horizontal", List.of(
                        ihTile1, ihTile2
                ))
        );

        // I big
        Tile ibTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ibTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile ibTile3 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        shapes.add(
                new Shape("I big", List.of(
                        ibTile1, ibTile2, ibTile3
                ))
        );

        // I huge
        Tile ihuTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ihuTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile ihuTile3 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile ihuTile4 = new Tile(new Rectangle(0,0, size, size), 0, size * 3);
        shapes.add(
                new Shape("I huge", List.of(
                        ihuTile1, ihuTile2, ihuTile3, ihuTile4
                ))
        );

        // I gigantic
        Tile iguTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile iguTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile iguTile3 = new Tile(new Rectangle(0,0, size, size), 0, size * 2);
        Tile iguTile4 = new Tile(new Rectangle(0,0, size, size), 0, size * 3);
        Tile iguTile5 = new Tile(new Rectangle(0,0, size, size), 0, size * 4);
        shapes.add(
                new Shape("I huge", List.of(
                        iguTile1, iguTile2, iguTile3, iguTile4, iguTile5
                ))
        );

        // I big horizontal
        Tile ibhTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ibhTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile ibhTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        shapes.add(
                new Shape("I big horizontal", List.of(
                        ibhTile1, ibhTile2, ibhTile3
                ))
        );

        // I huge horizontal
        Tile ihhTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ihhTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile ihhTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile ihhTile4 = new Tile(new Rectangle(0,0, size, size), size * 3, 0);
        shapes.add(
                new Shape("I huge horizontal", List.of(
                        ihhTile1, ihhTile2, ihhTile3, ihhTile4
                ))
        );

        // I gigantic horizontal
        Tile ihgTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile ihgTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile ihgTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile ihgTile4 = new Tile(new Rectangle(0,0, size, size), size * 3, 0);
        Tile ihgTile5 = new Tile(new Rectangle(0,0, size, size), size * 4, 0);
        shapes.add(
                new Shape("I gigantic horizontal", List.of(
                        ihgTile1, ihgTile2, ihgTile3, ihgTile4, ihgTile5
                ))
        );

        // T
        Tile tTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile tTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile tTile3 = new Tile(new Rectangle(0,0, size, size), size * 2, 0);
        Tile tTile4 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("T", List.of(
                        tTile1, tTile2, tTile3, tTile4
                ))
        );

        // T right
        Tile trTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile trTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile trTile3 = new Tile(new Rectangle(0,0, size, size),0, size * 2);
        Tile trTile4 = new Tile(new Rectangle(0,0, size, size), -size, -size);
        shapes.add(
                new Shape("T right", List.of(
                        trTile1, trTile2, trTile3, trTile4
                ))
        );

        // T left
        Tile tlTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile tlTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile tlTile3 = new Tile(new Rectangle(0,0, size, size),0, size * 2);
        Tile tlTile4 = new Tile(new Rectangle(0,0, size, size), size, size);
        shapes.add(
                new Shape("T right", List.of(
                        tlTile1, tlTile2, tlTile3, tlTile4
                ))
        );

        // T down
        Tile tdTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile tdTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile tdTile3 = new Tile(new Rectangle(0,0, size, size),size * 2, 0);
        Tile tdTile4 = new Tile(new Rectangle(0,0, size, size), size, -size);
        shapes.add(
                new Shape("T down", List.of(
                        tdTile1, tdTile2, tdTile3, tdTile4
                ))
        );

        // Z
        Tile zTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile zTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile zTile3 = new Tile(new Rectangle(0,0, size, size),size, size);
        Tile zTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, size);
        shapes.add(
                new Shape("Z", List.of(
                        zTile1, zTile2, zTile3, zTile4
                ))
        );

        // Z left
        Tile zlTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile zlTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile zlTile3 = new Tile(new Rectangle(0,0, size, size), -size, size);
        Tile zlTile4 = new Tile(new Rectangle(0,0, size, size), -size, size * 2);
        shapes.add(
                new Shape("Z left", List.of(
                        zlTile1, zlTile2, zlTile3, zlTile4
                ))
        );

        // Z right
        Tile zrlTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile zrlTile2 = new Tile(new Rectangle(0,0, size, size), 0, size);
        Tile zrlTile3 = new Tile(new Rectangle(0,0, size, size),size, size);
        Tile zrlTile4 = new Tile(new Rectangle(0,0, size, size), size, size * 2);
        shapes.add(
                new Shape("Z right", List.of(
                        zrlTile1, zrlTile2, zrlTile3, zrlTile4
                ))
        );

        // Z down
        Tile zdlTile1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile zdlTile2 = new Tile(new Rectangle(0,0, size, size), size, 0);
        Tile zdlTile3 = new Tile(new Rectangle(0,0, size, size), size, -size);
        Tile zdlTile4 = new Tile(new Rectangle(0,0, size, size), size * 2, -size);
        shapes.add(
                new Shape("Z down", List.of(
                        zdlTile1, zdlTile2, zdlTile3, zdlTile4
                ))
        );


        Tile special1 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        specialShapes.add(
                new Shape("Single", List.of(
                        special1
                ))
        );

        Tile special2 = new Tile(new Rectangle(0,0, size, size), 0, 0);
        Tile special3 = new Tile(new Rectangle(0,0, size, size), size, size);
        specialShapes.add(
                new Shape("Tilting", List.of(
                        special2, special3
                ))
        );

    }

    public List<Shape> getSpecialShapes() {
        return specialShapes;
    }
    public List<Shape> getShapes() {
        return shapes;
    }
}

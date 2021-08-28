package object;

import render.Tileset;
import tool.Maths;
import world.Laberinth;
import world.map.DungeonMap;
import world.map.Map;
import world.map.MapLayer;

import java.awt.*;

public class DungeonTiles {

    private final static int FLOOR_INDEX = 0;
    private final static int SOLID_INDEX = 1;

    private final static int[] DOWN_FACING_WALL_TILES = {1,2,3,4};
    private final static int[] UP_FACING_WALL_TILES = {51,52,41,42,43,44};
    private final static int[] LEFT_FACING_WALL_TILES = {5,15,25,35};
    private final static int[] RIGHT_FACING_WALL_TILES = {0,10,20,30};

    private final static int[] DOWN_FACING_FLOOR_TILES = {32,33};
    private final static int[] UP_FACING_FLOOR_TILES = {12,13};
    private final static int[] LEFT_FACING_FLOOR_TILES = {21};
    private final static int[] RIGHT_FACING_FLOOR_TILES = {24};

    private final static int[] TOP_RIGHT_VERTEX_FLOOR_TILES = {14};
    private final static int[] BOTTOM_RIGHT_VERTEX_FLOOR_TILES = {34};
    private final static int[] TOP_LEFT_VERTEX_FLOOR_TILES = {11};
    private final static int[] BOTTOM_LEFT_VERTEX_FLOOR_TILES = {31};

    private final static int[] NORMAL_FLOOR_TILES = {6,7,8,9,16,17,18,19,26,27,28,29};
    private final static int[] NORMAL_ABYSS_TILES = {78};

    private final static int[] BOTTOM_LEFT_VERTEX_WALL_TILES = {40};
    private final static int[] BOTTOM_RIGHT_VERTEX_WALL_TILES = {45};
    private final static int[] TOP_LEFT_VERTEX_WALL_TILES = {50,54};
    private final static int[] TOP_RIGHT_VERTEX_WALL_TILES = {53,55};

    private final static int[][] DOWN_FACING_TILES = {DOWN_FACING_FLOOR_TILES,DOWN_FACING_WALL_TILES};
    private final static int[][] UP_FACING_TILES = {UP_FACING_FLOOR_TILES,UP_FACING_WALL_TILES};
    private final static int[][] LEFT_FACING_TILES = {LEFT_FACING_FLOOR_TILES,LEFT_FACING_WALL_TILES};
    private final static int[][] RIGHT_FACING_TILES = {RIGHT_FACING_FLOOR_TILES,RIGHT_FACING_WALL_TILES};

    private final static int[][] NORMAL_TILES = {NORMAL_FLOOR_TILES,NORMAL_ABYSS_TILES};
    private final static int[] SPECIAL_FLOOR_TILES = {69};
    private final static int[] DECORATION_TILES = {49,59,68,77};

    private final static int[][] TOP_RIGHT_VERTEX_TILES = {TOP_RIGHT_VERTEX_FLOOR_TILES,TOP_RIGHT_VERTEX_WALL_TILES};
    private final static int[][] BOTTOM_RIGHT_VERTEX_TILES = {BOTTOM_RIGHT_VERTEX_FLOOR_TILES,BOTTOM_RIGHT_VERTEX_WALL_TILES};
    private final static int[][] TOP_LEFT_VERTEX_TILES = {TOP_LEFT_VERTEX_FLOOR_TILES,TOP_LEFT_VERTEX_WALL_TILES};
    private final static int[][] BOTTOM_LEFT_VERTEX_TILES = {BOTTOM_LEFT_VERTEX_FLOOR_TILES,BOTTOM_LEFT_VERTEX_WALL_TILES};




    public static void setTiles(Laberinth lab, int width, Map map){
        int ind, x1, y1;
        boolean a;
        MapLayer main = new MapLayer(lab.width()*width,lab.height()*width);
        MapLayer decorations = new MapLayer(lab.width()*width,lab.height()*width);
        for(int y=0;y<lab.height();y++){
            for(int x=0;x<lab.width();x++) {
                for(y1=0;y1<width;y1++){
                    for(x1=0;x1<width;x1++){
                        setT(NORMAL_TILES[SOLID_INDEX],main,x*width+x1,y*width+y1);
                    }
                }
            }
        }
        for(int y=1;y<lab.height()-1;y++){
            for(int x=1;x<lab.width()-1;x++){
                if(a=lab.get(x,y)) {
                    ind = FLOOR_INDEX;
                } else {
                    ind = SOLID_INDEX;
                    for(y1=0;y1<width;y1++){
                        for(x1=0;x1<width;x1++){
                            map.add(x*width+x1,y*width+y1,new Rectangle(Tileset.DEFAULT_TILE_SIZE*(x*width+x1),Tileset.DEFAULT_TILE_SIZE*(y*width+y1),Tileset.DEFAULT_TILE_SIZE,Tileset.DEFAULT_TILE_SIZE));
                        }
                    }
                }

                for(y1=1;y1<width-1;y1++){
                    for(x1=1;x1<width-1;x1++){
                        setT(NORMAL_TILES[ind],main,x*width+x1,y*width+y1);
                    }
                }

                if (lab.get(x - 1, y) != a && lab.get(x, y - 1) != a) {
                    setT(TOP_LEFT_VERTEX_TILES[ind], main, x * width, y * width);
                } else if (lab.get(x - 1, y) == a && lab.get(x, y - 1) != a) {
                    setT(UP_FACING_TILES[ind], main, x * width, y * width);
                } else if (lab.get(x - 1, y) != a && lab.get(x, y - 1) == a) {
                    setT(LEFT_FACING_TILES[ind], main, x * width, y * width);
                } else {
                    setT(NORMAL_TILES[ind], main, x * width, y * width);
                }
                x1 = 1;
                for (; x1 < width - 1; x1++) {
                    if (lab.get(x, y - 1) == a) {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width);
                    } else {
                        setT(UP_FACING_TILES[ind], main, x * width + x1, y * width);
                    }
                }
                if (lab.get(x + 1, y) != a && lab.get(x, y - 1) != a) {
                    setT(TOP_RIGHT_VERTEX_TILES[ind], main, x * width + x1, y * width);
                } else if (lab.get(x + 1, y) == a && lab.get(x, y - 1) != a) {
                    setT(UP_FACING_TILES[ind], main, x * width + x1, y * width);
                } else if (lab.get(x + 1, y) != a && lab.get(x, y - 1) == a) {
                    setT(RIGHT_FACING_TILES[ind], main, x * width + x1, y * width);
                } else {
                    setT(NORMAL_TILES[ind], main, x * width + x1, y * width);
                }

                y1 = 1;
                for(;y1<width-1;y1++){
                    if(lab.get(x+1,y)==a) {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width+y1);
                    } else {
                        setT(RIGHT_FACING_TILES[ind], main, x * width + x1, y * width+y1);
                    }
                }
                if(ind==FLOOR_INDEX) {
                    if (lab.get(x + 1, y) != a && lab.get(x, y + 1) != a) {
                        setT(BOTTOM_RIGHT_VERTEX_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x + 1, y) == a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x + 1, y) != a && lab.get(x, y + 1) == a) {
                        setT(RIGHT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                    }
                    x1--;
                    for (; x1 > 0; x1--) {
                        if (lab.get(x, y + 1) == a) {
                            setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                        } else {
                            setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                        }
                    }
                    if (lab.get(x - 1, y) != a && lab.get(x, y + 1) != a) {
                        setT(BOTTOM_LEFT_VERTEX_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x - 1, y) == a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x - 1, y) != a && lab.get(x, y + 1) == a) {
                        setT(LEFT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                    }
                } else {
                    //x1;

                    if (lab.get(x + 1, y) != a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x + 1, y) == a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x + 1, y) != a && lab.get(x, y + 1) == a) {
                        setT(RIGHT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x + 1, y) == a && lab.get(x, y + 1) == a && lab.get(x+1, y + 1) != a) {
                        setT(RIGHT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                    }
                    x1--;

                    for (; x1 > 0; x1--) {
                        if (lab.get(x, y + 1) == a) {
                            setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                        } else {
                            setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                        }
                    }
                    if (lab.get(x - 1, y) != a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x - 1, y) == a && lab.get(x, y + 1) != a) {
                        setT(DOWN_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x - 1, y) != a && lab.get(x, y + 1) == a) {
                        setT(LEFT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else if (lab.get(x - 1, y) == a && lab.get(x, y + 1) == a && lab.get(x-1, y + 1) != a) {
                        setT(LEFT_FACING_TILES[ind], main, x * width + x1, y * width + y1);
                    } else {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width + y1);
                    }
                    //x1++;
                }
                y1--;
                for(;y1>0;y1--){
                    if(lab.get(x-1,y)==a) {
                        setT(NORMAL_TILES[ind], main, x * width + x1, y * width+y1);
                    } else {
                        setT(LEFT_FACING_TILES[ind], main, x * width + x1, y * width+y1);
                    }
                }
            }
        }
        Pair[] special = {lab.start(),lab.exit()};
        for(int i=0;i<special.length;i++) {
            for (int y = special[i].y() * width; y < special[i].y() * width + width; y++) {
                for (int x = special[i].x() * width; x < special[i].x() * width + width; x++) {
                    setT(SPECIAL_FLOOR_TILES, main, x, y);
                }
            }
        }

        int x, y, decs = (int)((float)((lab.height()*lab.width())*width*0.01));
        while(decs>0){
            x = 1+Maths.rand((lab.width()-1)*width);
            y = 1+Maths.rand((lab.height()-1)*width);
            if(!map.solid(x,y)){
                setT(DECORATION_TILES, decorations, x, y);
                decs--;
            }
        }


        map.add(main);
        map.add(decorations);
    }

    private static void setT(int[] constant_array, MapLayer layer, int x, int y){
        layer.setTile(x,y,constant_array[Maths.rand(constant_array.length)]);
    }
}

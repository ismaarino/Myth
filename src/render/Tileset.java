package render;

import object.Loader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Tileset {

    public final static int DEFAULT_TILE_SIZE = 32;

    private int id;

    private List<Tile> tiles;

    public Tileset(int id, String image_path){
        this.id = id;
        tiles = new ArrayList<Tile>();
        BufferedImage image = Loader.readImage(image_path);
        int i = 0;
        for(int y=0;y<image.getHeight();y+=DEFAULT_TILE_SIZE){
            for(int x=0;x<image.getWidth();x+=DEFAULT_TILE_SIZE){
                tiles.add(new Tile(i, image.getSubimage(x, y, DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE)));
                i++;
            }
        }
        image.flush();
    }

    public boolean is(int id){
        return this.id == id;
    }

    public Tile getTile(int id){
        return tiles.get(id);
    }
}

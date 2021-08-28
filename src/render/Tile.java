package render;

import java.awt.image.BufferedImage;

public class Tile {

    private int id;
    private BufferedImage image;

    public Tile(int id, BufferedImage image) {
        this.id = id;
        this.image = image;
    }

    public boolean is(int id){
        return this.id==id;
    }

    public BufferedImage image(){
        return image;
    }
}

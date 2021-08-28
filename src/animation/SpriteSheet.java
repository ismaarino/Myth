package animation;

import object.Loader;
import render.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpriteSheet implements Iterable{

    private List<BufferedImage> sprites;


    public SpriteSheet(BufferedImage img, int width, int height){
        sprites = new ArrayList<BufferedImage>();
        int i = 0;
        for(int y=0;y<img.getHeight();y+=height){
            for(int x=0;x<img.getWidth();x+=width){
                sprites.add(img.getSubimage(x, y, width, height));
                i++;
            }
        }
        img.flush();
    }

    public Animation animation(String id, double speed){
        Animation r = new Animation(id, speed);
        sprites.forEach(s->r.addFrame(s));
        return r;
    }

    public BufferedImage frame(int i){
        return sprites.get(i);
    }

    public int frames(){
        return sprites.size();
    }

    @Override
    public Iterator iterator() {
        return sprites.iterator();
    }
}

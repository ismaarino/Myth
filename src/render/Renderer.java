package render;

import animation.SpriteSheet;
import main.Game;
import object.Pair;
import object.Store;
import world.Camera;
import world.entity.Entity;
import world.entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Renderer {

    private Graphics2D g;

    private Color background_color;

    private int scale;


    private Store<BufferedImage> images;

    private Store<SpriteSheet> sprites;

    private List<Tileset> tilesets;
    private Tileset last;

    private Camera camera;

    public static int[] minAndMaxTile(Camera c, int width, int height){
        Pair p = c.getPos();
        return minAndMaxTile(p.x(), p.y(), width, height);
    }

    public static int[] minAndMaxTile(Entity p, int width, int height){
        return minAndMaxTile(p.x(), p.y(), width, height);
    }

    private static int[] minAndMaxTile(int x, int y, int width, int height){
        int iniY = (y/Tileset.DEFAULT_TILE_SIZE)-((Game.height/Game.SCALE)/Tileset.DEFAULT_TILE_SIZE)/2-4;
        int iniX = (x/Tileset.DEFAULT_TILE_SIZE)-((Game.width/Game.SCALE)/Tileset.DEFAULT_TILE_SIZE)/2-4;
        if(iniY<0){
            iniY=0;
        }
        if(iniX<0){
            iniX=0;
        }
        int endY = 5+(y/Tileset.DEFAULT_TILE_SIZE)+((Game.height/Game.SCALE)/Tileset.DEFAULT_TILE_SIZE)/2;
        int endX = 5+(x/Tileset.DEFAULT_TILE_SIZE)+((Game.width/Game.SCALE)/Tileset.DEFAULT_TILE_SIZE)/2;
        if(endY>height){
            endY=height;
        }
        if(endX>width){
            endX=width;
        }
        return new int[]{(int)(((float)iniX/(float)Tileset.DEFAULT_TILE_SIZE)*Tileset.DEFAULT_TILE_SIZE),(int)(((float)iniY/(float)Tileset.DEFAULT_TILE_SIZE)*Tileset.DEFAULT_TILE_SIZE),(int)(((float)endX/(float)Tileset.DEFAULT_TILE_SIZE)*Tileset.DEFAULT_TILE_SIZE),(int)(((float)endY/(float)Tileset.DEFAULT_TILE_SIZE)*Tileset.DEFAULT_TILE_SIZE)};
    }

    public Renderer(int scale, Camera c){
        this.scale = scale;
        background_color = Color.BLACK;
        tilesets = new ArrayList<Tileset>();
        images = new Store<BufferedImage>();
        sprites = new Store<SpriteSheet>();
        camera = c;
    }

    public void addImage(String id, BufferedImage i){
        images.add(id, i);
    }

    public void addSpriteSheet(String id, SpriteSheet s){
        sprites.add(id, s);
    }

    public SpriteSheet getSpriteSheet(String id){
        return sprites.get(id);
    }

    public void setBackgroundColor(Color c){
        background_color = c;
    }

    public void renderBackground(){
        g.setColor(background_color);
        g.fillRect(0,0,Game.width,Game.height);
    }

    public void setGraphics(Graphics2D g2){
        g = g2;
        g.scale(scale, scale);

        /*
        g.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);*/
        g.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    public void addTileset(Tileset t){
        tilesets.add(t);
    }

    public void opacity(float o){
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o));
    }

    public void renderTile(int tileset_id, int tile_id, int x, int y){
        if(last==null || !last.is(tileset_id)) {
            last = tilesets.stream().filter(t -> t.is(tileset_id)).findFirst().get();
        }
        if(tile_id>-1) {
            renderImage(last.getTile(tile_id).image(), x, y);
        }
    }

    public void renderImage(BufferedImage img, int x, int y){

        g.drawImage(img, x+camera.XOffset, y+camera.YOffset, null);
    }

    public void renderImage(BufferedImage img, int x, int y, float xscale, float yscale){
        g.drawImage(img, x+camera.XOffset, y+camera.YOffset, (int)((float)img.getWidth()*xscale), (int)((float)img.getHeight()*yscale),null);
    }

    public void renderImage(String img_id, int x, int y){
        renderImage(images.get(img_id),x,y);
    }
}

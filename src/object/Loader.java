package object;

import animation.SpriteSheet;
import render.Renderer;
import render.Tileset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Loader {

    public final static String RES_FOLDER = "./resource/";
    public final static String TILESETS_FOLDER = "./tilesets/";

    public static BufferedImage readImage(String p){
        try {
            return ImageIO.read(new File(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadResources(Renderer r){
        r.addTileset(new Tileset(1,RES_FOLDER+TILESETS_FOLDER+"dungeon.png"));
        r.addImage("health_bar",readImage(RES_FOLDER+"health_bar.png"));
        r.addSpriteSheet("health_bar_colors",new SpriteSheet(readImage(RES_FOLDER+"health_colors.png"),38,3));
    }
}

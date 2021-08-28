package animation;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PlayerTexture {

    private final static String PACK_FOLDER = "./resource/player_textures/";
    private final static String PACK_EXTENSION = ".pack";
    private final static int SIZE = 32;

    public static AnimationSet fromFile(String name){
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(PACK_FOLDER+name+PACK_EXTENSION);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            AnimationSet as = new AnimationSet();
            SpriteSheet s;
            InputStream stream;
            ZipEntry entry;
            while (entries.hasMoreElements()) {
                entry = entries.nextElement();
                stream = zipFile.getInputStream(entry);
                s = new SpriteSheet(ImageIO.read(stream), SIZE, SIZE);
                if(s.frames()<=4) {
                    as.add(s.animation(entry.getName().replaceAll(".png",""),10));
                }
            }
            return as;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

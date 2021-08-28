package gui;

import animation.SpriteSheet;
import render.Renderer;
import world.entity.Entity;

import java.awt.image.BufferedImage;

public class GUI {

    public static void drawHealhBar(Entity e, Renderer r){
        float full = e.getHPRelation();
        if(full<1) {
            r.renderImage("health_bar", e.x() - 4, e.y() - 10);

            SpriteSheet colors = r.getSpriteSheet("health_bar_colors");
            BufferedImage c1, c2;
            float opacity;
            if (full > 0.5) {
                c1 = colors.frame(1);
                c2 = colors.frame(2);
                opacity = (full - 0.5f) / 0.5f;
            } else {
                c1 = colors.frame(0);
                c2 = colors.frame(1);
                opacity = full / 0.5f;
            }
            r.renderImage(c1, e.x() - 3, e.y() - 10, full, 1);
            r.opacity(opacity);
            r.renderImage(c2, e.x() - 3, e.y() - 10, full, 1);
            r.opacity(1);
        }
    }
}

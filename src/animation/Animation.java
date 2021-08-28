package animation;

import render.Renderer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private String id;
    private int frame_index;
    private double speed, last_tick;
    private List<BufferedImage> frames;

    public Animation(String id, double speed){
        build(id, speed);
    }

    public Animation(String id, BufferedImage img){
        build(id, 0);
        frames.add(img);
    }

    private void build(String i, double s){
        id = i;
        frames = new ArrayList<BufferedImage>();
        frame_index=0;
        last_tick = System.currentTimeMillis();
        speed = s;
    }

    public int size(){
        return frames.get(0).getWidth();
    }

    public void reset(){
        frame_index=0;
    }

    public void addFrame(BufferedImage frame){
        frames.add(frame);
    }

    public void tick(){
        if(speed>0 && frames.size()>1) {
            if (System.currentTimeMillis() - last_tick > (1000f / (float)speed)) {
                if (frame_index == frames.size() - 1) {
                    frame_index = 0;
                } else {
                    frame_index++;
                }
                last_tick = System.currentTimeMillis();
            }

        }
    }

    public boolean is(String id){
        return this.id.equals(id);
    }
    public boolean is(Animation a){
        return is(a.id);
    }

    public void render(int x, int y, Renderer r){
        r.renderImage(frames.get(frame_index),x, y);
    }
}

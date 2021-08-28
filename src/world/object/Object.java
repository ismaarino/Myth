package world.object;

import animation.Animation;
import animation.AnimationSet;
import render.Renderer;
import tool.ID;
import world.list.Listable;
import world.map.Map;

import java.awt.image.BufferedImage;

public class Object implements Listable {

    private int id, x, y;
    private AnimationSet animations;
    private Animation a;
    private boolean solid;

    public Object(int x, int y, Animation a){
        animations = new AnimationSet();
        animations.add(a);
        build(a);
    }

    public Object(Object o){
        //
    }

    public Object(int x, int y, AnimationSet a){
        animations = a;
        build(null);
    }

    public Object(int x, int y, BufferedImage img){
        build(new Animation("",img));
    }

    private void build(Animation a){
        this.x = x;
        this.y = y;
        this.a = a;
        solid = false;
        id = ID.generate();
    }

    public void setSolid(){
        solid = true;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public void addA(Animation a){
        animations.add(a);
    }

    public void selectA(Animation a){
        this.a = a;
    }

    public Object copy(){
        return new Object(this);
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int id() {
        return id;
    }

    public void tick(Map m){
        a.tick();
    }

    public void render(Renderer r){
        a.render(x, y, r);
    }
}

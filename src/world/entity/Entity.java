package world.entity;

import animation.Animation;
import animation.AnimationSet;
import object.Pair;
import render.Renderer;
import render.Tileset;
import tool.ID;
import world.list.Listable;
import world.map.Map;

import java.awt.*;



public abstract class Entity implements Listable {

    private int id, hp, max_hp;
    protected int x, y, speed;
    protected AnimationSet animations;
    protected Animation current;
    private Rectangle area;
    protected ENTITY_FACING facing;

    public Entity(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        id = ID.generate();
        this.speed = speed;
        facing = ENTITY_FACING.DOWN;
        animations = new AnimationSet();
    }

    public Entity(Entity e) {
        this.x = e.x;
        this.y = e.y;
        id = e.id;
        this.speed = e.speed;
        facing = e.facing;
        animations = e.animations;
    }

    public void setMaxHP(int v){
        max_hp=v;
        hp=v;
    }

    public float getHPRelation(){
        return (float)hp/(float)max_hp;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public int size(){
        return animations.size();
    }

    public void addA(Animation a){
        animations.add(a);
    }

    public void selectA(String id){
        if(current==null || (current!=null && !current.is(id))){
            current = animations.get(id);
        }
    }

    public void setID(int id){
        this.id = id;
    }

    public int id(){
        return id;
    }

    public Animation getA(String id){
        return animations.get(id);
    }

    public int speed(){
        return speed;
    }

    public void move(int tx, int ty){
        /*
        if(tx!=0 && ty!=0){
            x+=tx/2;
            y+=ty/2;
        } else {
            x+=tx;
            y+=ty;
        }*/
        x+=tx;
        y+=ty;
    }

    public void setPos(Pair p){
        this.x = Tileset.DEFAULT_TILE_SIZE*(int)p.x;
        this.y = Tileset.DEFAULT_TILE_SIZE*(int)p.y;
    }

    protected void tickArea(){
        area = new Rectangle(x-animations.size()/2, y-animations.size()/2, animations.size(), animations.size());
    }

    public void damage(int i){
        hp-=i;
    }

    public boolean collidesWith(Rectangle r){
        return area.intersects(r);
    }

    public abstract void tick(Map m);

    public abstract void render(Renderer r);
}

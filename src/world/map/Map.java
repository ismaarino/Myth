package world.map;

import main.Game;
import object.Pair;
import render.Renderer;
import render.Tileset;
import world.Camera;
import world.entity.Entity;
import world.entity.Player;
import world.list.Listable;
import world.list.WorldSet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Map {

    private String name;
    private int id,tileset,width,height;
    private List<MapLayer> layers;
    protected Rectangle[][] solids;
    protected Pair origin;


    public Map(String name, int id, int w, int h, int tileset) {
        this.name = name;
        this.id = id;
        width = w;
        height = h;
        layers = new ArrayList<MapLayer>();
        solids = new Rectangle[width][height];
        this.tileset = tileset;
        origin = new Pair(0,0);
    }

    public void add(int x, int y, Rectangle solid){
        solids[x][y]=solid;
    }

    public void add(MapLayer l){
        layers.add(l);
    }

    public Pair origin(){
        return origin;
    }

    public String name(){
        return name;
    }

    public int id(){
        return id;
    }

    public boolean solid(int x, int y){
        return solids[x][y]!=null;
    }

    public boolean collides(Entity e, int tx, int ty){
        Rectangle aux = new Rectangle(e.x()+tx, e.y()+ty,Tileset.DEFAULT_TILE_SIZE,Tileset.DEFAULT_TILE_SIZE);
        int[] bounds = Renderer.minAndMaxTile(e,width, height);
        int y=bounds[1];
        int x=bounds[0];
        boolean c = false;
        while(!c && y<bounds[3]){
            while(!c && x<bounds[2]){
                if(solids[x][y]!=null && solids[x][y].intersects(aux)){
                    c = true;
                }
                x++;
            }
            x=0;
            y++;
        }
        return c;
    }

    public void tick(){

    }

    public void render(Camera c, Renderer r){

        int[] bounds = Renderer.minAndMaxTile(c,width, height);
        layers.forEach(l-> {
                for (int y = bounds[1]; y < bounds[3]; y++) {
                    for (int x = bounds[0]; x < bounds[2]; x++) {
                        r.renderTile(tileset, l.getTile(x,y), x * Tileset.DEFAULT_TILE_SIZE, y * Tileset.DEFAULT_TILE_SIZE);
                    }
                }
            });

    }


}

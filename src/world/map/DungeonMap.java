package world.map;

import main.Game;
import object.DungeonTiles;
import object.Pair;
import tool.Maths;
import render.Renderer;
import tool.Test;
import world.Camera;
import world.Laberinth;
import world.entity.Player;

import java.awt.*;

public class DungeonMap extends Map {


    private int tile_width, width, height;

    private Laberinth lab;

    public DungeonMap(String name, int w, int h, int tile_width, int tileset_id){
        super(name, Maths.rand(9999999), w*tile_width, h*tile_width, tileset_id);
        width = w*tile_width;
        height = h*tile_width;
        this.tile_width = tile_width;
        lab = new Laberinth(w, h);
        Test.printLaberinth(lab);
        super.origin = start();
        DungeonTiles.setTiles(lab, tile_width, this);
    }

    public Pair start(){
        Pair p = new Pair(lab.start());
        p.x*=tile_width;
        p.y*=tile_width;
        return p;
    }

    public Pair exit(){
        Pair p = new Pair(lab.exit());
        p.x*=tile_width;
        p.y*=tile_width;
        return p;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Camera c, Renderer r) {
        super.render(c,r);
    }
}

package world;

import main.Game;
import render.Renderer;
import world.entity.Entity;
import world.entity.Player;
import world.list.WorldSet;
import world.map.Map;

public class World {

    private Map map;
    private WorldSet entities;
    private Camera camera;
    private Player player;

    public World(Map m, Player p, WorldSet entities, Camera c){
        map = m;
        player = p;
        p.setPos(map.origin());
        c.set(p);
        this.entities = entities;
        camera = c;
    }

    public World(World w){
        map = w.map;
        player = new Player(w.player);
        player.setPos(map.origin());
        camera = new Camera(w.camera);
        camera.set(player);
        this.entities = new WorldSet(w.entities);

    }

    public void add(Entity e){
        entities.add(e);
    }

    public void tick() {
        map.tick();
        entities.tick(map);
        camera.tick();
    }

    public void render(Renderer r) {
        map.render(camera,r);
        entities.render(r);
    }
}

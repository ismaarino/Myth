package world.entity;

import animation.Animation;
import animation.PlayerTexture;
import gui.GUI;
import main.Game;
import object.KeyInput;
import render.Renderer;
import world.list.Listable;
import world.map.Map;

public class Player extends Entity{

    public Player(int x, int y, int speed, String texture_pack_name) {
        super(x, y, speed);
        super.setMaxHP(100);
        animations = PlayerTexture.fromFile("player");
    }

    public Player(Player p) {
        super(p);
    }

    private void tickMove(Map m){
        if(Game.keyboard.pressed()) {
            int tx=0, ty=0;
            if (Game.keyboard.pressed(KeyInput.UP)) {
                ty=-speed;
                selectA("walk_up");
                facing=ENTITY_FACING.UP;
            } else if (Game.keyboard.pressed(KeyInput.DOWN)) {
                ty=speed;
                selectA("walk_down");
                facing=ENTITY_FACING.DOWN;
            }
            if (Game.keyboard.pressed(KeyInput.LEFT)) {
                tx=-speed;
                selectA("walk_left");
                facing=ENTITY_FACING.LEFT;
            } else if (Game.keyboard.pressed(KeyInput.RIGHT)) {
                tx=+speed;
                selectA("walk_right");
                facing=ENTITY_FACING.RIGHT;
            }
            if(!m.collides(this, tx, ty)) {
                move(tx, ty);
            } else if(!m.collides(this, 0, ty)){
                move(0, ty);
            } else if(!m.collides(this, tx, 0)){
                move(tx, 0);
            }
        } else {
            if(facing.equals(ENTITY_FACING.UP)){
                selectA("idle_up");
            } else if(facing.equals(ENTITY_FACING.RIGHT)){
                selectA("idle_right");
            } else if(facing.equals(ENTITY_FACING.DOWN)){
                selectA("idle_down");
            } else if(facing.equals(ENTITY_FACING.LEFT)){
                selectA("idle_left");
            }
        }
    }

    @Override
    public Listable copy() {
        return new Player(this);
    }

    public void tick(Map m){
        tickMove(m);
        current.tick();
        tickArea();
    }

    public void render(Renderer r){
        current.render(x,y,r);
        GUI.drawHealhBar(this,r);
    }

}

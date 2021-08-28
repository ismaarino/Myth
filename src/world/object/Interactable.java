package world.object;

import animation.Animation;
import animation.AnimationSet;
import render.Renderer;
import world.map.Map;

public abstract class Interactable extends Object{

    public Interactable(int x, int y, AnimationSet a){
        super(x, y, a);
    }

    public void tick(Map m){
        super.tick(m);
    }

    public void render(Renderer r){
        super.render(r);
    }

    public abstract void interact();


}

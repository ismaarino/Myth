package world.list;

import render.Renderer;
import world.map.Map;

public interface Listable {

    public void setID(int id);

    public int id();

    public Listable copy();

    public void tick(Map m);

    public void render(Renderer r);
}

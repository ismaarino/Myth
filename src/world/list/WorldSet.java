package world.list;

import render.Renderer;
import tool.ID;
import world.map.Map;

public class WorldSet {

    private Node list;

    public WorldSet(){
        list = null;
    }

    public WorldSet(WorldSet ws){
        Node n = ws.list;
        while(n!=null){
            add(n.object().copy());
            n = n.next();
        }
    }

    public void add(Listable e){
        while(exists(e.id())){
            e.setID(ID.generate());
        }
        list = new Node(list, e);
    }

    public void delete(int id){
        Node last = list, n = list;
        boolean f = false;
        while(!f && n!=null){
            if(n.object().id()==id){
                last.next(n.next());
                f = true;
            }
            last = n;
            n = n.next();
        }
    }

    public boolean exists(int id){
        return get(id)!=null;
    }

    public Listable get(int id){
        Node n = list;
        while(n!=null && n.object().id()!=id){
            n = n.next();
        }
        if(n!=null && n.object().id()==id){
            return n.object();
        } else {
            return null;
        }
    }

    public void tick(Map m){
        Node n = list;
        while(n!=null){
            n.object().tick(m);
            n = n.next();
        }
    }

    public void render(Renderer r){
        Node n = list;
        while(n!=null){
            n.object().render(r);
            n = n.next();
        }
    }
}

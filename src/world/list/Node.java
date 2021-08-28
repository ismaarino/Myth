package world.list;

import world.entity.Entity;

public class Node {

    private Listable e;
    private Node next;

    public Node(Node n, Listable e){
        next = n;
        this.e = e;
    }

    public Node next(){
        return next;
    }

    public void next(Node n){
        next = n;
    }

    public Listable object(){
        return e;
    }
}

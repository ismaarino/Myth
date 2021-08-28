package object;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Store<T> {

    private List<String> ids;
    private List<T> buffer;

    public Store(){
        ids = new ArrayList<String>();
        buffer = new ArrayList<T>();
    }

    public void add(String id, T object){
        ids.add(id);
        buffer.add(object);
    }

    public T get(String id){
        return buffer.get(ids.indexOf(id));
    }

    public void destroy(){
        ids.clear();
        buffer.clear();
    }
}

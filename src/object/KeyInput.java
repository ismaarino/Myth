package object;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyInput extends KeyAdapter {

    public final static String UP = "w";
    public final static String RIGHT = "d";
    public final static String DOWN = "s";
    public final static String LEFT = "a";

    private Set<String> pressed;

    public KeyInput(){
        pressed = new HashSet<String>();
    }

    public void keyPressed(KeyEvent e){
        pressed.add(String.valueOf(e.getKeyChar()).toLowerCase());
    }

    public void keyReleased(KeyEvent e){
        String key = String.valueOf(e.getKeyChar());
        pressed = pressed.stream().filter(k->!k.equals(key)).collect(Collectors.toSet());
    }

    public boolean pressed(String key){
        return pressed.stream().filter(k->k.equals(key)).count()>0;
    }
    public boolean pressed(){
        return pressed.size()>0;
    }

}

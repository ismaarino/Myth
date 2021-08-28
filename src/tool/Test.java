package tool;

import main.Game;
import world.Camera;
import world.Laberinth;
import object.Pair;
import world.World;
import world.entity.Player;
import world.list.WorldSet;
import world.map.DungeonMap;
import world.map.Map;

public class Test {

    public static void p(String s){
        System.out.print(s);
    }

    public static void printLaberinth(Laberinth l){
        for(int y=0;y<l.height();y++){
            p("\n");
            for(int x=0;x<l.width();x++){
                if(l.start().same(new Pair(x, y))){
                    p("S");
                } else if (l.exit().same(new Pair(x, y))) {
                    p("X");
                } else {
                    p((l.get(x, y) ? "·" : "#"));
                }
            }
        }
    }

    public static World genWorld(Player p, Camera c){
        Map m = new DungeonMap("",35,25,3,1);
        WorldSet entities = new WorldSet();
        return new World(m, p, entities,c);
    }
}

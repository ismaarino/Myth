package world;

import object.Pair;
import tool.Maths;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Laberinth {

    private int width, height, diagonal;
    private boolean mesh[][];
    private Pair start, exit;

    public Laberinth(int w, int h){
        width = w;
        height = h;
        diagonal = (int)Math.sqrt(Math.pow(width, 2)+Math.pow(height, 2));
        mesh = new boolean[width][height];
        start = new Pair(2+Maths.rand(width-4), 2+Maths.rand(height-4));
        mesh[start.x()][start.y()] = true;
        int intents = 0;
        do{
            exit = new Pair(2+Maths.rand(width-4), 2+Maths.rand(height-4));
            intents++;
        }while(start.same(exit) || (start.distance(exit)<(diagonal/2f) && intents<10));
        mesh[exit.x()][exit.y()] = true;
        makeRouteBetweenStartAndExit();
        randomizeLeft();
    }

    private void randomizeLeft(){
        boolean last = false;
        for(int y=2;y<height-4;y++){
            for(int x=2;x<width-4;x++){
                if((Math.random()>0.7 && last) || Math.random()>0.8) {
                    mesh[x][y] = last = true;
                }
            }
        }
        for(int y=2;y<height-4;y++){
            for(int x=2;x<width-4;x++){
                if(mesh[x][y] && !mesh[x-1][y] && !mesh[x+1][y] && !mesh[x][y-1] && !mesh[x][y+1]) {
                    mesh[x][y] = false;
                }
            }
        }
    }

    private void makeRouteBetweenStartAndExit(){
        List<Pair> poss = new ArrayList<Pair>();
        Pair aux;
        for(int u=0;u<(int)((float)(width+height)*0.1);u++){
            do {
                aux = new Pair(2 + Maths.rand(width - 4), 2 + Maths.rand(height - 4));
            }while(aux.same(start) || aux.same(exit) || (poss.size()>0 && aux.distance(poss.get(poss.size()-1))>(int)((float)diagonal*0.3f)));
            poss.add(aux);
        }
        poss.add(new Pair(exit));
        Iterator<Pair> it = poss.iterator();
        Pair pos = new Pair(start), to;
        boolean c;
        int cs, v=0;
        while(it.hasNext()) {
            to = it.next();
            while (!pos.same(to)) {
                c = false;
                cs = 0;
                if (Math.random() > 0.5) {
                    if (pos.x > to.x) {
                        cs = 1 + Maths.rand(pos.x() - to.x());
                        v = -1;
                        c = true;
                    } else if (pos.x < to.x) {
                        cs = 1 + Maths.rand(to.x() - pos.x());
                        v = 1;
                        c = true;
                    }
                    for (int i = 0; i < cs; i++) {
                        pos.x += v;
                        mesh[pos.x()][pos.y()] = true;
                    }
                }
                if (!c) {
                    if (pos.y > to.y) {
                        cs = 1 + Maths.rand(pos.y() - to.y());
                        v = -1;
                    } else if (pos.y < to.y) {
                        cs = 1 + Maths.rand(to.y() - pos.y());
                        v = 1;
                    }
                    for (int i = 0; i < cs; i++) {
                        pos.y += v;
                        mesh[pos.x()][pos.y()] = true;
                    }
                }
            }
        }
    }

    public boolean get(int x, int y){
        return mesh[x][y];
    }

    public Pair start(){
        return start;
    }

    public Pair exit(){
        return exit;
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }
}

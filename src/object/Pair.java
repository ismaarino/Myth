package object;

public class Pair {

    public float x, y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Pair(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Pair(Pair p){
        this.x = p.x;
        this.y = p.y;
    }

    public boolean same(Pair p){
        return p.x==x && p.y==y;
    }

    public int x(){
        return (int)x;
    }

    public int y(){
        return (int)y;
    }

    public int distance(Pair p){
        return (int)Math.sqrt(Math.pow(x-p.x,2)+Math.pow(y-p.y,2));
    }
}

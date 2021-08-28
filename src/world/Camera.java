package world;

import main.Game;
import object.Pair;
import world.entity.Entity;

public class Camera {

    private final static float SMOOTHNESS = 0.1f;

    private Entity following;
    public int XOffset, YOffset;
    private int xaux, yaux, preXOffset, preYOffset;
    private double sincount;

    public Camera(Entity e){
        set(e);
        sincount = 0;
    }

    public Camera(Camera c){
        following = c.following;
        XOffset = c.XOffset;
        YOffset = c.YOffset;
        xaux = c.xaux;
        yaux = c.yaux;
        preXOffset = c.preXOffset;
        preYOffset = c.preYOffset;
        sincount = c.sincount;
    }

    public void set(Entity e){
        following = e;
        xaux = (int)((((float)Game.width/2f/Game.SCALE))-2*((float)following.size()/Game.SCALE));
        yaux = (int)((((float)Game.height/2f/Game.SCALE))-2*((float)following.size()/Game.SCALE));
        preXOffset = xaux-following.x();
        preYOffset = yaux-following.y();
    }

    public Pair getPos(){
        return new Pair(following.x(),following.y());
    }

    public void tick(){
        if(following!=null) {

            float distanceX = new Pair(preXOffset,preYOffset).distance(new Pair(xaux-following.x(),preYOffset));
            float distanceY = new Pair(preXOffset,preYOffset).distance(new Pair(preXOffset,yaux-following.y()));
            float speedX = 1+(int)(Math.pow(SMOOTHNESS*(distanceX/2f),3f));
            float speedY = 1+(int)(Math.pow(SMOOTHNESS*(distanceY/2f),3f));
            if(preXOffset-speedX>xaux-following.x()){
                preXOffset-=speedX;
            } else if(preXOffset+speedX<xaux-following.x()){
                preXOffset+=speedX;
            }

            if(preYOffset-speedY>yaux-following.y()){
                preYOffset-=speedY;
            } else if(preYOffset+speedY<yaux-following.y()){
                preYOffset+=speedY;
            }
            XOffset=preXOffset;
            YOffset=preYOffset;
            /*YOffset += 4f*Math.sin(sincount);
            if(sincount+0.06<Math.PI*2f) {
                sincount += 0.06;
            } else {
                sincount=0;
            }*/
            //YOffset = yaux-following.y();
            /*
            XOffset = xaux-following.x();
            YOffset = yaux-following.y();*/
        }
    }
}

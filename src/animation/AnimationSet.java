package animation;

import java.util.Arrays;
import java.util.Iterator;

public class AnimationSet implements Iterable{

    private Animation[] anims;

    public AnimationSet(){
        anims = new Animation[0];
    }

    public void add(Animation a){
        Animation[] a2 = new Animation[anims.length+1];
        for(int i=0;i<anims.length;i++){
            a2[i]=anims[i];
        }
        a2[anims.length]=a;
        anims=a2;
    }

    public int size(){
        if(anims.length>0) {
            return anims[0].size();
        } else {
            return -1;
        }
    }

    public Animation get(String id){
        boolean f = false;
        int i=0;
        while(!f && i<anims.length){
            if(anims[i].is(id)){
                f = true;
            } else {
                i++;
            }
        }
        if(f){
            anims[i].reset();
            return anims[i];
        } else {
            return null;
        }
    }

    @Override
    public Iterator iterator() {
        return Arrays.asList(anims).iterator();
    }
}

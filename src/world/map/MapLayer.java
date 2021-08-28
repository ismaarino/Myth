package world.map;

public class MapLayer {

    private int[][] tiles;

    public MapLayer(int width, int height){
        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y]=-1;
            }
        }
    }

    public void setTile(int x, int y, int id){
        tiles[x][y]=id;
    }

    public int getTile(int x, int y){
        return tiles[x][y];
    }
}

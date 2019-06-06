package Katzengold;

import java.lang.reflect.Array;
import java.security.PublicKey;

public class Map {

    public int[][] blub = {{1,1},{2,3}};

    public int MapSize = 20;

    public Map(){
        for (LevelTile[] Row: MapObjects ) {
            for(LevelTile Tile: Row){

            }
        }
    }

    public LevelTile[][] MapObjects = new LevelTile[MapSize][MapSize];

    public LevelTile GetObject (int x, int y){
        return MapObjects[x][y];
    }
}

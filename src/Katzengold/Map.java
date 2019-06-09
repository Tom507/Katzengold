package Katzengold;

import jserver.XSendAdapterEN;

public class Map {

    public static int mapSize = 20;

    public static Collidable[][] Level = new Collidable[mapSize][mapSize];

    //public LevelTile[] tiles = {};

    private MapObject tree = new MapObject("resources/Tree.png", true);
    private MapObject sea = new MapObject("resources/Water.png", true);
    private MapObject coin = new MapObject("resources/Coin.png", false);
    private MapObject key = new MapObject("resources/Key.png", false);


    public void generateMap(XSendAdapterEN xs){

        for(int i=0; i<20; i++){ //level füllen random
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            Level[rand1][rand2] = new Collidable(rand1, rand2, tree.directory, xs, tree.collidable);
        }
        for(int i=0; i<10; i++){ //level füllen random
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            Level[rand1][rand2] = new Collidable(rand1, rand2, sea.directory, xs, sea.collidable);
        }
        for(int i=0; i<3; i++){ //level füllen random
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            Level[rand1][rand2] = new Coin(rand1, rand2, coin.directory, xs, coin.collidable, 1);
        }
        for(int i=0; i<2; i++){ //level füllen random
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            Level[rand1][rand2] = new Key(rand1, rand2, key.directory, xs, key.collidable, 1);
        }
    }

    public LevelTile[][] MapObjects = new LevelTile[mapSize][mapSize];

    public LevelTile GetObject (int x, int y){
        return MapObjects[x][y];
    }
}

package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Map {

    public int mapSize;
    public TileDictionary tD;

    public Collidable[][] level;

    public Map(int size, TileDictionary td){
        this.mapSize = size;
        this.level = new Collidable[mapSize][mapSize];
        this.tD = td;
    }

    private MapObject tree = new MapObject("resources/Tree.png", true);
    private MapObject sea = new MapObject("resources/Water.png", true);
    private MapObject coin = new MapObject("resources/Coin.png", false);
    private MapObject key = new MapObject("resources/Key.png", false);


    public void generateMap(){

        for(int i=0; i<20; i++){ //trees
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            this.level[rand1][rand2] = new Collidable(rand1, rand2, tD.getById(5).directory, tree.collidable);
        }
        for(int i=0; i<10; i++){ //Water
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            this.level[rand1][rand2] = new Collidable(rand1, rand2, tD.getById(9).directory, sea.collidable);
        }
        for(int i=0; i<3; i++){ //Coin
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            this.level[rand1][rand2] = new Coin(rand1, rand2, coin.directory, coin.collidable, 1);
        }
        for(int i=0; i<2; i++){ //Keys
            int rand1 = (int)(Math.random()*20);
            int rand2 = (int)(Math.random()*20);
            this.level[rand1][rand2] = new Key(rand1, rand2, key.directory, key.collidable, 1);
        }
    }

    public void display (XSendAdapterEN xs){
        XSendAdapterEN xsend = xs;
        Board board = xsend.getBoard();
        Plotter plotter = board.getPlotter();

        xsend.size(this.level.length, this.level.length);

        for(Collidable[] row : this.level){
            for(Collidable tile : row){
                if(tile != null) {
                    System.out.println("x : " + tile.x + " y : " + tile.y+ " -- image : " + tile.imageDirectory);
                    xs.getBoard().getSymbol(tile.x, tile.y).setImage(tile.imageDirectory, plotter);

                    if(tile instanceof Movable){ //tile.getClass().isAssignableFrom(Movable.class
                        //System.out.println( "movable : " + tile.imageDirectory);
                        ((Movable)tile).setXsend(xs);
                    }
                }
            }
        }
        xs.getBoard().redrawSymbols();
    }


    public int[][] toIntMap (){
        int[][] out = new int[this.level.length][this.level.length];
        for(Collidable[] row : this.level){
            for(Collidable tile : row){
                if(tile != null) {
                    System.out.println("x : " + tile.x + " y : " + tile.y);
                    //out[tile.x][tile.y] = tilesDictionary.elements();
                    out[tile.x][tile.y] = this.tD.getByDirectory(tile.imageDirectory).id;
                }
            }
        }
        return out;
    }

    public Collidable[][] setIntMap (int[][] intMap){
        Collidable[][] outLevel = new Collidable[intMap.length][intMap.length];
        for(int y = 0; y < intMap.length; y++){
            int[] row = intMap[y];
            for(int x =0; x < row.length; x++){
                int tileId = intMap[x][y];

                switch (tileId){
                    case 0:
                        outLevel[x][y] = new Collidable(x,y,tD.getById(0).directory, false);
                    break;
                    case 1:
                        outLevel[x][y] = new CatPlayer(x,y,tD.getById(tileId).directory, this);
                        break;
                    case 3:
                        outLevel[x][y] = new Coin(x,y,tD.getById(tileId).directory, false, 1);
                        break;
                    case 4:
                        outLevel[x][y] = new Key(x,y,tD.getById(tileId).directory, false, 0001);
                        break;
                    case 6:
                        outLevel[x][y] = new Merchant(x,y,tD.getById(tileId).directory, true, 0001, 5);
                        break;
                    case 7:
                        outLevel[x][y] = new Merchant(x,y,tD.getById(tileId).directory, true, 0002, 20);
                        break;
                    default:
                        outLevel[x][y] = new Collidable(x,y,tD.getById(tileId).directory, true);
                }
            }
        }
        this.mapSize = outLevel.length;
        this.level = outLevel;
        return outLevel;
    }
    /*
    private String dictFindKey (Dictionary d, String value){
        for(int i=0; i< d.size(); i++){
            if()
        }
    }*/
}

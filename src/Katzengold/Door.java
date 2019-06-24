package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class Door extends Collidable{

    protected XSendAdapterEN xsend;
    protected Board board;
    protected Plotter plotter;
    protected Map map;

    public boolean locked;
    public int requiredKey;

    public String OpenedImage = "resources/DoorOpen.png";

    public Door(){}
    public Door(int x, int y, String ImageDirectory, boolean collidable,boolean locked){

        this.x = x;
        this.y = y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;

        this.locked = locked;
    }
    public Door(int x, int y, String ImageDirectory, boolean collidable,boolean locked, int requiredKey){

        this.x = x;
        this.y = y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;

        this.locked = locked;
        this.requiredKey = requiredKey;
    }

    public void open (XSendAdapterEN xsend){
        this.xsend = xsend;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        collidable = false;
    }

}

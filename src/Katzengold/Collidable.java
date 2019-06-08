package Katzengold;

import jserver.XSendAdapterEN;

public class Collidable extends LevelTile {

    public boolean collidable = true;

    public Collidable(){};

    public Collidable (int X, int Y, String ImageDirectory, XSendAdapterEN xS, boolean collidable){
        xsend = xS;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
        board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
    }





    public Collidable(boolean Collidable){
        this.collidable = Collidable;
    }
}

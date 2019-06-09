package Katzengold;

import jserver.XSendAdapterEN;

public class Key extends Collidable {
    public int keycode;

    public Key (int X, int Y, String ImageDirectory, XSendAdapterEN xS, boolean collidable, int keycode) {
        xsend = xS;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        this.keycode = keycode;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
        board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
    }

    public Key ( int keycode){
        this.keycode = keycode;
    }
}

package Katzengold;

import jserver.XSendAdapterEN;

public class Coin extends Collidable {
    public int value;

    public Coin (int X, int Y, String ImageDirectory, XSendAdapterEN xS, boolean collidable, int Value) {
        xsend = xS;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        this.value = Value;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
        board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
    }
}

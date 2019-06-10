package Katzengold;

import jserver.XSendAdapterEN;

public class Coin extends Collidable {
    public int value;

    public Coin (int X, int Y, String ImageDirectory, boolean collidable, int Value) {

        this.value = Value;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
    }
}

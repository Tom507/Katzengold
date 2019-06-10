package Katzengold;

import jserver.XSendAdapterEN;

public class Collidable extends LevelTile {

    public boolean collidable = true;

    public Collidable(){};

    public Collidable (int X, int Y, String ImageDirectory, boolean collidable){

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
    }
}

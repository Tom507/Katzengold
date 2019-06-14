package Katzengold;


public class Key extends Collidable {
    public int keycode;

    public Key (int X, int Y, String ImageDirectory, boolean collidable, int keycode) {

        this.keycode = keycode;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
    }

    public Key ( int keycode){
        this.keycode = keycode;
    }
}

package Katzengold;

public class Merchant extends Collidable{

    public Key key ;
    public int price;

    public Merchant (int X, int Y, String ImageDirectory, boolean collidable,int keycode,int price){

        this.key = new Key(keycode);
        this.price = price;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
    }
}

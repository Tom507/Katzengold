package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CatPlayer extends Movable {

    public int money =0;
    public int keys = 0;

    private String[] catImages = {"resources/cat/Left.png","resources/cat/Up.png","resources/cat/Right.png","resources/cat/Down.png"};

    public CatPlayer(){};

    public CatPlayer (int X, int Y, String ImageDirectory, XSendAdapterEN xS) {

        this.xsend =xS;

        this.money = 0;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.up = catImages[1];
        this.left = catImages[0];
        this.right = catImages[2];
        this.down = catImages[3];
    }

    @Override
    public boolean move(int direction){
        boolean supRet = super.move(direction);

        //String key = Character.toString((char) 9919 );
        if( Map.Level[x][y] != null) {
            if (Map.Level[x][y].getClass() == Coin.class) {
                System.out.println("Coin");
                this.money += ((Coin) Map.Level[x][y]).value;
                Map.Level[x][y] = null;
                //xsend.statusText("KatzenGold : \uD834" + 0x26BF );
            }
        }
        if( Map.Level[x][y] != null) {
            if (Map.Level[x][y].getClass() == Key.class) {
                System.out.println("Key");
                this.keys += 1;
                Map.Level[x][y] = null;
            }
        }


        if(Map.Level[x][y] != null){
            if(Map.Level[x][y+1].getClass() == Merchant.class){

            }
        }

        xsend.statusText( "KatzenGold : " + money  + " Keys : " + keys);
        return supRet;
    }


}

package Katzengold;

import jserver.XSendAdapterEN;

public class CatPlayer extends Movable {

    public int money =0;
    public int keys = 0;

    private String[] catImages = {"resources/cat/Left.png","resources/cat/Up.png","resources/cat/Right.png","resources/cat/Down.png"};

    public CatPlayer(){};

    public CatPlayer (int X, int Y, String ImageDirectory, Map m) {

        this.map = m;
        this.x = X;
        this.y = Y;
        map.level[x][y] = this;

        this.money = 0;

        this.imageDirectory = ImageDirectory;

        this.left = catImages[0];
        this.up = catImages[1];
        this.right = catImages[2];
        this.down = catImages[3];
    }

    @Override
    public boolean move(int direction){
        boolean supRet = super.move(direction);

        //String key = Character.toString((char) 9919 );
        if( map.level[x][y] != null) {
            if (map.level[x][y].getClass() == Coin.class) {
                System.out.println("Coin");
                this.money += ((Coin) map.level[x][y]).value;
                map.level[x][y] = null;
                //xsend.statusText("KatzenGold : \uD834" + 0x26BF );
            }
        }
        if( map.level[x][y] != null) {
            if (map.level[x][y].getClass() == Key.class) {
                System.out.println("Key");
                this.keys += 1;
                map.level[x][y] = null;
            }
        }


        if(map.level[x][y] != null){
            if(map.level[x][y+1].getClass() == Merchant.class){

            }
        }

        xsend.statusText( "KatzenGold : " + money  + " Keys : " + keys);
        return supRet;
    }


}

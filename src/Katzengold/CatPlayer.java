package Katzengold;

import jserver.XSendAdapterEN;

import javax.swing.*;

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

        if( map.level[x][y] != null) {  // ######## Coin
            if (map.level[x][y].getClass() == Coin.class) {
                System.out.println("Coin");
                this.money += ((Coin) map.level[x][y]).value;
                map.level[x][y] = null;
                //xsend.statusText("KatzenGold : \uD834" + 0x26BF );
            }
        }

        if( map.level[x][y] != null) { // ######## Key
            if (map.level[x][y].getClass() == Key.class) {
                System.out.println("Key");
                this.keys += 1;
                map.level[x][y] = null;
            }
        }


        if(map.level[x][y+1].getClass() == Merchant.class){
            if (direction == 2){
                int reply = JOptionPane.showConfirmDialog(null, ((Merchant)map.level[x][y+1]).price + " coins?", "Coins!", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Coins!");
                    this.money = this.money-((Merchant)map.level[x][y+1]).price;
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nö!");
                }
            }
        }


        xsend.statusText( "KatzenGold : " + money  + " Keys : " + keys);
        return supRet;
    }


}

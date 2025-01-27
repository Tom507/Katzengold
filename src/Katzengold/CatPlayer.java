package Katzengold;

import jserver.XSendAdapterEN;

import javax.swing.*;
import java.util.ArrayList;

public class CatPlayer extends Movable {

    public int money =0;
    public ArrayList<Key> keys = new ArrayList<>();

    public Bridge bridge;

    private String[] catImages = {"resources/cat/Left.png","resources/cat/Up.png","resources/cat/Right.png","resources/cat/Down.png"};

    public CatPlayer(){};

    public CatPlayer (int X, int Y, String ImageDirectory, Map m) {

        GameLogic.Player = this;
        this.map = m;
        this.x = X;
        this.y = Y;
        //map.level[x][y] = this;

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

        // ######## Coin
        if( map.level[x][y] != null) {
            if (map.level[x][y].getClass() == Coin.class) {
                System.out.println("Coin");
                money += ((Coin) map.level[x][y]).value;
                map.level[x][y] = null;
                //xsend.statusText("KatzenGold : \uD834" + 0x26BF );
            }
        }

        // ######## Key
        if( map.level[x][y] != null) {
            if (map.level[x][y].getClass() == Key.class) {
                System.out.println("Key");
                keys.add( (Key) map.level[x][y]);
                map.level[x][y] = null;
            }
        }


        // ######## Bridge
        if( map.level[x][y] != null) {
            if (map.level[x][y].getClass() == Bridge.class) {
                bridge = (Bridge) map.level[x][y];
                bridge.onBridge(direction, xsend);
            }
        }
        if(bridge != null && map.level[x][y] == null){
            bridge.offBridge(direction);
            bridge = null;
        }

        // ######## Merchant
        if(map.level[x][y+1]!= null) {
            if (map.level[x][y + 1].getClass() == Merchant.class) {
                Merchant merchant = (Merchant) map.level[x][y + 1];
                if (direction == 2) {
                    int reply = JOptionPane.showConfirmDialog(null, ((Merchant) map.level[x][y + 1]).price + " coins?", "Coins!", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION && money > merchant.price) {
                        JOptionPane.showMessageDialog(null, "Dankeeeee!");
                        money = money - merchant.price;
                        keys.add(merchant.key);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nope!");
                    }
                }
            }
        }

        xsend.statusText( "KatzenGold : " + money  + " Keys : " + keys.size());
        return supRet;
    }
}

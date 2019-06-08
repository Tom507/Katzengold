package Katzengold;

import jserver.XSendAdapterEN;

public class CatPlayer extends Movable {

    public int money =0;

    public CatPlayer(){};

    public CatPlayer (int X, int Y, String ImageDirectory, XSendAdapterEN xS, int Money) {
        xsend = xS;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        this.money = Money;
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
    }

    @Override
    public boolean move(int direction){
        boolean supRet = super.move(direction);

        xsend.statusText("" + money);
        if (Map.Level[x][y].getClass() == Coin.class){
            money += ((Coin)Map.Level[x][y]).value;

            xsend.statusText("" + money);
        }

        return supRet;
    }
}

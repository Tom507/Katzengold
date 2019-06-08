package Katzengold;

import jserver.XSendAdapterEN;

public class Movable extends Collidable{

    public Movable (){};
    public Movable (int X, int Y, String ImageDirectory, XSendAdapterEN xS){
        xsend = xS;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
    }

    public boolean move (int direction){

        //remove old image
        board.getSymbol(this.x, this.y).setImage(this.emptyImage, plotter);
        switch (direction){
            case 1:
                if (colCase(x-1,y)) {

                    this.x -= 1;
                }
                break;
            case 2:
                if (colCase(x,y+1)) {
                    this.y += 1;
                }
                break;
            case 3:
                if (colCase(x+1,y)) {
                    this.x += 1;
                }
                break;
            case 4:
                if (colCase(x,y-1)) {
                    this.y -= 1;
                }
                break;
        }


        //set image
        board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
        //check for collision

        return true;
    }

    private boolean colCase(int X,int Y){
        if(Map.Level[X][Y] != null){
            return !Map.Level[X][Y].collidable;
        } else
            return true;
    }
}

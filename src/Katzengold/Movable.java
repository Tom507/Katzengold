package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class Movable extends Collidable{

    protected XSendAdapterEN xsend;
    protected Board board;
    protected Plotter plotter;
    protected Map map;

    protected String up = "resources/Up.png";
    protected String down = "resources/Down.png";
    protected String left = "resources/Left.png";
    protected String right = "resources/Right.png";

    public Movable (){}
    public Movable (int X, int Y, String ImageDirectory, Map m){
        this.map = m;
        this.x = X;
        this.y = Y;
        //map.level[x][y] = this;

        this.imageDirectory = ImageDirectory;
    }

    public void setXsend(XSendAdapterEN xsend) {
        this.xsend = xsend;
        this.board = this.xsend.getBoard();
        this.plotter = this.board.getPlotter();
    }

    public boolean move (int direction){

        xsend.symbolSize2(this.x, this.y, 0.5);

        switch (direction){
            case 1: //Left
                if (colCase(x-1,y)) {
                    this.board.getSymbol(this.x, this.y).setImage(this.emptyImage, this.plotter);

                    this.x -= 1;
                }
                setImage(this.left);
                break;

            case 2://Up
                if (colCase(x,y+1)) {
                    this.board.getSymbol(this.x, this.y).setImage(this.emptyImage, this.plotter);

                    this.y += 1;
                }
                setImage(this.up);
                break;

            case 3://Right
                if (colCase(x+1,y)) {
                    this.board.getSymbol(this.x, this.y).setImage(this.emptyImage, this.plotter);

                    this.x += 1;
                }
                setImage(this.right);
                break;

            case 4://Down
                if (colCase(x,y-1)) {
                    this.board.getSymbol(this.x, this.y).setImage(this.emptyImage, this.plotter);
                    this.y -= 1;
                }
                setImage(this.down);
                break;
        }

        xsend.symbolSize2(this.x, this.y, 1);
        board.redrawSymbols();
        //set image
        //board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
        //check for collision

        return true;
    }

    private boolean colCase(int X,int Y){
        if(this.map.level[X][Y] != null){
            return !this.map.level[X][Y].collidable;
        } else
            return true;
    }

    public void setImage (String image){
        this.board.getSymbol(this.x, this.y).setImage(image, this.plotter);
    }
}

package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class Interactable extends Collidable {

    protected XSendAdapterEN xsend;
    protected Board board;
    protected Plotter plotter;
    protected Map map;

    public void setImage (String image){
        this.board.getSymbol(this.x, this.y).setImage(image, this.plotter);
    }

    public void interactionInit (XSendAdapterEN xsend){
        this.xsend = xsend;
        board = xsend.getBoard();
        plotter = board.getPlotter();
    }
}

package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class Bridge extends Collidable{

    protected XSendAdapterEN xsend;
    protected Board board;
    protected Plotter plotter;
    protected Map map;

    public String bridgeCatUp = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatDown = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatRight = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatLeft = "resources/bridge/BridgeCatUp.png";

    public Bridge (int X, int Y, String ImageDirectory, boolean collidable){

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;
    }

    public void onBridge( int direction, XSendAdapterEN xsend ){
        this.xsend = xsend;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        switch(direction){
            case 1 :
                setImage(bridgeCatLeft);
                break;
            case 2 :
                setImage(bridgeCatUp);
                break;
            case 3 :
                setImage(bridgeCatRight);
                break;
            case 4 :
                setImage(bridgeCatDown);
                break;

        }
    }
    public void offBridge(int direction){
        setImage(this.imageDirectory);
    }

    public void setImage (String image){
        this.board.getSymbol(this.x, this.y).setImage(image, this.plotter);
    }
}

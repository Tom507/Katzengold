package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

import javax.swing.*;

public class Bridge extends Collidable{

    protected XSendAdapterEN xsend;
    protected Board board;
    protected Plotter plotter;
    protected Map map;

    public String bridgeCatUp = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatDown = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatRight = "resources/bridge/BridgeCatUp.png";
    public String bridgeCatLeft = "resources/bridge/BridgeCatUp.png";

    public String[][] bridges =
            {
                    {"Bridge_1_1.png","Bridge_1_2.png","Bridge_1_3.png","Bridge_1_4.png"},
                    {"Bridge_2_1.png","Bridge_2_2.png","Bridge_2_3.png","Bridge_2_4.png"},
                    {"Bridge_3_1.png","Bridge_3_2.png","Bridge_3_3.png","Bridge_3_4.png"},
                    {"Bridge_4_1.png","Bridge_4_2.png","Bridge_4_3.png","Bridge_4_4.png"}
            };

    protected int bridgeRotation;

    public Bridge (int X, int Y, String ImageDirectory, boolean collidable){

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        this.collidable = collidable;

        if(this.imageDirectory.contains("Left"))
            bridgeRotation = 1;
        if(this.imageDirectory.contains("Up"))
            bridgeRotation = 2;
        if(this.imageDirectory.contains("Right"))
            bridgeRotation = 3;
        if(this.imageDirectory.contains("Down"))
            bridgeRotation = 4;
    }

    public void onBridge( int direction, XSendAdapterEN xsend ){
        this.xsend = xsend;
        board = xsend.getBoard();
        plotter = board.getPlotter();

        //JOptionPane.showMessageDialog(null,"resources/bridge/"+bridges[bridgeRotation - 1 ][direction - 1 ]);
        setImage( "resources/bridge/"+bridges[bridgeRotation - 1 ][direction - 1 ]);

    }
    public void offBridge(int direction){
        setImage(this.imageDirectory);
    }

    public void setImage (String image){
        this.board.getSymbol(this.x, this.y).setImage(image, this.plotter);
    }
}

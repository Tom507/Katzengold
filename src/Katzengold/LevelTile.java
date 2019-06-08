package Katzengold;

import java.awt.*;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class LevelTile {
    protected XSendAdapterEN xsend;
    protected Board board ;
    protected Plotter plotter ;

    public String imageDirectory = "";
    public String emptyImage = "resources/Empty.png";
    public int x;
    public int y;

    public LevelTile(){};

    public LevelTile (int X, int Y, String ImageDirectory, XSendAdapterEN xS){
          xsend = xS;
          board = xsend.getBoard();
          plotter = board.getPlotter();

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
        board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
    }

}

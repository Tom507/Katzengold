package Katzengold;

import java.awt.*;

import jserver.Board;
import jserver.XSendAdapterEN;
import plotter.Plotter;

public class LevelTile {

    public String imageDirectory = "";
    public String emptyImage = "resources/Empty.png";
    public int x;
    public int y;

    public LevelTile(){}

    public LevelTile (int X, int Y, String ImageDirectory){

        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
    }

}

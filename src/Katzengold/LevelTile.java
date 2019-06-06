package Katzengold;

import java.awt.*;

public class LevelTile {
    public String imageDirectory = "";
    public int x;
    public int y;

    public LevelTile(){};

    public LevelTile (int X, int Y, String ImageDirectory){
        this.x = X;
        this.y = Y;
        this.imageDirectory = ImageDirectory;
    }

}

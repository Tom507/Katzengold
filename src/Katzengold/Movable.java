package Katzengold;

import jserver.XSendAdapterEN;

public class Movable extends Collidable{

    protected String up = "resources/Up.png";
    protected String down = "resources/Down.png";
    protected String left = "resources/Left.png";
    protected String right = "resources/Right.png";

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
        switch (direction){
            case 1:
                if (colCase(x-1,y)) {
                    board.getSymbol(this.x, this.y).setImage(this.emptyImage, plotter);

                    this.x -= 1;
                }
                setImage(this.left);

                break;
            case 2:
                if (colCase(x,y+1)) {
                    board.getSymbol(this.x, this.y).setImage(this.emptyImage, plotter);

                    this.y += 1;
                }
                setImage(this.up);

                break;
            case 3:
                if (colCase(x+1,y)) {
                    board.getSymbol(this.x, this.y).setImage(this.emptyImage, plotter);

                    this.x += 1;
                }
                setImage(this.right);

                break;
            case 4:
                if (colCase(x,y-1)) {
                    board.getSymbol(this.x, this.y).setImage(this.emptyImage, plotter);
                    this.y -= 1;
                }
                setImage(this.down);

                break;
        }


        //set image
        //board.getSymbol(this.x, this.y).setImage(this.imageDirectory, plotter);
        //check for collision

        return true;
    }

    private boolean colCase(int X,int Y){
        if(Map.Level[X][Y] != null){
            return !Map.Level[X][Y].collidable;
        } else
            return true;
    }

    public void setImage (String image){
        board.getSymbol(this.x, this.y).setImage(image, plotter);
    }
}

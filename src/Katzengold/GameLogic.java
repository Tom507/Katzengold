package Katzengold;

import jserver.Board;
import jserver.Symbol;
import jserver.XSendAdapter;
import jserver.XSendAdapterEN;
import plotter.Plotter;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class GameLogic implements KeyListener{
    private Board board;
    private Plotter plotter;
    private XSendAdapterEN xsend = new XSendAdapterEN();

    public int boardSize = 20;

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        //gameLogic.Gl();
        gameLogic.setup();
    }

    public void setup(){
        board = xsend.getBoard();
        plotter = board.getPlotter();
        xsend.forms("none");
        xsend.size(boardSize,boardSize);

        board.getGraphic().addKeyListener(this);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board.setSize(screenSize.height-100, screenSize.height-100);

        Gl();
    }

    public CatPlayer player;

    public void Gl(){

        Map map = new Map();

        map.generateMap(xsend);


        player = new CatPlayer(8,8,"resources/Arrow.png", xsend);
        System.out.println("test: " + (int)(Math.random()*20));

        board.redrawSymbols();
    }

    //TODO:
    /*

        - Verkäufer
    - Tür
    - Gegner
    - Level
    - Truhe
     */

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 10){
            //Gl();
            board.getSymbol(player.x, player.y).reset();
            board.redrawSymbols();
        }
        if (e.getKeyCode() > 36 && e.getKeyCode() < 41){
            player.move(e.getKeyCode()-36);

            board.redrawSymbols();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import org.w3c.dom.events.Event;
import plotter.Plotter;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    public CatPlayer Player;
    public MapGen MG;
    public TileDictionary TD;

    public void Gl(){

        MG = new MapGen(false);
        TD = new TileDictionary();

        Map map = new Map(20, TD);

        map.generateMap();

        Player = new CatPlayer(8,8,TD.getById(1).directory, map);
        map.display(xsend);

        board.redrawSymbols();
        
    }

    /*TODO:

        - Verkäufer
    - Tür
    - Gegner
    - level
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
            board.getSymbol(Player.x, Player.y).reset();
            board.redrawSymbols();
        }
        if (e.getKeyCode() > 36 && e.getKeyCode() < 41){
            Player.move(e.getKeyCode()-36);

            board.redrawSymbols();
        }
        if(e.getKeyCode() == 77){
            MG.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package Katzengold;

import jserver.Board;
import jserver.XSendAdapterEN;
import org.w3c.dom.events.Event;
import plotter.Plotter;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameLogic implements KeyListener, ActionListener {
    public static Board board;
    private Plotter plotter;
    public static XSendAdapterEN xsend = new XSendAdapterEN();

    private JButton load = new JButton("load");

    public int boardSize = 20;

    public static void main(String[] args) throws InterruptedException {
        GameLogic gameLogic = new GameLogic();
        //gameLogic.Gl();
        gameLogic.setup();
        Thread.sleep(1000);
        gameLogic.Gl();
    }

    public void setup(){
        this.board = this.xsend.getBoard();
        this.plotter = this.board.getPlotter();
        this.xsend.forms("none");
        this.xsend.size(this.boardSize,this.boardSize);

        this.board.getPlotter().addKeyListener(this);
        plotter.setFocusable(true);
        plotter.requestFocusInWindow();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.board.setSize(screenSize.height-100, screenSize.height-100);

        this.board.getGraphic().addEastComponent(this.load);
        this.load.addActionListener(this);


        MG = new MapGen(false);
        TD = new TileDictionary();

        map = new Map(20, TD);
    }

    public static CatPlayer Player;
    public MapGen MG;
    public TileDictionary TD;
    public Map map;

    public void Gl(){



        //map.generateMap();

        //Player = new CatPlayer(8,8,TD.getById(1).directory, map);
        //Player.setXsend(xsend);
        //map.display(xsend);

        loadMap("resources/maps/test.txt");

        //map.display(xsend);

        //board.redrawSymbols();
        
    }

    /*TODO:

        - Verkäufer
    - Tür
    - Gegner
    - level
    - Truhe
     */
    public void loadMap(String map){
        this.MG.load(xsend, map);
        plotter.setFocusable(true);
        plotter.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 10){
            //Gl();
            board.getSymbol(Player.x, Player.y).reset();
        }
        if (e.getKeyCode() > 36 && e.getKeyCode() < 41){
            XSendAdapterEN x1 = this.xsend;
            XSendAdapterEN x2= Player.xsend;
            Player.move(e.getKeyCode()-36);

        }
        if(e.getKeyCode() == 77){
            MG.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.MG.load(xsend);
        plotter.setFocusable(true);
        plotter.requestFocusInWindow();
    }
}

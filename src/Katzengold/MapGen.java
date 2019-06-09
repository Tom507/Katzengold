package Katzengold;

import jserver.*;
import plotter.Graphic;
import plotter.Plotter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class MapGen implements BoardClickListener, KeyListener {

    private XSendAdapterEN xsend = new XSendAdapterEN();
    private Board board;
    private Graphic graphic;

    private XSendAdapterEN selectXsend = new XSendAdapterEN();
    private Board selectBoard;
    private Graphic selectGraphic;
    private Plotter selectPlotter;

    private Dictionary tileDirectories = new Hashtable();
    private int selected = 0;
    private int size = 0;


    public static void main(String[] args) {
        MapGen MP = new MapGen();
        MP.setup();
        MP.generate();
    }

    public void setup (){
        board = xsend.getBoard();
        graphic = board.getGraphic();
        board.addClickListener(this::boardClick);

        size = Integer.parseInt( JOptionPane.showInputDialog(null, "MapSize: "));
        xsend.size(size,size);



        selectBoard = selectXsend.getBoard();
        selectGraphic = selectBoard.getGraphic();
        selectPlotter = selectBoard.getPlotter();

        selectBoard.addClickListener(this::selectBoardClick);

        tileDirectories.put("0", "resources/Empty.png");
        tileDirectories.put("1","resources/cat/Down.png");
        tileDirectories.put("5","resources/Tree.png");

        tileDirectories.put("10", "resources/Water.png");
        tileDirectories.put("11", "resources/river/RiverCorner.png");
        tileDirectories.put("12", "resources/river/RiverStraight.png");

        selectXsend.forms("none");
        selectXsend.size(10,10);
        for(int i =0; i< 20; i++){
            try {
                selectBoard.getSymbol(i).setImage(tileDirectories.get(""+i).toString(), selectPlotter);
                System.out.println(tileDirectories.get(""+i));
            }catch (Exception e){}
        }
        System.out.println( "0 : "+ tileDirectories.get("1"));
        selectBoard.redrawSymbols();
    }

    public void generate (){

    }

    @Override
    public void boardClick(BoardClickEvent e) {
        System.out.println(e.getX()+ e.getY()*size); // an board stelle schreiben
        // in array speichern
    }

    public void selectBoardClick(BoardClickEvent e) {
        selected = e.getX()+ e.getY()*size; //tile selektieren
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 82){ // R

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}

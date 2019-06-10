package Katzengold;

import jserver.*;
import plotter.Graphic;
import plotter.Plotter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class MapGen implements BoardClickListener, KeyListener, ActionListener {

    public MapGen(boolean startMapGen){
        tiles();

        if(startMapGen){
            this.start();
        }
    }

    //#######> Boards Setup
    private XSendAdapterEN xsend;
    private Board board;
    private Graphic graphic;

    private XSendAdapterEN selectXsend;
    private Board selectBoard;
    private Graphic selectGraphic;
    private Plotter selectPlotter;


    //#######> Variables Setup
    public Dictionary tileDirectories = new Hashtable();
    private int selected = 0;
    private int size = 0;
    public int[][] map;


    //#######> Interface Setup
    private JButton save = new JButton("save");
    private JButton load = new JButton("load");

    //####################> Main ##############################################
    /*
    private static MapGen MP;
    public static void main(String[] args) {
        MP = new MapGen();
        MP.start();
    }*/
    private void tiles(){
        //####################> Tiles ##############################################
        tileDirectories.put("0", "resources/Empty.png");
        tileDirectories.put("1","resources/cat/Down.png");
        tileDirectories.put("5","resources/Tree.png");
        tileDirectories.put("9", "resources/Water.png");

        tileDirectories.put("10", "resources/river/RiverCornerLeft.png");
        tileDirectories.put("11", "resources/river/RiverCornerUp.png");
        tileDirectories.put("12", "resources/river/RiverCornerRight.png");
        tileDirectories.put("13", "resources/river/RiverCornerDown.png");

        tileDirectories.put("14", "resources/river/RiverStraightLeft.png");
        tileDirectories.put("15", "resources/river/RiverStraightUp.png");
        tileDirectories.put("16", "resources/river/RiverStraightRight.png");
        tileDirectories.put("17", "resources/river/RiverStraightDown.png");
    }

    public void start(){

        //####################> Board #############################################
        xsend  = new XSendAdapterEN();
        board = xsend.getBoard();
        graphic = board.getGraphic();
        board.addClickListener(this::boardClick);
        graphic.addKeyListener(this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        board.setSize(screenSize.height-100, screenSize.height-100);

        size = Integer.parseInt( JOptionPane.showInputDialog(null, "MapSize: "));
        xsend.size(size,size);
        xsend.statusText("Map Editor");
        //inteface
        graphic.addEastComponent(save);
        graphic.addEastComponent(load);
        save.addActionListener(this );
        load.addActionListener(this::load);


        //####################> Select Board #######################################
        selectXsend  = new XSendAdapterEN();
        selectBoard = selectXsend.getBoard();
        selectGraphic = selectBoard.getGraphic();
        selectPlotter = selectBoard.getPlotter();

        selectBoard.addClickListener(this::selectBoardClick);

        selectXsend.forms("none");
        selectXsend.size(10,10);
        selectXsend.statusText("Tile Selektor");
        for(int i =0; i< 20; i++){ //Selection Board Setup
            if(i > 10){
                try {
                    selectBoard.getSymbol(((i-9)/4) + 10).setImage(tileDirectories.get("" + i).toString(), selectPlotter);
                    System.out.println(tileDirectories.get("" + i));
                } catch (Exception e) {
                }
                i+=3;
            }else {
                try {
                    selectBoard.getSymbol(i).setImage(tileDirectories.get("" + i).toString(), selectPlotter);
                    System.out.println(tileDirectories.get("" + i));
                } catch (Exception e) {
                }
            }
        }
        System.out.println( "0 : "+ tileDirectories.get("1"));
        selectBoard.redrawSymbols();

        //####################> Map #######################################
        map = new int[size][size];
    }

    //####################> Read + Write ##############################################
    public  static int[][] read (String filename){ //TODO
        return new  int[2][3];
    }

    public static void writeMap (String filename, int[][]map) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));

        for(int[] row : map){
            for (int i = 0; i < row.length; i++) {
                // Maybe:
                outputWriter.write(String.format("%02d", row[i]) +" ");
                // Or:
                //outputWriter.write(Integer.toString(row[i]));
            }
            outputWriter.newLine();
        }

        outputWriter.flush();
        outputWriter.close();
    }

    //####################> Click Listener #######################################
    @Override
    public void boardClick(BoardClickEvent e) {
        System.out.println(e.getX()+ e.getY()*size + ""); // an board stelle schreiben
        int selection = selected;
        if(selected >= 10)
            selection =  selected + rotationOffset;

        board.getSymbol(e.getX(), e.getY()).setImage(tileDirectories.get(""+(selection)).toString(), board.getPlotter());
        xsend.form2(e.getX(),e.getY(),"none");
        // in array speichern
        map[e.getX()][e.getY()] = selection;
        board.redrawSymbols();
    }

    public void selectBoardClick(BoardClickEvent e) {
        if(e.getY() > 0){
            selected = e.getX() * 4 + e.getY()*10; //tile selektieren
        }else {
            selected = e.getX()+ e.getY()*10; //tile selektieren
        }

        System.out.println(selected);
    }



    //####################> Rotation + Keys #######################################
    private int rotationOffset = 0;
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() > 36 && e.getKeyCode() < 41){ // Pfeiltasten
            rotationOffset = e.getKeyCode() - 37;
        }

        if(e.getKeyCode() == 82){ // R
            if(rotationOffset < 3){
                rotationOffset++;
            }else {
                rotationOffset = 0;
            }
        }
        System.out.println("rotation : " + rotationOffset);
        if(e.getKeyCode() == 10){ // Enter
            for (int[] row: map) {
                String r ="";
                for(int tile : row){
                    r = r + tile+ ", ";
                }
                System.out.println(r);
            }
        }
    }

    //####################> Buttons #######################################
    public  void pauseButton(){

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) { // Save
        try {
            writeMap("resources/maps/"+ JOptionPane.showInputDialog(null, "save as :"), map);
            JOptionPane.showMessageDialog(null,"Save Complete");
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Save Failed");
        }

    }
    public void load (ActionEvent e) { // Load
        try {
            map = read(JOptionPane.showInputDialog(null, "enter File name :"));
        }catch (Exception ex){}
        JOptionPane.showMessageDialog(null, "loaded");
    }
}

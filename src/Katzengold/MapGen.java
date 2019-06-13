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
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MapGen implements BoardClickListener, KeyListener, ActionListener {

    public MapGen(boolean startMapGen){
        tileDictionary = new TileDictionary();

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
    public TileDictionary tileDictionary;
    private int selected = 0;
    private int size = 0;
    public int[][] intMap;


    //#######> Interface Setup
    private JButton save = new JButton("save");
    private JButton load = new JButton("load");

    //####################> Main ##############################################


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
                    selectBoard.getSymbol(((i-9)/4) + 10).setImage(tileDictionary.getById(i).directory, selectPlotter);
                    System.out.println(tileDictionary.getById(i).toString());
                } catch (Exception e) {
                }
                i+=3;
            }else {
                try {
                    selectBoard.getSymbol(i).setImage(tileDictionary.getById(i).directory, selectPlotter);
                    System.out.println(tileDictionary.getById(i).directory);
                } catch (Exception e) {
                }
            }
        }
        System.out.println( "0 : "+ tileDictionary.getById(1));
        selectBoard.redrawSymbols();

        //####################> Map #######################################

        intMap = new int[size][size];
    }

    //####################> Read + Write ##############################################
    public  static int[][] read (String filename) throws Exception{

        Scanner sc = new Scanner(new File(filename));

        int lineCount = sc.nextInt();

        int[][] returnArray = new int[lineCount][lineCount];

        for(int y = 0; y < lineCount; y++){
            for (int x = 0; x < lineCount; x++){
                returnArray[y][x] = sc.nextInt();
            }
        }

        /*
        BufferedReader br = new BufferedReader(new FileReader(filename));

        int[][] returnArray = new int[(int)br.lines().count()][(int) br.lines().count()];

        String st;
        for (int y =0; (st = br.readLine()) != null; y++) {

            for(int x=0; x < st.length(); x++){

                String num = "";
                for(int i=0; st.charAt(i) != ' '; i++ ){
                    num += st.charAt(i);
                }
                returnArray[x][y] = Integer.parseInt(num);
            }
        }

         */
        return returnArray;
    }

    public static void writeIntMap(String filename, int[][]map) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(filename));

        outputWriter.write(map.length + "");
        outputWriter.newLine();

        for(int[] row : map){
            for (int i = 0; i < row.length; i++) {
                outputWriter.write(String.format("%02d", row[i]) +" ");
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

        board.getSymbol(e.getX(), e.getY()).setImage(tileDictionary.getById(selection).directory, board.getPlotter());
        xsend.form2(e.getX(),e.getY(),"none");
        // in array speichern
        intMap[e.getX()][e.getY()] = selection;
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
            for (int[] row: intMap) {
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
            writeIntMap("resources/maps/"+ JOptionPane.showInputDialog(null, "save as :"), intMap);
            JOptionPane.showMessageDialog(null,"Save Complete");
        }catch (IOException ex){
            JOptionPane.showMessageDialog(null,"Save Failed");
        }
    }

    public void load (ActionEvent e) { // Load
        try {
            //intMap = read("resources/maps/" + JOptionPane.showInputDialog(null, "enter map File name :"));
            intMap = read("resources/maps/test.txt");
            Map m = new Map(intMap.length, tileDictionary);
            m.setIntMap(intMap);
            xsend.forms("none");
            m.display(xsend);
            intMap = m.toIntMap();
            System.out.println( Arrays.toString(intMap));

            JOptionPane.showMessageDialog(null, "loaded");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "load Failed : "+ ex.getMessage());
            ex.printStackTrace();
        }

    }
}

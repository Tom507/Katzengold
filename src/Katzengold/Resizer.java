package Katzengold;

import jserver.Board;
import jserver.XSendAdapter;
import plotter.Plotter;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

class Resizer implements ComponentListener {
    

    private XSendAdapter xsend = new XSendAdapter();
    private Board board;
    private Plotter plotter;

    public Dimension dimension;

    public void componentResized(ComponentEvent e) {
        dimension = e.getComponent().getBounds().getSize();
        board.setSize(dimension.height, dimension.height);
    }


    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}

}
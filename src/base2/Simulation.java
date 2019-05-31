package base2;

import javax.swing.*;

import jserver.*;
import plotter.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.jar.JarEntry;

/**
 * The main class for the simulation game. Current Version:
 * <ul>
 * <li>create board</li>
 * <li>add some money</li>
 * <li>create one piece and move it for some time</li>
 * <ul>
 * 
 * @version 0.2 19.04.29
 * @author Euler
 *
 */
public class Simulation implements ActionListener, BoardClickListener {
	private XSendAdapter xsend = new XSendAdapter();
	private int size = 30;
	private Money[] moneys;
	private Merchant[] merchants;
	private Questioner questioner = new Questioner();
	private Board board;
	private Graphic graphic;


	private int rundenZaeler = 0;
	private JLabel Zaehler = new JLabel();

	private boolean pause = false; //#####################
	private JButton Pause = new JButton("pause"); //################
	private JTextArea MerchInfo = new JTextArea();

	public static void main(String[] args) {
		Simulation simu = new Simulation();
		simu.start();
	}

	private void start() {

		Pause.addActionListener(this);
		Zaehler.setText("Runde : "+ rundenZaeler);

		board = xsend.getBoard();
		graphic = board.getGraphic();
		graphic.addEastComponent(Pause);
		graphic.addEastComponent(Zaehler);
		graphic.addSouthComponent(MerchInfo);

		board.addClickListener(this);


		System.out.println("Spiel-Simulator V0.2 April 2019");
		System.out.println("Plotter: " + Plotter.getVersion());
		xsend.groesse(getSize(), getSize());
		generateMerchants();
		generateMoney();

		Merchant nemo = new Merchant("nemo", this);

		while (true) {
			if(!pause){ //###################
				gameLogic();
			}
			Sleep.sleep(200);
		}

	}

	private void gameLogic(){
		String infoText = "";
		for (Merchant merchant : merchants) {
			xsend.farbe2(merchant.getX(), merchant.getY(), Symbol.getBoSColor().getRGB());
			merchant.move();
			checkMoney(merchant);
			xsend.farbe2(merchant.getX(), merchant.getY(), merchant.getColor());
			xsend.statusText(merchant.toString());

			infoText += merchant.toString()+ "\r\n";
			Sleep.sleep(200);

		}
		MerchInfo.setText(infoText);

		rundenZaeler ++; //###################
		Zaehler.setText("Runde : "+ rundenZaeler);

	}

	@Override
	public void boardClick(BoardClickEvent e) {
		pause = true;
		JOptionPane.showMessageDialog(null, "X : " + e.getX() + " -- Y : "+ e.getY(), "Klick Position :", JOptionPane.INFORMATION_MESSAGE);
		pause = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) { //###################
		pause = ! pause;
	}

	private void generateMerchants() {
		merchants = new Merchant[5];
		for (int i = 0; i < merchants.length; i++) {
			merchants[i] = new Merchant(Utils.getRandomName(), this);
		}
	}

	private void checkMoney(Merchant merchant) {
		for (Money money : moneys) {
			if (merchant.getX() == money.getX() && merchant.getY() == money.getY()) {
				if(questioner.askQuestion( ) ) {
					merchant.addMoney(money.getValue());
				}
			}
		}
	}

	private void generateMoney() {
		moneys = new Money[getSize() / 2];
		for (int i = 0; i < getSize(); i += 2) {
			moneys[i / 2] = new Money(i, i, i);
			xsend.farbe2(i, i, XSendAdapter.GOLD);
		}
		JOptionPane.showMessageDialog(null, "total amount " + Money.getTotalValue(), "Money, money", JOptionPane.INFORMATION_MESSAGE);
	}

	public int getSize() {
		return size;
	}
}

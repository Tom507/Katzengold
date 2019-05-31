package base2;

import javax.swing.JOptionPane;

import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Plotter;
import plotter.Sleep;

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
public class Simulation {
	private XSendAdapter xsend = new XSendAdapter();
	private int size = 30;
	private Money[] moneys;
	private Merchant[] merchants;
	private Questioner questioner = new Questioner();

	public static void main(String[] args) {
		Simulation simu = new Simulation();
		simu.start();
	}

	private void start() {
		System.out.println("Spiel-Simulator V0.2 April 2019");
		System.out.println("Plotter: " + Plotter.getVersion());
		xsend.groesse(getSize(), getSize());
		generateMerchants();
		generateMoney();

		for (int t = 0; t < 100; t++) {
			for (Merchant merchant : merchants) {
				xsend.farbe2(merchant.getX(), merchant.getY(), Symbol.getBoSColor().getRGB());
				merchant.move();
				checkMoney(merchant);
				xsend.farbe2(merchant.getX(), merchant.getY(), merchant.getColor());
				xsend.statusText(merchant.toString());
				Sleep.sleep(200);
			}
		}

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

package base;

import jserver.Symbol;
import jserver.XSendAdapter;
import plotter.Plotter;
import plotter.Sleep;

/**
 * The main class for the simulation game. Current Version: 
 * <ul>
 * <li>create board  </li>
 * <li>add some money  </li>
 * <li>create one piece and move it for some time </li>
 * <ul>
 * 
 * @version 0.1 19.03.11
 * @author Euler
 *
 */
public class Simulation {
	private XSendAdapter xsend = new XSendAdapter();
	private int size = 30;
	private Money[] moneys;

	public static void main(String[] args) {
		Simulation simu = new Simulation();
		simu.start();
	}

	private void start() {
		System.out.println("Spiel-Simulator V0.1 Maerz 2019");
		System.out.println("Plotter: " + Plotter.getVersion());
		xsend.groesse(size, size);
		generateMoney();

		Merchant felix = new Merchant("Felix");
		for (int t = 0; t < 100; t++) { 	// Simulation Loop

			xsend.farbe2(felix.getX(), felix.getY(), Symbol.getBoSColor().getRGB());
			felix.move();
			checkMoney(felix);
			xsend.farbe2(felix.getX(), felix.getY(), felix.getColor());
			xsend.statusText(felix.toString() + "     Money on field : " + (MoneyOnField() - felix.getMoney())); 	// hier wird oben der Status text geschrieben

			Sleep.sleep(200);
		}

	}

	private void checkMoney(Merchant merchant) {
		for (Money money : moneys) {
			if (merchant.getX() == money.getX() && merchant.getY() == money.getY()) {
				merchant.addMoney(money.getValue());

				System.out.println(merchant.toString());
			}
		}
	}

	private void generateMoney() {
		moneys = new Money[size / 2];
		for (int i = 0; i < size; i += 2) {
			moneys[i / 2] = new Money(i, i, i);
			xsend.farbe2(i, i, XSendAdapter.GOLD);
		}

	}

	private double MoneyOnField(){ // Zählt das Geld auf dem Feld
		int allTheMoneys =0;
		for (Money m: moneys) { // ForeEach schleife läuft einmal durch die Liste moneys, in m steht bei jedem duchlauf das entsprechende listenelement
			// equivalent mit for(int i=0; i < moneys.length; i++) {m = moneys[i]} -- aber bei foreach hat man keinen zugiff auf den index
			allTheMoneys += m.getValue();
		}
		return allTheMoneys;
	}

}


package base2;

import java.util.Random;

public class Merchant {
	static Random random = new Random();
	private int x = 12;
	private int y = 15;
	private int exp = 0;
	private int money = 0;
	private int color = 0xff;
	private String name;
	private Simulation simulation;
	
	public Merchant(String name, Simulation simulation) {
		super();
		this.name = name;
		this.simulation = simulation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getColor() {
		return color;
	}

	void move( ) {
		x += random.nextInt(3) - 1;
		y += random.nextInt(3) - 1;		
		x = check( x );
		y = check( y );
		++exp;
	}

	private int check(int v) {
		if( v < 0 ) return 0;
		if( v >= simulation.getSize() ) return simulation.getSize() - 1;
		return v;
	}

	@Override
	public String toString() {
		return "Merchant [x=" + x + ", y=" + y + ", exp=" + exp + ", money=" + money + ", name=" + name + "]";
	}

	public void addMoney(int value) {
		money += value;		
	}
}

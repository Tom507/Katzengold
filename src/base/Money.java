package base;

public class Money {
	private int value;
	private int x;
	private int y;

	public static int moneyOnFiel = 0;
	
	public Money(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;

		moneyOnFiel += value;
	}
	
	public int getValue() {
		return value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}



}

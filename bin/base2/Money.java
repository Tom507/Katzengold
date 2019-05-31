package base2;

public class Money {
	private int value;
	private int x;
	private int y;
	private static int totalValue;
	
	public Money(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		totalValue += value;
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

	public static int getTotalValue() {
		return totalValue;
	}



}

package Model;

public class Food implements IFood{

	private String name;
	private String brand;
	private int price;
	private String exactPrice;
	

	public Food(String name, String brand, int price, String exactPrice) {
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.exactPrice = exactPrice;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	public String getPrice() {
		return IFood.formatPrice(price);
	}

	public String getExactPrice() {
		return exactPrice;
	}

	@Override
	public String toString() {
	return getName() + " " + getBrand() + " " + getPrice()+ " " + getExactPrice();
	}

}

package Model;

public interface IFood {

	public static String formatPrice(int price) {
		return String.format("%.2f",(double)price/100);
	}
	
	public String getName();

	public String getBrand();

	public String getPrice();

	public String getExactPrice();

}

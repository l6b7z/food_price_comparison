package Model;

public interface IExactPriceFromQuantity {

	public static String getExactPrice(int price, int quantity) {
		if(quantity == 0) {
			return "0.00 per unit";
		}
		return IFood.formatPrice(price / quantity) + " per unit";
	}

}

package Model;

public interface IExactPriceFromMass {


	
	public static String getExactPrice(int price,int mass, String measurementUnit){
		
		int pricePer100Units = (int) (Math.round((double)price/mass*100));
		
		return  IFood.formatPrice(pricePer100Units)+ "/100" + measurementUnit;
		
	}
}

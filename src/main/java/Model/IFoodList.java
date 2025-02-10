package Model;

import java.util.ArrayList;

public interface IFoodList {
	public String[] getCollumnNames();
	public ArrayList<Food> getFoodList();
	
	public void addFood(String name, String brand,int price,String exactPrice);
	
	public void removeFood(int index);
	public void removeAll();
	
	public String getFood(int index);
	public boolean checkIfFoodExists(int index);
}

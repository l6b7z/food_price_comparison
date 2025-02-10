package Model;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class FoodList implements IFoodList {

	private final String[] collumnNames = { "Name", "Brand", "Price", "Exact Price" };

	private ArrayList<Food> foodList;
	private IDataStoring dataStoring;

	public FoodList(IDataStoring dataStoring) {
		this.dataStoring = dataStoring;
		foodList = dataStoring.loadData();
	}

	@Override
	public String[] getCollumnNames() {
		return collumnNames;
	}

	@Override
	public ArrayList<Food> getFoodList() {
		return new ArrayList<>(foodList);
	}

	@Override
	public void addFood(String name, String brand, int price, String exactPrice) {
		foodList.add(new Food(name, brand, price, exactPrice));
		sortAndStoreFoodList();
	}

	@Override
	public void removeFood(int index) {
		if (!checkIfFoodExists(index)) {
			System.out.println("index doesn't exist");
			return;
		}
		foodList.remove(index);
		dataStoring.saveData(foodList);
	}

	@Override
	public void removeAll() {
		foodList = new ArrayList<>();
		dataStoring.saveData(foodList);
	}

	
	@Override
	public String getFood(int index) {
		if(!checkIfFoodExists(index)) {
			System.out.println("index doesn't exist");
			return "";
		}
		return foodList.get(index).toString();
	}
	
	
	@Override
	public boolean checkIfFoodExists(int index) {
		return index >= 0 && index < foodList.size();
	}



	public ArrayList<Food> sortList() {
		return foodList.stream().sorted((e, e2) -> e.getName().compareTo(e2.getName()))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void sortAndStoreFoodList() {
		foodList = sortList();
		dataStoring.saveData(foodList);
	}

}
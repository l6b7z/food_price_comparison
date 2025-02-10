package Model;

import java.util.ArrayList;

public interface IDataStoring {
	public ArrayList<Food> loadData();
	public void saveData(ArrayList<Food> foodList);
}

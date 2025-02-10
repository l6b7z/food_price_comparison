package Model;

import java.util.ArrayList;

public class DummyDataStoring implements IDataStoring{
	
	//class for testing purposes
	
	@Override
	public ArrayList<Food> loadData() {
		return new ArrayList<Food>();
		
	}

	@Override
	public void saveData(ArrayList<Food> foodList) {
	}

}

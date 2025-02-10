package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DataStoringWithGson implements IDataStoring {

	public ArrayList<Food> loadData() {
		ArrayList<Food> foodList = null;
		try {
			Reader reader = Files.newBufferedReader(Paths.get("Output.json"));

			foodList = new Gson().fromJson(reader, new TypeToken<ArrayList<Food>>() {
			}.getType());

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return foodList;

	}

	public void saveData(ArrayList<Food> foodList) {

		try (Writer writer = new FileWriter("Output.json")) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(foodList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

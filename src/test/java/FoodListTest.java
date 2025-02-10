import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.FoodList;
import Model.DummyDataStoring;

class FoodListTest {
	@Test
	void testAddingFoodItem() {
		FoodList f = new FoodList(new DummyDataStoring());
		f.addFood("name", "brand", 1, "1");
		assertEquals(f.getFoodList().size(), 1);

	}

	@Test
	void FoodListAutomaticSorting() {

		FoodList f = new FoodList(new DummyDataStoring());
		f.addFood("fff", "brand", 1, "1");
		f.addFood("abc", "brand", 1, "1");
		f.addFood("zzz", "brand", 1, "1");
		f.addFood("aaa", "brand", 1, "1");

		assertEquals(f.getFoodList().get(0).getName(), "aaa");
	}

	@Test
	void testElementExistsByIndexMethod() {
		FoodList f = new FoodList(new DummyDataStoring());
		f.addFood("name", "brand", 1, "1");
		assertEquals(false, f.checkIfFoodExists(2));

	}

	@Test
	void RemovingIndexOutOfBoundsDoesntThrowException() {
		FoodList f = new FoodList(new DummyDataStoring());
		f.addFood("name", "brand", 1, "1");
		assertDoesNotThrow(() -> f.removeFood(999));

	}

}

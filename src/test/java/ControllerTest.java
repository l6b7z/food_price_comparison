import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Controller.Controller;
import Model.DummyDataStoring;
import Model.IExactPriceFromMass;
import Model.IExactPriceFromQuantity;

class ControllerTest {

	@Test
	void addBasicFood() {

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFood("AAA", "brand", 100);

		assertEquals(controller.checkIfFoodExists(0), true);
	}

	@Test
	void addFoodWithQuantity() {

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFoodWithQuantity("AAA", "brand", 100, 2);

		assertEquals(controller.checkIfFoodExists(0), true);
	}

	@Test
	void addFoodWithMass() {

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFoodWithMass("AAA", "brand", 1234, 200, "g");

		assertEquals(controller.checkIfFoodExists(0), true);
	}

	@Test
	void checkBasicFoodExactPrice() {
		// tableContent[y][x] x = 3 element price | x = 4 element exact price
		// should be exactly the same in basic food

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFood("AAA", "brand", 100);

		String[][] tableContent = controller.getTableContent();

		assertEquals(tableContent[0][3], tableContent[0][4]);
	}

	@Test
	void checkFoodWithQuantityExactPrice() {
		// tableContent[y][x] x = 4 element exact price
		
		int price = 1000;
		int quantity = 2;

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFoodWithQuantity("AAA", "brand", price, quantity);

		String[][] tableContent = controller.getTableContent();

		String formattedExactPrice = IExactPriceFromQuantity.getExactPrice(price, quantity);

		assertEquals(tableContent[0][4], formattedExactPrice);
	}

	@Test
	void checkFoodWithMassExactPrice() {
		// tableContent[y][x] x = 4 element exact price
		
		int price = 1000;
		int mass = 200;
		String measurementUnit = "g";

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFoodWithMass("AAA", "brand", price, mass, measurementUnit);

		String[][] tableContent = controller.getTableContent();

		String formattedExactPrice = IExactPriceFromMass.getExactPrice(price, mass, measurementUnit);

		assertEquals(tableContent[0][4], formattedExactPrice);
	}

	@Test
	void ControllerTableArrayFirstElement() {
		// add operation should sort content by name
		// tableContent[y][x] x = 0 list element index | x = 1 list element name

		Controller controller = new Controller(new DummyDataStoring());

		controller.addFood("BBB", "brand1", 100);
		controller.addFood("AAA", "brand2", 200);
		controller.addFood("CCC", "brand3", 300);

		String[][] tableContent = controller.getTableContent();

		assertEquals(tableContent[0][1], "AAA");

	}

	@Test
	void RemoveAllOperation() {

		Controller controller = new Controller(new DummyDataStoring());

		controller.addFood("BBB", "brand1", 100);
		controller.addFood("AAA", "brand2", 200);

		controller.removeAll();

		assertEquals(controller.getTableContent().length, 0);
	}

	@Test
	void RemoveByIndexOperation() {

		Controller controller = new Controller(new DummyDataStoring());

		controller.addFood("BBB", "brand1", 100);
		controller.addFood("AAA", "brand2", 200);
		controller.addFood("CCC", "brand3", 300);

		controller.removeFood(0);

		assertEquals(controller.getTableContent().length, 2);
	}

	@Test
	void checkIfExists() {

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFood("AAA", "brand", 100);

		assertEquals(controller.checkIfFoodExists(0), true);
	}

	@Test
	void checkIfDoesntExist() {

		Controller controller = new Controller(new DummyDataStoring());
		controller.addFood("AAA", "brand", 100);
		assertEquals(controller.checkIfFoodExists(1), false);
	}

}
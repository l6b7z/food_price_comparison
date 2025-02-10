import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.IFood;


class FoodTest {

	
	@Test
	void testIfPriceIsDisplayedProperly() {
		int price = 2233;
		assertEquals("22.33",IFood.formatPrice(price));
	}

}

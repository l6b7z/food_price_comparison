package View;

import java.util.Scanner;

import Controller.Controller;
import Controller.IController;
import Model.DataStoringWithGson;

public class ApplicationView {

	private IController controller;
	private ITable table;
	private Scanner scanner;
	
	private final String MENU = new String(""
					+ "Press [1] to view products" +"\n"
					+ "Press [2] to add a products" +"\n"
					+ "Press [3] to remove a product" +"\n"
					+ "Press [4] to remove all products" +"\n"
					+ "Press [0] to quit the application" +"\n"
					);	
	
	private final String INCORRECT_INPUT_ERROR_MESSAGE = "Please enter correct number";
	private final String INCORRECT_VALUE_ERROR_MESSAGE = "Please enter correct value";
	
	
	private final String GO_BACK_MESSAGE = "Press [0] to go back";
	
	private final String ADD_PRODUCT_MENU = new String(""
					+ "Press [1] to add a product" +"\n"
					+ "Press [2] to add a product with mass" +"\n"
					+ "Press [3] to add a product with quanity" +"\n"
					+ "Press [0] to go back"
					);
	
	private final String ADD_PRODUCT_CONFIRMATION_MESSAGE = "Product has been added";
	
	private final String REMOVE_ALL_PRODUCTS_CONFIRMATION_MESSAGE = "All products have been removed";
	private final String REMOVE_ALL_PRODUCTS_MESSAGE = "Are you sure you want to remove all products";

	private final String REMOVE_PRODUCT_MENU = ""
							+"Enter product # number to remove it" + "\n"
							+"Press [0] to go back";
	
	private final String REMOVE_PRODUCT_MESSAGE = "Are you sure you want to remove this product";
	private final String REMOVE_PRODUCT_ERROR_MESSAGE = "Product wasn't found";
	private final String REMOVE_PRODUCT_CONFIRMATION_MESSAGE = "Product has been removed";
	
	private final String GET_PRODUCT_NAME_MESSAGE = "Enter name of the product";
	private final String GET_PRODUCT_BRAND_MESSAGE = "Enter brand of the product";
	
	private final String GET_PRODUCT_PRICE_MESSAGE = "" 
							+ "Enter price of the product" 
							+ "\n" 
							+ "input example 1000 -> 10.00";
	
	private final String GET_PRODUCT_MASS_MESSAGE = "Enter mass of the product";
	
	private final String GET_PRODUCT_MEASUREMENT_UNIT_MESSAGE = ""
					+"Is the mass entered in milliliters or grams" + "\n"
					+ "1 -> grams" +"\n"
					+ "2 -> milliliters";
	
	private final String GET_PRODUCT_QUANTITY_MESSAGE = "Enter quantity of the product:";
	
	
	
	public static void main(String[] args) {
		
		ApplicationView view = new ApplicationView(new Controller(new DataStoringWithGson()));
		
		view.startApplication();
	}
	
	public ApplicationView(IController controller) {
		this.controller = controller;
		table = new ConsoleTable(controller);
		scanner = new Scanner(System.in);
	}

	
	private void startApplication(){
		boolean isRunning = true;
		boolean firstRun = true;
		int userInput;
		
		while(isRunning){
			
			if (!firstRun) clearConsole();
			printInConsole(MENU);
			
			int minIntInput = 0;
			int maxIntInput = 4;
			
			userInput = getIntInput(minIntInput,maxIntInput,INCORRECT_INPUT_ERROR_MESSAGE);				
			clearConsole();
			switch(userInput) {
				case 0 -> isRunning = false;
			    case 1 -> printProducts();
			    case 2 -> addProduct();
			    case 3 -> removeProduct();
			    case 4 -> removeAll();
			};
			
			firstRun = false;
		}
		scanner.close();
	}


	
	private void addProduct() {
		printInConsole(ADD_PRODUCT_MENU);

		int productType;
		int minIntInput = 0;
		int maxIntInput = 3;
		
		productType = getIntInput(minIntInput,maxIntInput,INCORRECT_INPUT_ERROR_MESSAGE);	
		
		clearConsole();
		
		switch(productType) {
		    case 1 -> addBasicProduct();
		    case 2 -> addProductWithMass();
		    case 3 -> addProductWithQuantity();
		};
	}

	
	
	public void addBasicProduct() {
		
		String name = getProductName();
		String brand = getProductBrand();
		int price = getProductPrice();
		
		controller.addFood(name, brand, price);
		
		clearConsole();
		printInConsole(ADD_PRODUCT_CONFIRMATION_MESSAGE);
		goBackToMenu();
	}
	
	
	private String getProductName() {
		clearConsole();
		printInConsole(GET_PRODUCT_NAME_MESSAGE);
		return getStringInput();
	}

	private String getProductBrand() {
		clearConsole();
		printInConsole(GET_PRODUCT_BRAND_MESSAGE);
		return getStringInput();
	}

	private int getProductPrice() {
		clearConsole();
		printInConsole(GET_PRODUCT_PRICE_MESSAGE);

		int minInput = 1;
		int maxInput = Integer.MAX_VALUE;
		
		return getIntInput(minInput, maxInput, INCORRECT_VALUE_ERROR_MESSAGE);
	}

	public void addProductWithMass() {
		
		String name = getProductName();
		String brand = getProductBrand();
		int price = getProductPrice();
		int mass = getProductMass();
		String measurementUnit = getMeasurementUnit();
			
		controller.addFoodWithMass(name, brand, price, mass, measurementUnit);
		clearConsole();
		printInConsole(ADD_PRODUCT_CONFIRMATION_MESSAGE);
		goBackToMenu();
	}
	

	private int getProductMass() {
		clearConsole();
		printInConsole(GET_PRODUCT_MASS_MESSAGE);
		
		int minInput = 1;
		int maxInput = Integer.MAX_VALUE;
		
		int mass = getIntInput(minInput,maxInput, INCORRECT_VALUE_ERROR_MESSAGE);
		return mass;
	}

	private String getMeasurementUnit() {
		clearConsole();
		printInConsole(GET_PRODUCT_MEASUREMENT_UNIT_MESSAGE);
		
		int minInput = 1;
		int maxInput = 2;
		
		int userInput = getIntInput(minInput, maxInput, INCORRECT_VALUE_ERROR_MESSAGE);
		
		String unit = (userInput == 1) ? "g":"ml" ;
		
		return unit;
	}
	
	public void addProductWithQuantity() {
		
		String name = getProductName();
		String brand = getProductBrand();
		int price = getProductPrice();
		int quantity = getProductQuantity();
			
		controller.addFoodWithQuantity(name, brand, price, quantity);
		clearConsole();
		printInConsole(ADD_PRODUCT_CONFIRMATION_MESSAGE);
		goBackToMenu();
	}
	
	
	private int getProductQuantity() {
		clearConsole();
		printInConsole(GET_PRODUCT_QUANTITY_MESSAGE);
		
		int minInput = 1;
		int maxInput = Integer.MAX_VALUE;
		
		return getIntInput(minInput, maxInput, INCORRECT_VALUE_ERROR_MESSAGE);
	}

	
	private void removeProduct() {
		table.printTable();
		printInConsole(REMOVE_PRODUCT_MENU);
		
		int minInput = 0;
		int maxInput = Integer.MAX_VALUE;
		
		int productTableIndex = getIntInput(minInput, maxInput, INCORRECT_INPUT_ERROR_MESSAGE);
		
		if(productTableIndex == 0) {
			return;
		}
		
		int productIndex = productTableIndex - 1;
		
		if(!controller.checkIfFoodExists(productIndex)) {
			printInConsole(REMOVE_PRODUCT_ERROR_MESSAGE);
		}
		
		String message = REMOVE_PRODUCT_MESSAGE + "\n" 
		+"[#"+productTableIndex +" "+controller.getFood(productIndex)+"]";
		
		if(choiceConfirmation(message)) {
			controller.removeFood(productIndex);
			clearConsole();
			printInConsole(REMOVE_PRODUCT_CONFIRMATION_MESSAGE);
		}

	}

	private void removeAll() {
		if(choiceConfirmation(REMOVE_ALL_PRODUCTS_MESSAGE)) {
			controller.removeAll();
			clearConsole();
			printInConsole(REMOVE_ALL_PRODUCTS_CONFIRMATION_MESSAGE);
			goBackToMenu();
		}
	}
	
	private boolean choiceConfirmation(String message) {
		clearConsole();
		printInConsole(message + "\n");
		printInConsole("Press [1] to confirm");
		printInConsole("Press [2] to cancel");
		
		if(getIntInput(1, 2, "please pres 1 or 2") == 1) {
			clearConsole();
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	private void printProducts() {
		table.printTable();
		goBackToMenu();
	}
	
	private void goBackToMenu(){
		printInConsole(GO_BACK_MESSAGE);
		getIntInput(0, 0, GO_BACK_MESSAGE);
	}


	
	private void printInConsole(String str) {
		System.out.println(str);
	}

	private void clearConsole() {
		String whiteSpace = "\n";
		System.out.print(whiteSpace.repeat(30));
	}

	private String getStringInput() {
		String input = "";
		
		input = scanner.next();
		input += " " + scanner.nextLine();
		
		return input;
	}
	
	private int getIntInput(int minIntInput, int maxIntInput, String errorMessage) {
		int input = 0;
		while (true) {
			try {
				input = Integer.parseInt(scanner.next());
				if(input >= minIntInput && input <= maxIntInput) {
					return input;
				}
				else {
					throw new NumberFormatException();
				}

			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
			}
		}
	}
	
	
}

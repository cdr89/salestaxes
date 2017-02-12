package it.caldesi.salestaxes;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.caldesi.salestaxes.model.Category;
import it.caldesi.salestaxes.model.Item;
import it.caldesi.salestaxes.model.taxes.Tax;

public class ShoppingBasketTest {

	static Tax basicTax;
	static Tax importTax;

	static Category taxedCategory;
	static Category bookCategory;
	static Category foodCategory;
	static Category medicalCategory;

	@BeforeClass
	public static void setUpData() {
		basicTax = new Tax("Basic tax", 10);
		importTax = new Tax("Import tax", 5);

		taxedCategory = new Category("taxed", basicTax);
		bookCategory = new Category("book", null);
		foodCategory = new Category("food", null);
		medicalCategory = new Category("medical", null);
	}

	@Test
	public void testCase1() {
		Item book = new Item("book", 12.49f, bookCategory);
		Item musicCD = new Item("music CD", 14.99f, taxedCategory);
		Item chocolateBar = new Item("chocolate bar", 0.85f, foodCategory);

		ShoppingBasket shoppingBasket = new ShoppingBasket();
		shoppingBasket.addItem(book);
		shoppingBasket.addItem(musicCD);
		shoppingBasket.addItem(chocolateBar);

		String receipt = shoppingBasket.generateReceipt(true);

		System.out.println(receipt);
		System.out.println();

		String expectedReceipt = "1 book: 12.49\n" + //
				"1 chocolate bar: 0.85\n" + //
				"1 music CD: 16.49\n" + //
				"Sales taxes: 1.50\n" + //
				"Total: 29.83";

		assertEquals(expectedReceipt, receipt);
	}

	@Test
	public void testCase2() {
		Item impBoxChocolates = new Item("imported box of chocolates", 10.00f, foodCategory, importTax);
		Item impBottlePerfume = new Item("imported bottle of perfume", 47.50f, taxedCategory, importTax);

		ShoppingBasket shoppingBasket = new ShoppingBasket();
		shoppingBasket.addItem(impBoxChocolates);
		shoppingBasket.addItem(impBottlePerfume);

		String receipt = shoppingBasket.generateReceipt(true);

		System.out.println(receipt);
		System.out.println();

		String expectedReceipt = "1 imported bottle of perfume: 54.65\n" + //
				"1 imported box of chocolates: 10.50\n" + //
				"Sales taxes: 7.65\n" + //
				"Total: 65.15";

		assertEquals(expectedReceipt, receipt);
	}

	@Test
	public void testCase3() {
		Item impBottlePerfume = new Item("imported bottle of perfume", 27.99f, taxedCategory, importTax);
		Item bottlePerfume = new Item("bottle of perfume", 18.99f, taxedCategory);
		Item headachePills = new Item("headache pills", 9.75f, medicalCategory);
		Item impBoxChocolates = new Item("imported box of chocolates", 11.25f, foodCategory, importTax);

		ShoppingBasket shoppingBasket = new ShoppingBasket();
		shoppingBasket.addItem(impBottlePerfume);
		shoppingBasket.addItem(bottlePerfume);
		shoppingBasket.addItem(headachePills);
		shoppingBasket.addItem(impBoxChocolates);

		String receipt = shoppingBasket.generateReceipt(true);

		System.out.println(receipt);
		System.out.println();

		String expectedReceipt = "1 bottle of perfume: 20.89\n" + //
				"1 headache pills: 9.75\n" + //
				"1 imported bottle of perfume: 32.19\n" + //
				"1 imported box of chocolates: 11.80\n" + //
				"Sales taxes: 6.65\n" + //
				"Total: 74.63";

		assertEquals(expectedReceipt, receipt);
	}

}

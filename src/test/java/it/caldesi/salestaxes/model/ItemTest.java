package it.caldesi.salestaxes.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.caldesi.salestaxes.model.taxes.Tax;

public class ItemTest {

	static Tax basicTax;
	static Tax importTax;

	static Category taxedCategory;
	static Category notTaxedCategory;

	@BeforeClass
	public static void setUpData() {
		basicTax = new Tax("Basic tax", 10);
		importTax = new Tax("Import tax", 5);

		taxedCategory = new Category("taxed", basicTax);
		notTaxedCategory = new Category("notTaxed", null);
	}

	@Test
	public void testNotImportedNoBasicTax() {
		Item notImportedNoBasicTax = new Item("notImportedNoBasicTax", 10.00f, notTaxedCategory);

		float basicTaxValue = notImportedNoBasicTax.basicTax();
		assertEquals(0f, basicTaxValue, 0);

		float importTaxValue = notImportedNoBasicTax.importTax();
		assertEquals(0f, importTaxValue, 0);

		float totalTaxesValue = notImportedNoBasicTax.getTaxes();
		assertEquals(0f, totalTaxesValue, 0);

		float taxedPrice = notImportedNoBasicTax.getTaxedPrice();
		assertEquals(10.00f, taxedPrice, 0);
	}

	@Test
	public void testImportedNoBasicTax() {
		Item importedNoBasicTax = new Item("importedNoBasicTax", 10.00f, notTaxedCategory, importTax);

		float basicTaxValue = importedNoBasicTax.basicTax();
		assertEquals(0f, basicTaxValue, 0);

		float importTaxValue = importedNoBasicTax.importTax();
		assertEquals(0.5f, importTaxValue, 0);

		float totalTaxesValue = importedNoBasicTax.getTaxes();
		assertEquals(0.5f, totalTaxesValue, 0);

		float taxedPrice = importedNoBasicTax.getTaxedPrice();
		assertEquals(10.5f, taxedPrice, 0);
	}

	@Test
	public void testNotImportedBasicTax() {
		Item notImportedBasicTax = new Item("notImportedBasicTax", 10.00f, taxedCategory);

		float basicTaxValue = notImportedBasicTax.basicTax();
		assertEquals(1.0f, basicTaxValue, 0);

		float importTaxValue = notImportedBasicTax.importTax();
		assertEquals(0.0f, importTaxValue, 0);

		float totalTaxesValue = notImportedBasicTax.getTaxes();
		assertEquals(1.0f, totalTaxesValue, 0);

		float taxedPrice = notImportedBasicTax.getTaxedPrice();
		assertEquals(11.00f, taxedPrice, 0);
	}

	@Test
	public void testImportedBasicTax() {
		Item importedBasicTax = new Item("importedBasicTax", 10.00f, taxedCategory, importTax);

		float basicTaxValue = importedBasicTax.basicTax();
		assertEquals(1.0f, basicTaxValue, 0);

		float importTaxValue = importedBasicTax.importTax();
		assertEquals(0.5f, importTaxValue, 0);

		float totalTaxesValue = importedBasicTax.getTaxes();
		assertEquals(1.5f, totalTaxesValue, 0);

		float taxedPrice = importedBasicTax.getTaxedPrice();
		assertEquals(11.50f, taxedPrice, 0);
	}

}
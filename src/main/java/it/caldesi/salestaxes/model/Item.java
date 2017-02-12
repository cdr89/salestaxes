package it.caldesi.salestaxes.model;

import it.caldesi.salestaxes.model.taxes.Tax;
import it.caldesi.salestaxes.utils.Utils;

public class Item {

	private String name; // the name is supposed to be unique for each item
	private float price;
	private Category category;
	private Tax importTax;

	// Constructors

	public Item(String name, float price, Category category) {
		setName(name);
		setPrice(price);
		setCategory(category);
	}

	public Item(String name, float price, Category category, Tax importTax) {
		setName(name);
		setPrice(price);
		setCategory(category);
		setImportTax(importTax);
	}

	// Getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Tax getImportTax() {
		return importTax;
	}

	public void setImportTax(Tax importTax) {
		this.importTax = importTax;
	}

	// Object methods

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", category=" + category + ", importTax=" + importTax + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	// Utility methods

	/**
	 * Calculates the total amount of taxes for the given quantity
	 * 
	 * @param quantity
	 *            quantity of items
	 * 
	 * @return total taxes calculated on the given quantity of items
	 */
	public float getTaxes(int quantity) {
		return Utils.sumFloatDecimals(basicTax(quantity), importTax(quantity));
	}

	/**
	 * Calculates the total amount of taxes
	 * 
	 * @return total taxes on the item
	 */
	public float getTaxes() {
		return getTaxes(1);
	}

	/**
	 * Calculates the total amount of import taxes for the given quantity
	 * 
	 * @param quantity
	 *            quantity of items
	 * 
	 * @return total import taxes calculated on the given quantity of items
	 */
	public float importTax(int quantity) {
		float totalPrice = quantity * price;
		if (importTax == null)
			return 0f;

		return importTax.calculateAmount(totalPrice);
	}

	/**
	 * Calculates the total amount of import taxes
	 * 
	 * @return total import taxes calculated on the given quantity of items
	 */
	public float importTax() {
		return importTax(1);
	}

	/**
	 * Calculates the total amount of basic taxes for the given quantity
	 * 
	 * @param quantity
	 *            quantity of items
	 * 
	 * @return total basic taxes calculated on the given quantity of items
	 */
	public float basicTax(int quantity) {
		float totalPrice = quantity * price;
		if (category.getBasicTax() == null)
			return 0f;

		return category.getBasicTax().calculateAmount(totalPrice);
	}

	/**
	 * Calculates the total amount of basic taxes
	 * 
	 * @return total basic taxes
	 */
	public float basicTax() {
		return basicTax(1);
	}

	/**
	 * Calculates the price for a given quantity
	 * 
	 * @param quantity
	 *            quantity of items
	 * 
	 * @return total price calculated on the given quantity of items
	 */
	public float getPrice(int quantity) {
		return Utils.multiplyFloatDecimals(price, (float) quantity);
	}

	/**
	 * Calculates the taxed price for a given quantity
	 * 
	 * @param quantity
	 *            quantity of items
	 * 
	 * @return total taxed price calculated on the given quantity of items
	 */
	public float getTaxedPrice(int quantity) {
		return Utils.sumFloatDecimals(getPrice(quantity), getTaxes(quantity));
	}

	/**
	 * Calculates the taxed price
	 * 
	 * @return total taxed price calculated on the given quantity of items
	 */
	public float getTaxedPrice() {
		return Utils.sumFloatDecimals(getPrice(), getTaxes());
	}

}

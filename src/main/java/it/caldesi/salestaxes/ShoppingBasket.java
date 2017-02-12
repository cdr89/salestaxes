package it.caldesi.salestaxes;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

import it.caldesi.salestaxes.model.Item;
import it.caldesi.salestaxes.utils.Utils;

public class ShoppingBasket {

	private HashMap<Item, Integer> itemsQuantity;

	// Constructors

	public ShoppingBasket() {
		itemsQuantity = new HashMap<Item, Integer>();
	}

	// Utility methods

	/**
	 * Add an item to the shopping basket
	 * 
	 * @param item
	 *            item to be added to the shopping basket
	 */
	public void addItem(Item item) {
		if (itemsQuantity.containsKey(item)) {
			int quantity = itemsQuantity.get(item);
			itemsQuantity.put(item, quantity + 1);
		} else {
			itemsQuantity.put(item, 1);
		}
	}

	/**
	 * Generate the shopping receipt with total and taxes
	 * 
	 * @param sortedByItemNames
	 *            true if the list has to be sorted by item names, false for
	 *            random order
	 */
	public String generateReceipt(boolean sortedByItemNames) {
		StringBuilder stringBuilder = new StringBuilder();
		float totalSalesTaxes = 0;
		float total = 0;

		Collection<Item> items = itemsQuantity.keySet();
		if (sortedByItemNames) {
			LinkedList<Item> itemList = new LinkedList<Item>(itemsQuantity.keySet());
			items = itemList;
			Collections.sort(itemList, new Comparator<Item>() {
				public int compare(Item o1, Item o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
		}

		for (Item item : items) {
			int quantity = itemsQuantity.get(item);

			float taxes = item.getTaxes(quantity);
			totalSalesTaxes = Utils.sumFloatDecimals(totalSalesTaxes, taxes);
			float taxedPrice = item.getTaxedPrice(quantity);
			total = Utils.sumFloatDecimals(total, taxedPrice);

			stringBuilder.append(quantity);
			stringBuilder.append(' ');
			stringBuilder.append(item.getName());
			stringBuilder.append(':');
			stringBuilder.append(' ');

			stringBuilder.append(String.format(Locale.US, "%.2f", taxedPrice));
			stringBuilder.append('\n');
		}

		stringBuilder.append("Sales taxes: ");
		stringBuilder.append(String.format(Locale.US, "%.2f", totalSalesTaxes));
		stringBuilder.append('\n');
		stringBuilder.append("Total: ");
		stringBuilder.append(String.format(Locale.US, "%.2f", total));

		return stringBuilder.toString();
	}

	// Object methods

	@Override
	public String toString() {
		return generateReceipt(true);
	}

}

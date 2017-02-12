package it.caldesi.salestaxes.model.taxes;

import it.caldesi.salestaxes.utils.Utils;

public class Tax {

	private String name;
	private float percentage;

	// Constructors

	public Tax(String name, float percentage) {
		setName(name);
		setPercentage(percentage);
	}

	// Getters & Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	// Utility methods

	/**
	 * Apply the tax to a given item rounding up to the nearest 0.05
	 * 
	 * @param price
	 *            the price on which the tax is calculated
	 * 
	 * @return tax amount calculated on item
	 */
	public float calculateAmount(float price) {
		float calculatedAmount = price * percentage / 100;
		return Utils.roundToNearest5Cent(calculatedAmount);
	}

	// Object methods

	@Override
	public String toString() {
		return "Tax [name=" + name + ", percentage=" + percentage + "]";
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
		Tax other = (Tax) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

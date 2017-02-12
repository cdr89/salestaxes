package it.caldesi.salestaxes.model;

import it.caldesi.salestaxes.model.taxes.Tax;

public class Category {

	private String name; // the name is supposed to be unique for each category
	private Tax basicTax;

	// Constructors

	public Category(String name) {
		setName(name);
	}

	public Category(String name, Tax basicTax) {
		setName(name);
		setBasicTax(basicTax);
	}

	// Getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tax getBasicTax() {
		return basicTax;
	}

	public void setBasicTax(Tax basicTax) {
		this.basicTax = basicTax;
	}

	// Object methods

	@Override
	public String toString() {
		return "Category [name=" + name + ", basicTax=" + basicTax + "]";
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

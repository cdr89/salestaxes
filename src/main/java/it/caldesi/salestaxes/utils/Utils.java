package it.caldesi.salestaxes.utils;

import java.math.BigDecimal;

public class Utils {

	/**
	 * Round up the tax amount to the nearest 0.05
	 * 
	 * @param amount
	 *            amount to round
	 * 
	 * @return rounded amount to the nearest 0.05
	 */
	public static float roundToNearest5Cent(float amount) {
		float calcAmountFloat = (float) (Math.round(amount * 20.0) / 20.0);
		return calcAmountFloat;
	}

	/**
	 * Sum float numbers using BigDecimal in order to not loose precision during
	 * sum operation and then convert the result again to a float
	 * 
	 * @param n
	 *            numbers to be summed
	 * 
	 * @return the sum of the float numbers given as argument
	 */
	public static float sumFloatDecimals(float... n) {
		BigDecimal sum = new BigDecimal(0);
		for (float f : n) {
			// is needed to use a string representation of the number to have no
			// errors in the conversion of the float in BigDecimal
			BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
			sum = sum.add(bigDecimal);
		}

		return sum.floatValue();
	}

	/**
	 * Multiplies float numbers using BigDecimal in order to not loose precision
	 * during multiplication operation and then convert the result again to a
	 * float
	 * 
	 * @param n
	 *            numbers to be summed
	 * 
	 * @return the product of the float numbers given as argument
	 */
	public static float multiplyFloatDecimals(float... n) {
		BigDecimal mult = new BigDecimal(1);
		for (float f : n) {
			// is needed to use a string representation of the number to have no
			// errors in the conversion of the float in BigDecimal
			BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
			mult = mult.multiply(bigDecimal);
		}

		return mult.floatValue();
	}

}

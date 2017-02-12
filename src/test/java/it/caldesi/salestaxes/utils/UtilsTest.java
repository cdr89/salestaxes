package it.caldesi.salestaxes.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import it.caldesi.salestaxes.utils.Utils;

public class UtilsTest {

	@Test
	public void roundToNearest5CentTest() {
		assertEquals(0f, Utils.roundToNearest5Cent(0), 0);

		assertEquals(0.25f, Utils.roundToNearest5Cent(0.24f), 0);
		assertEquals(2.75f, Utils.roundToNearest5Cent(2.73f), 0);
		assertEquals(3.75f, Utils.roundToNearest5Cent(3.7323f), 0);
		assertEquals(5.70f, Utils.roundToNearest5Cent(5.725f), 0);
		assertEquals(7.75f, Utils.roundToNearest5Cent(7.75f), 0);

		assertEquals(0.30f, Utils.roundToNearest5Cent(0.31f), 0);
		assertEquals(11.70f, Utils.roundToNearest5Cent(11.71f), 0);
		assertEquals(13.70f, Utils.roundToNearest5Cent(13.70f), 0);

		assertEquals(0.50f, Utils.roundToNearest5Cent(0.48f), 0);
		assertEquals(17.80f, Utils.roundToNearest5Cent(17.78f), 0);
		assertEquals(19.75f, Utils.roundToNearest5Cent(19.77f), 0);
	}
	
	@Test
	public void testDecimalPrecision(){
		float a = 2.45f;
		float b = 4.2f;
		
		float sum = a + b;
		assertNotEquals(6.65f, sum, 0);
		
		sum = Utils.sumFloatDecimals(a, b);
		assertEquals(6.65f, sum, 0);
	}

}

package com.itau.distributor.utils;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class FormatterUtilsTest {

	@Test
	public void testFormatterBigValuesBigDecimalOK() {
		final BigDecimal bg1 = new BigDecimal(999999999.99);
		Assert.assertEquals("999.999.999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg1));
		final BigDecimal bg2 = new BigDecimal(999999999999.99);
		Assert.assertEquals("999.999.999.999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg2));
	}

	@Test
	public void testFormatterShortValuesBigDecimalOK() {
		final BigDecimal bg1 = new BigDecimal(0);
		Assert.assertEquals("0,00", FormatterUtils.formatterBigDecimalToMonetaryString(bg1));
		final BigDecimal bg2 = new BigDecimal(0.01);
		Assert.assertEquals("0,01", FormatterUtils.formatterBigDecimalToMonetaryString(bg2));
		final BigDecimal bg3 = new BigDecimal(1.05);
		Assert.assertEquals("1,05", FormatterUtils.formatterBigDecimalToMonetaryString(bg3));
	}

	@Test
	public void testFormatterBigDecimalDecimalPointsFalse() {
		final BigDecimal bg1 = new BigDecimal(0);
		Assert.assertNotEquals("0", FormatterUtils.formatterBigDecimalToMonetaryString(bg1));
		final BigDecimal bg2 = new BigDecimal(0);
		Assert.assertNotEquals("0,", FormatterUtils.formatterBigDecimalToMonetaryString(bg2));
		final BigDecimal bg3 = new BigDecimal(0);
		Assert.assertNotEquals("0,0", FormatterUtils.formatterBigDecimalToMonetaryString(bg3));
		final BigDecimal bg4 = new BigDecimal(1.00);
		Assert.assertNotEquals("1,000", FormatterUtils.formatterBigDecimalToMonetaryString(bg4));
	}

	@Test
	public void testFormatterBigDecimalNumeralPointsFalse() {
		final BigDecimal bg1 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999999.999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg1));
		final BigDecimal bg2 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999.999999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg2));
		final BigDecimal bg3 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999.999.99999", FormatterUtils.formatterBigDecimalToMonetaryString(bg3));
		final BigDecimal bg4 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999,999.999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg4));
		final BigDecimal bg5 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999.999,999,99", FormatterUtils.formatterBigDecimalToMonetaryString(bg5));
		final BigDecimal bg6 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999.999.999.99", FormatterUtils.formatterBigDecimalToMonetaryString(bg6));
		final BigDecimal bg7 = new BigDecimal(999999999.99);
		Assert.assertNotEquals("999,999,999.99", FormatterUtils.formatterBigDecimalToMonetaryString(bg7));
	}
}
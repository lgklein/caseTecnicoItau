package com.itau.distributor.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import lombok.experimental.UtilityClass;

/**
 * @author luis
 */
@UtilityClass
public final class FormatterUtils {

	private final String CURRENCY_DEFAULT_FORMAT = "#,###,##0.00";

	/**
	 * Method responsible for formatting a BigDecimal to String in the display format.
	 * @param bigDecimal that represents the money value.
	 * @return Monetary value formatted for display, without the monetary symbol.
	 */
	public static String formatterBigDecimalToMonetaryString(final BigDecimal bigDecimal) {
		return new DecimalFormat(CURRENCY_DEFAULT_FORMAT).format(bigDecimal);
	}
}
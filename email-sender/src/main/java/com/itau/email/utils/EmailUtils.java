package com.itau.email.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luis
 */
public final class EmailUtils {

	private static final String TITLE_REGEX = "<title>(.+?)</title>";

	/**
	 * Method responsible for find the first title tag value for a content emailTemplate.
	 * @param content of the emailTemplate.
	 * @return value from tag title.
	 */
	public static String takeFirstContentTitle(final String content) {
		final StringBuilder ret = new StringBuilder("");
		final Matcher matcher = Pattern.compile(TITLE_REGEX, Pattern.DOTALL).matcher(content);
		if (matcher.find()) {
			ret.append(matcher.group(1));
		}
		return ret.toString();
	}
}
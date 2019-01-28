package com.foo.activity;

import org.apache.commons.lang3.Validate;

class NameParser implements ActivityConstants {

	static String parseName(String line) {
		String ret = line.replaceAll(TIME_REGEX, "");

		ret = ret.replaceAll("\\s{2,}", " ").trim();

		Validate.notBlank(ret);

		return ret;
	}

}

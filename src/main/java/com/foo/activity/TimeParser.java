package com.foo.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TimeParser {

	static final String FIXED_LENGTH_NAME = "sprint";

	static final int FIXED_LENGTH_MINS = 15;

	static final String TIME_REGEX = String.format(

			"([\\d]+min|%s)",

			FIXED_LENGTH_NAME
	);

	static final Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX);

	static int parseTime(String line) {
		Matcher m = TIME_PATTERN.matcher(line);

		int ret;

		if (m.find()) {

			String result = m.group();

			if (m.find()) {
				throw new IllegalArgumentException(
						String.format(
								"Multiple activity times encountered in line '%s'",
								line
						)
				);
			}

			if ("sprint".equals(result)) {

				return FIXED_LENGTH_MINS;

			} else {

				String time = result.replace("min", "");

				ret = Integer.parseInt(time);

			}

		} else {

			throw new IllegalArgumentException(
					String.format(
							"No activity time found in line '%s'",
							line
					)
			);

		}

		return ret;
	}

}

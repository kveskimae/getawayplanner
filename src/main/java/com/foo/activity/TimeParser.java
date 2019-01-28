package com.foo.activity;

import com.foo.exception.ActivityException;

import java.util.regex.Matcher;

class TimeParser implements ActivityConstants {

	static int parseTime(String line) {
		Matcher m = TIME_PATTERN.matcher(line);

		int ret;

		if (m.find()) {

			String result = m.group();

			if (m.find()) {
				throw new ActivityException(
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

			throw new ActivityException(
					String.format(
							"No activity time found in line '%s'",
							line
					)
			);

		}

		return ret;
	}

}

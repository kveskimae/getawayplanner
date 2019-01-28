package com.foo.activity;

import com.foo.exception.ActivityException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

class TimeParser implements ActivityConstants {

	static Duration parseTime(String line) {
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

				return Duration.of(FIXED_LENGTH_MINS, ChronoUnit.MINUTES);

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

		return Duration.of(ret, ChronoUnit.MINUTES);
	}

}

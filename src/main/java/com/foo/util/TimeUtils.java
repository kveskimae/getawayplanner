package com.foo.util;

import com.foo.activity.ActivityConstants;
import com.foo.exception.ActivityException;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;

public class TimeUtils implements ActivityConstants {

	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

	public static String formatTime(LocalTime time) {
		return time.format(formatter);
	}

	public static Duration parseTime(String line) {
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

			if (SPRINT_DURATION_NAME.equals(result)) {

				return Duration.of(SPRINT_DURATION_MINS, ChronoUnit.MINUTES);

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

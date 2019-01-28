package com.foo.activity;

import com.foo.exception.ActivityException;
import com.foo.util.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Duration;

public class Activity implements ActivityConstants {

	public final String name;

	public final Duration duration;

	public Activity(String name, Duration duration) {
		if (StringUtils.isBlank(name)) {
			throw new ActivityException("Empty activity name encountered");
		}

		if (duration.toMinutes() < MINIMUM_ALLOWED_ACTIVITY_DURATION_MINS || duration.toMinutes() > MAXIMUM_ALLOWED_ACTIVITY_DURATION_MINS) {
			throw new ActivityException(String.format("Illegal activity duration: %dmins", duration.toMinutes()));
		}

		this.name = name;
		this.duration = duration;
	}

	public static Activity parseActivity(String s) {
		Duration duration = TimeUtils.parseTime(s);

		String name = NameParser.parseName(s);

		return new Activity(name, duration);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	public String buildDisplayString() {
		return String.format("%s %dmin", name, duration.toMinutes());
	}
}

package com.foo.activity;

import com.foo.exception.ActivityException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Activity implements ActivityConstants {

	public final String name;

	public final int mins;

	public Activity(String name, int mins) {
		if (StringUtils.isBlank(name)) {
			throw new ActivityException("Empty activity name encountered");
		}

		if (mins < MINIMUM_ALLOWED_ACTIVITY_LENGTH_MINS || mins > MAXIMUM_ALLOWED_ACTIVITY_LENGTH_MINS) {
			throw new ActivityException(String.format("Illegal activity duration: %d", mins));
		}

		this.name = name;
		this.mins = mins;
	}

	public static Activity parseActivity(String s) {
		int time = TimeParser.parseTime(s);

		String name = NameParser.parseName(s);

		return new Activity(name, time);
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
		return String.format("%s %dmin", name, mins);
	}
}

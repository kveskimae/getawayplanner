package com.foo.activity;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Activity {

	public final String name;

	public final int mins;

	public Activity(String name, int mins) {
		Validate.notBlank(name, "Illegal activity name");

		Validate.exclusiveBetween(
				1,
				120,
				mins,
				String.format("Illegal length: %d", mins)
		);

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

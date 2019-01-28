package com.foo.activity;

import java.util.regex.Pattern;

public interface ActivityConstants {

	int

			MINIMUM_ALLOWED_ACTIVITY_DURATION_MINS = 1,

	MAXIMUM_ALLOWED_ACTIVITY_DURATION_MINS = 120,

	SPRINT_DURATION_MINS = 15;

	String SPRINT_DURATION_NAME = "sprint",

	TIME_REGEX = String.format(

			"([\\d]+min|%s)",

			SPRINT_DURATION_NAME
	);

	Pattern TIME_PATTERN = Pattern.compile(TIME_REGEX);

}

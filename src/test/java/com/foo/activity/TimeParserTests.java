package com.foo.activity;


import com.foo.exception.ActivityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeParserTests {

	@Test
	public void extractsMinutes() {
		assertEquals(60, TimeParser.parseTime("Duck Herding 60min"));
	}

	@Test
	public void findsSprint() {
		assertEquals(TimeParser.FIXED_LENGTH_MINS, TimeParser.parseTime("Time Tracker sprint"));
	}

	@Test
	public void throwsIfHours() {
		assertThrows(ActivityException.class,
				() -> {
					TimeParser.parseTime("Time Tracker 1hr");
				});
	}

}

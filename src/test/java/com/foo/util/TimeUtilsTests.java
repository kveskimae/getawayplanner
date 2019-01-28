package com.foo.util;


import com.foo.exception.ActivityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeUtilsTests {

	@Test
	public void extractsMinutes() {
		assertEquals(60, TimeUtils.parseTime("Duck Herding 60min").toMinutes());
	}

	@Test
	public void findsSprint() {
		assertEquals(TimeUtils.SPRINT_DURATION_MINS, TimeUtils.parseTime("Time Tracker sprint").toMinutes());
	}

	@Test
	public void throwsIfHours() {
		assertThrows(ActivityException.class,
				() -> {
					TimeUtils.parseTime("Time Tracker 1hr");
				});
	}

}

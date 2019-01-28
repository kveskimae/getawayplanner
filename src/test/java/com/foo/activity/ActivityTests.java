package com.foo.activity;

import com.foo.exception.ActivityException;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTests {

	@Test
	public void equalsComparesByAttributes() {
		assertEquals(
				new Activity("Duck Herding", Duration.of(60, ChronoUnit.MINUTES)),
				new Activity("Duck Herding", Duration.of(60, ChronoUnit.MINUTES))
		);
	}

	@Test
	public void notEqualIfDurationsDiffer() {
		assertNotEquals(
				new Activity("Duck Herding", Duration.of(40, ChronoUnit.MINUTES)),
				new Activity("Duck Herding", Duration.of(60, ChronoUnit.MINUTES))
		);
	}

	@Test
	public void notEqualIfNamesDiffer() {
		assertNotEquals(
				new Activity("Duck Herding", Duration.of(40, ChronoUnit.MINUTES)),
				new Activity("Duck Herding2", Duration.of(40, ChronoUnit.MINUTES))
		);
	}

	@Test
	public void throwsIfTimeZero() {
		assertThrows(ActivityException.class,
				() -> {
					new Activity("Duck Herding", Duration.of(0, ChronoUnit.MINUTES));
				});
	}

	@Test
	public void throwsNameIsEmpty() {
		assertThrows(ActivityException.class,
				() -> {
					new Activity("    	", Duration.of(40, ChronoUnit.MINUTES));
				});
	}

	@Test
	public void throwsNameIsNull() {
		assertThrows(ActivityException.class,
				() -> {
					new Activity(null, Duration.of(40, ChronoUnit.MINUTES));
				});
	}

	@Test
	public void extractsMinutesFormat() {
		assertEquals(
				new Activity("Duck Herding", Duration.of(60, ChronoUnit.MINUTES)),
				Activity.parseActivity("Duck Herding 60min")
		);
	}

	@Test
	public void extractsSprintFormat() {
		assertEquals(
				new Activity("Time Tracker", Duration.of(15, ChronoUnit.MINUTES)),
				Activity.parseActivity("Time Tracker sprint")
		);
	}

	@Test
	public void throwsIfHours() {
		assertThrows(ActivityException.class,
				() -> {
					TimeParser.parseTime("Time Tracker 1hr");
				});
	}

}

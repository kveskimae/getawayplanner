package com.foo.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTests {

	@Test
	public void equalsComparesByAttributes() {
		assertEquals(
				new Activity("Duck Herding", 60),
				new Activity("Duck Herding", 60)
		);
	}

	@Test
	public void notEqualIfLengthsDiffer() {
		assertNotEquals(
				new Activity("Duck Herding", 40),
				new Activity("Duck Herding", 60)
		);
	}

	@Test
	public void notEqualIfNamesDiffer() {
		assertNotEquals(
				new Activity("Duck Herding", 40),
				new Activity("Duck Herding2", 40)
		);
	}

	@Test
	public void throwsIfTimeNegative() {
		assertThrows(IllegalArgumentException.class,
				() -> {
					new Activity("Duck Herding", -40);
				});
	}

	@Test
	public void throwsNameIsEmpty() {
		assertThrows(IllegalArgumentException.class,
				() -> {
					new Activity("    	", 40);
				});
	}

	@Test
	public void throwsNameIsNull() {
		assertThrows(NullPointerException.class,
				() -> {
					new Activity(null, 40);
				});
	}

	@Test
	public void extractsMinutesFormat() {
		assertEquals(
				new Activity("Duck Herding", 60),
				Activity.parseActivity("Duck Herding 60min")
		);
	}

	@Test
	public void extractsSprintFormat() {
		assertEquals(
				new Activity("Time Tracker", 15),
				Activity.parseActivity("Time Tracker sprint")
		);
	}

	@Test
	public void throwsIfHours() {
		assertThrows(IllegalArgumentException.class,
				() -> {
					TimeParser.parseTime("Time Tracker 1hr");
				});
	}

}

package com.foo.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameParserTests {

	@Test
	public void removesMinutes() {
		assertEquals("Duck Herding", NameParser.parseName("Duck Herding 60min"));
	}

	@Test
	public void removesSprint() {
		assertEquals("Time Tracker", NameParser.parseName("Time Tracker sprint"));
	}

	@Test
	public void cleansWhitespace() {
		assertEquals("More Fun", NameParser.parseName("  More	 Fun 	 sprint"));
	}

	@Test
	public void throwsIfEmptyLeft() {
		assertThrows(IllegalArgumentException.class,
				() -> {
					NameParser.parseName("   5min");
				});
	}

}

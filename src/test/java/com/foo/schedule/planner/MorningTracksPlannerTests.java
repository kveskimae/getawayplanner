package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.exception.ScheduleException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MorningTracksPlannerTests {

	@Test
	public void fitsOne() {

		TreeMap<Integer, List<Activity>> mins2Count = Length2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 1)
		);

		List<Activity> result = MorningTracksPlanner.findExactCombination(60, mins2Count);

		assertEquals(1, result.size());
		assertEquals(60, result.get(0).mins);
	}

	@Test
	public void fitsMultiple1() {
		TreeMap<Integer, List<Activity>> mins2Count = Length2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 1),
				Pair.of(30, 1),
				Pair.of(15, 1)
		);

		List<Activity> result = MorningTracksPlanner.findExactCombination(45, mins2Count);

		assertEquals(2, result.size());

		assertEquals(30, result.get(0).mins);
		assertEquals(15, result.get(1).mins);

		Assertions.assertIterableEquals(Arrays.asList(60), mins2Count.keySet());
	}

	@Test
	public void fitsMultiple2() {
		TreeMap<Integer, List<Activity>> mins2Count = Length2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 3),
				Pair.of(30, 5),
				Pair.of(15, 2)
		);

		List<Activity> result = MorningTracksPlanner.findExactCombination(135, mins2Count);

		assertEquals(3, result.size());

		assertEquals(60, result.get(0).mins);
		assertEquals(60, result.get(1).mins);
		assertEquals(15, result.get(2).mins);

		assertEquals(1, mins2Count.get(60).size());
		assertEquals(5, mins2Count.get(30).size());
		assertEquals(1, mins2Count.get(15).size());
	}

	@Test
	public void totalTimeOutOfRangeFails() {
		assertThrows(ScheduleException.class,
				() -> {
					MorningTracksPlanner.findExactCombination(60, new TreeMap<>());
				});
	}

	@Test
	public void noExactCombinationFails() {
		TreeMap<Integer, List<Activity>> mins2Count = Length2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 1),
				Pair.of(30, 1),
				Pair.of(15, 1)
		);

		assertThrows(IllegalArgumentException.class,
				() -> {
					MorningTracksPlanner.findExactCombination(17, mins2Count);
				});
	}

}

package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.schedule.ScheduleConfiguration;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EveningTracksPlannerTests {

	@Test
	public void splitsTwo() {
		TreeMap<Integer, List<Activity>> mins2Activities = Duration2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 2)
		);

		ScheduleConfiguration scheduleConfiguration = new ScheduleConfiguration();

		scheduleConfiguration.noOfTeams = 2;

		List<List<Activity>> result = EveningTracksPlanner.partitionMaximallyEqualDurations(mins2Activities, scheduleConfiguration);

		assertEquals(1, result.get(0).size());
		assertEquals(60, result.get(0).get(0).duration.toMinutes());
		assertEquals(1, result.get(1).size());
		assertEquals(60, result.get(1).get(0).duration.toMinutes());
	}

	@Test
	public void fitsMultiple1() {
		TreeMap<Integer, List<Activity>> mins2Activities = Duration2ActivitiesMapBuilder.buildMap(
				Pair.of(60, 1),
				Pair.of(40, 1),
				Pair.of(30, 5),
				Pair.of(15, 3),
				Pair.of(45, 4)
		);

		ScheduleConfiguration scheduleConfiguration = new ScheduleConfiguration();

		scheduleConfiguration.noOfTeams = 2;

		List<List<Activity>> result = EveningTracksPlanner.partitionMaximallyEqualDurations(mins2Activities, scheduleConfiguration);

		assertEquals(2, result.size());

		int sum1 = EveningTracksPlanner.sumList(result.get(0));
		int sum2 = EveningTracksPlanner.sumList(result.get(1));

		assertEquals(235, Math.min(sum1, sum2));
		assertEquals(240, Math.max(sum1, sum2));
	}

}

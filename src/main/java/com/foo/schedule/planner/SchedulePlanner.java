package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.schedule.Schedule;
import com.foo.schedule.ScheduleConfiguration;

import java.util.List;

public class SchedulePlanner {

	public static Schedule plan(
			List<Activity> activities,
			ScheduleConfiguration scheduleConfiguration
	) {
		Length2ActivitiesMap length2ActivitiesMap = new Length2ActivitiesMap(activities);

		length2ActivitiesMap.validate(scheduleConfiguration);

		List<List<Activity>> morningTracks = MorningTracksPlanner.extractTracks(length2ActivitiesMap, scheduleConfiguration);

		List<List<Activity>> eveningTracks = EveningTracksPlanner.extractTracks(length2ActivitiesMap, scheduleConfiguration);

		Schedule schedule = new Schedule(morningTracks, eveningTracks, scheduleConfiguration);

		return schedule;
	}

}

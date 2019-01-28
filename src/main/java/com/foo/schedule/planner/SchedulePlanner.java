package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.schedule.Schedule;
import com.foo.schedule.ScheduleConfiguration;

import java.util.List;

public class SchedulePlanner implements ScheduleConstants {

	public static Schedule plan(

			List<Activity> activities,

			ScheduleConfiguration scheduleConfiguration

	) {
		Duration2ActivitiesMap duration2ActivitiesMap = new Duration2ActivitiesMap(activities);

		duration2ActivitiesMap.validate(scheduleConfiguration);

		List<List<Activity>> morningTracks = MorningTracksPlanner.extractTracks(duration2ActivitiesMap, scheduleConfiguration);

		List<List<Activity>> eveningTracks = EveningTracksPlanner.extractTracks(duration2ActivitiesMap, scheduleConfiguration);

		Schedule schedule = new Schedule(morningTracks, eveningTracks, scheduleConfiguration);

		return schedule;
	}

}

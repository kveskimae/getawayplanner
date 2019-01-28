package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.exception.ScheduleException;
import com.foo.schedule.ScheduleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class Duration2ActivitiesMap implements ScheduleConstants {

	final TreeMap<Integer, List<Activity>> mins2Activities = new TreeMap<>();

	Duration2ActivitiesMap(List<Activity> activities) {

		activities.stream().forEach(

				activity -> {

					Integer time = new Long(activity.duration.toMinutes()).intValue();

					List<Activity> activitiesOfDuration;

					if (mins2Activities.containsKey(time)) {

						activitiesOfDuration = mins2Activities.get(time);

					} else {

						activitiesOfDuration = new ArrayList<>();

					}

					activitiesOfDuration.add(activity);

					mins2Activities.put(time, activitiesOfDuration);

				}
		);
	}

	int getTotalTime() {

		int totalActivitiesTime = mins2Activities.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue().size()).sum();

		return totalActivitiesTime;

	}

	void validate(ScheduleConfiguration configuration) {

		int minTotalMins = configuration.noOfTeams * (configuration.morningDurationMin + configuration.eveningDurationMin);

		int maxTotalMins = minTotalMins + configuration.noOfTeams * configuration.eveningFlexMin;

		int totalTime = getTotalTime();

		if (totalTime < minTotalMins || totalTime > maxTotalMins) {

			throw new ScheduleException(

					String.format(

							"Supplied activities total time %dmin does not fit in configured range [%d-%d]min",

							totalTime,

							minTotalMins,

							maxTotalMins

					)

			);

		}
	}

}

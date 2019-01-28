package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.exception.ScheduleException;
import com.foo.schedule.ScheduleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class Length2ActivitiesMap implements ScheduleConstants {

	final TreeMap<Integer, List<Activity>> mins2Count = new TreeMap<>();

	Length2ActivitiesMap(List<Activity> activities) {

		activities.stream().forEach(

				activity -> {

					Integer time = activity.mins;

					List<Activity> activitiesOfLength;

					if (mins2Count.containsKey(time)) {

						activitiesOfLength = mins2Count.get(time);

					} else {

						activitiesOfLength = new ArrayList<>();

					}

					activitiesOfLength.add(activity);

					mins2Count.put(time, activitiesOfLength);

				}
		);
	}

	int getTotalTime() {

		int totalActivitiesTime = mins2Count.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue().size()).sum();

		return totalActivitiesTime;

	}

	void validate(ScheduleConfiguration configuration) {

		int minTotalMins = configuration.noOfTeams * (configuration.morningLengthMin + configuration.eveningLengthMin);

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

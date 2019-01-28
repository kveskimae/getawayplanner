package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.schedule.ScheduleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

class EveningTracksPlanner implements ScheduleConstants {

	public static List<List<Activity>> extractTracks(Duration2ActivitiesMap duration2ActivitiesMap, ScheduleConfiguration scheduleConfiguration) {

		return partitionMaximallyEqualDurations(duration2ActivitiesMap.mins2Activities, scheduleConfiguration);

	}

	static List<List<Activity>> partitionMaximallyEqualDurations(

			TreeMap<Integer, List<Activity>> mins2Activities,

			ScheduleConfiguration scheduleConfiguration

	) {
		List<List<Activity>> ret = new ArrayList<>();

		for (int i = 0; i < scheduleConfiguration.noOfTeams; i++) {

			ret.add(new ArrayList<>());

		}

		List<Integer> minutesBig2Small = mins2Activities.descendingKeySet().stream().mapToInt(x -> x).boxed().collect(Collectors.toList());

		for (Integer minutes : minutesBig2Small) {

			while (mins2Activities.containsKey(minutes)) {

				Integer minIdx = findMinimumSumIdx(ret);

				Activity activity = mins2Activities.get(minutes).get(0);

				mins2Activities.get(minutes).remove(activity);

				ret.get(minIdx).add(activity);

				if (mins2Activities.get(minutes).isEmpty()) {

					mins2Activities.remove(minutes);

				}
			}
		}

		return ret;
	}

	private static Integer findMinimumSumIdx(List<List<Activity>> partitions) {
		int min = sumList(partitions.get(0)), ret = 0;
		for (int i = 1; i < partitions.size(); i++) {
			int s = sumList(partitions.get(i));
			if (s < min) {
				min = s;
				ret = i;
			}
		}
		return ret;
	}

	static int sumList(List<Activity> integers) {
		return integers.stream().mapToInt(x -> (int)x.duration.toMinutes()).sum();
	}

}

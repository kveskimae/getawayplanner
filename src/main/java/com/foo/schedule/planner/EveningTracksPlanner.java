package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.schedule.ScheduleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

class EveningTracksPlanner {

	public static List<List<Activity>> extractTracks(Length2ActivitiesMap length2ActivitiesMap, ScheduleConfiguration scheduleConfiguration) {
		return partitionMaximallyEqualLengths(length2ActivitiesMap.mins2Count, scheduleConfiguration);
	}

	static List<List<Activity>> partitionMaximallyEqualLengths(
			TreeMap<Integer, List<Activity>> mins2Count,
			ScheduleConfiguration scheduleConfiguration
	) {
		List<List<Activity>> ret = new ArrayList<>();
		for (int i = 0; i < scheduleConfiguration.noOfTeams; i++) {
			ret.add(new ArrayList<>());
		}
		List<Integer> minutesBig2Small = mins2Count.descendingKeySet().stream().mapToInt(x -> x).boxed().collect(Collectors.toList());
		for (Integer minutes : minutesBig2Small) {
			while (mins2Count.containsKey(minutes)) {
				Integer minIdx = findMinimumSumIdx(ret);
				Activity activity = mins2Count.get(minutes).get(0);
				mins2Count.get(minutes).remove(activity);
				ret.get(minIdx).add(activity);
				if (mins2Count.get(minutes).isEmpty()) {
					mins2Count.remove(minutes);
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
		return integers.stream().mapToInt(x -> x.mins).sum();
	}

}

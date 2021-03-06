package com.foo.schedule.planner;

import com.foo.activity.Activity;
import com.foo.exception.ScheduleException;
import com.foo.schedule.ScheduleConfiguration;

import java.util.*;
import java.util.stream.Collectors;

class MorningTracksPlanner implements ScheduleConstants {

	static List<List<Activity>> extractTracks(

			Duration2ActivitiesMap duration2ActivitiesMap,

			ScheduleConfiguration scheduleConfiguration
	) {

		List<List<Activity>> ret = new ArrayList<>();

		for (int i = 0; i < scheduleConfiguration.noOfTeams; i++) {

			List<Activity> morningTrack =

					findExactCombination(

							scheduleConfiguration.morningDurationMin,

							duration2ActivitiesMap.mins2Activities
					);

			ret.add(morningTrack);
		}

		return ret;
	}

	static List<Activity> findExactCombination(
			int requiredMins,

			TreeMap<Integer, List<Activity>> mins2Activities
	) {

		if (requiredMins < MINIMUM_MORNING_TRACK_LENGTH_MINS || requiredMins > MAXIMUM_MORNING_TRACK_LENGTH_MINS) {

			throw new ScheduleException(

					String.format(

							String.format(

									"Parameter morning track duration %dmin is out of range [%d-%d]min",

									requiredMins,

									MINIMUM_MORNING_TRACK_LENGTH_MINS,

									MAXIMUM_MORNING_TRACK_LENGTH_MINS

							),

							requiredMins,

							MINIMUM_MORNING_TRACK_LENGTH_MINS,

							MAXIMUM_MORNING_TRACK_LENGTH_MINS

					)

			);

		}

		if (mins2Activities == null) {

			throw new ScheduleException("Parameter time counts map is null");

		} else if (mins2Activities.isEmpty()) {

			throw new ScheduleException("Parameter time counts map is empty");

		}

		Optional<List<Activity>> maybeCombination = recursivelyFindExactCombination(requiredMins, mins2Activities, new ArrayList<Activity>());

		if (!maybeCombination.isPresent()) {

			throw new IllegalArgumentException("No combination found");

		}

		return maybeCombination.get();
	}

	private static Optional<List<Activity>> recursivelyFindExactCombination(int requiredMins, TreeMap<Integer, List<Activity>> mins2Activities, List<Activity> pathThisFar) {
		Collection<Integer> keys = mins2Activities.descendingKeySet().stream().mapToInt(x -> x).boxed().collect(Collectors.toList());

		for (Integer key : keys) {


			List<Activity> oldCount = mins2Activities.get(key);

			Activity activity = oldCount.get(0);

			oldCount.remove(activity);

			if (oldCount.isEmpty()) {

				mins2Activities.remove(key);

			}

			pathThisFar.add(activity);

			int time = pathThisFar.stream().mapToInt(x -> (int)x.duration.toMinutes()).sum();

			if (time == requiredMins) {

				return Optional.of(pathThisFar);

			} else if (time < requiredMins) {

				Optional<List<Activity>> found = recursivelyFindExactCombination(requiredMins, mins2Activities, pathThisFar);

				if (found.isPresent()) {

					return found;

				}
			}

			oldCount.add(activity);

			mins2Activities.put(key, oldCount);

			pathThisFar.remove(pathThisFar.size() - 1);
		}
		return Optional.empty();
	}

}

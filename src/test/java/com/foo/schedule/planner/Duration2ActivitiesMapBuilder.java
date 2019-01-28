package com.foo.schedule.planner;

import com.foo.activity.Activity;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Duration2ActivitiesMapBuilder {

	public static TreeMap<Integer, List<Activity>> buildMap(Pair<Integer, Integer>... counts) {
		TreeMap<Integer, List<Activity>> mins2Activities = new TreeMap<>();

		for (Pair<Integer, Integer> x : counts) {
			Integer minutes = x.getKey();
			Integer count = x.getValue();
			List<Activity> l = new ArrayList();
			for (int i = 0; i < count; i++) {
				Activity activity = new Activity(
						String.format("test %d'' %d", minutes, (i + 1)),
						Duration.of(minutes, ChronoUnit.MINUTES)
				);

				l.add(activity);
			}
			mins2Activities.put(minutes, l);
		}

		return mins2Activities;
	}

}

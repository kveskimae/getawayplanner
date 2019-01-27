package com.foo.schedule.planner;

import com.foo.activity.Activity;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Length2ActivitiesMapBuilder {

	public static TreeMap<Integer, List<Activity>> buildMap(Pair<Integer, Integer>... counts) {
		TreeMap<Integer, List<Activity>> mins2Count = new TreeMap<>();

		for (Pair<Integer, Integer> x : counts) {
			Integer minutes = x.getKey();
			Integer count = x.getValue();
			List<Activity> l = new ArrayList();
			for (int i = 0; i < count; i++) {
				Activity activity = new Activity(
						// "test 60'' 1"
						String.format("test %d'' %d",
								minutes,
								(i + 1)
						)
						, minutes);
				l.add(activity);
			}
			mins2Count.put(minutes, l);
		}

		return mins2Count;
	}

}

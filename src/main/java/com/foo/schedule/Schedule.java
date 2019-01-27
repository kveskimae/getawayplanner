package com.foo.schedule;

import com.foo.activity.Activity;
import com.foo.schedule.planner.SchedulePlanner;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class Schedule {

	public final List<List<Activity>>
			morningTracks,
			eveningTracks;

	public final ScheduleConfiguration configuration;

	public Schedule(List<List<Activity>> morningTracks, List<List<Activity>> eveningTracks, ScheduleConfiguration configuration) {
		this.morningTracks = morningTracks;
		this.eveningTracks = eveningTracks;
		this.configuration = configuration;
	}


	public static Schedule of(
			List<Activity> activities,
			ScheduleConfiguration scheduleConfiguration
	) {
		return SchedulePlanner.plan(activities, scheduleConfiguration);
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	@Override
	public boolean equals(Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

}

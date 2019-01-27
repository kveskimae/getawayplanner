package com.foo.schedule;

import com.foo.activity.Activity;
import com.foo.schedule.planner.SchedulePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleFacade {

	@Autowired
	ScheduleConfiguration configuration;

	public Schedule buildSchedule(List<Activity> activities) {

		return SchedulePlanner.plan(activities, configuration);

	}

	public String buildString(Schedule schedule) {

		return new ScheduleToStringBuilder(schedule, configuration).build();

	}

}

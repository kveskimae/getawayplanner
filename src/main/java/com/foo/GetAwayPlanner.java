package com.foo;

import com.foo.activity.ActivitiesFileReader;
import com.foo.activity.Activity;
import com.foo.schedule.Schedule;
import com.foo.schedule.ScheduleConfiguration;
import com.foo.schedule.ScheduleFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class GetAwayPlanner {

	@Autowired
	ScheduleConfiguration configuration;

	@Autowired
	ActivitiesFileReader activitiesFileReader;

	@Autowired
	ScheduleFacade scheduleFacade;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(GetAwayPlanner.class, args);

		GetAwayPlanner planner = ctx.getBean(GetAwayPlanner.class);

		System.out.println(planner.planGetAway());
	}

	public String planGetAway() {
		List<Activity> activities = activitiesFileReader.readActivitiesFromFile();

		Schedule schedule = scheduleFacade.buildSchedule(activities);

		String prettySchedule = scheduleFacade.buildString(schedule);

		return prettySchedule;
	}

}

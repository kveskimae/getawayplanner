package com.foo.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfiguration {

	@Value("${schedule.noOfTeams}")
	public int noOfTeams;

	@Value("${schedule.morning.start_hr}")
	public int morningStartHr;

	@Value("${schedule.morning.start_min}")
	public int morningStartMin;

	@Value("${schedule.morning.duration.min}")
	public int morningDurationMin;

	@Value("${schedule.lunch.duration.min}")
	public int lunchDurationMin;

	@Value("${schedule.evening.duration.min}")
	public int eveningDurationMin;

	@Value("${schedule.evening.flex.min}")
	public int eveningFlexMin;

}

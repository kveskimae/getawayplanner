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

	@Value("${schedule.morning.length.min}")
	public int morningLengthMin;

	@Value("${schedule.lunch.length.min}")
	public int lunchLengthMin;

	@Value("${schedule.evening.length.min}")
	public int eveningLengthMin;

	@Value("${schedule.evening.flex.min}")
	public int eveningFlexMin;

}

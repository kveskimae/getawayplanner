package com.foo.schedule.planner;

import com.foo.activity.ActivitiesFileReader;
import com.foo.activity.Activity;
import com.foo.exception.ScheduleException;
import com.foo.schedule.ScheduleConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Duration2ActivitiesMapTests {

	static List<Activity> activities;
	@Autowired
	ActivitiesFileReader activitiesFileReader;
	@Autowired
	ScheduleConfiguration configuration;

	@BeforeEach
	public void initialiseActivities() {
		activities = activitiesFileReader.readActivitiesFromFile();
	}

	@Test
	public void totalTimeInRangePasses() {
		Duration2ActivitiesMap duration2ActivitiesMap = new Duration2ActivitiesMap(activities);

		duration2ActivitiesMap.validate(configuration);
	}

	@Test
	public void totalTimeOutOfRangeFails() {
		Duration2ActivitiesMap duration2ActivitiesMap = new Duration2ActivitiesMap(Arrays.asList());
		duration2ActivitiesMap.mins2Activities.clear();
		duration2ActivitiesMap.mins2Activities.put(100, Arrays.asList(new Activity("test1", Duration.of(100, ChronoUnit.MINUTES))));

		assertThrows(ScheduleException.class,
				() -> {
					duration2ActivitiesMap.validate(configuration);
				});
	}
}

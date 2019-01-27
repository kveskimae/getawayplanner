package com.foo.schedule.planner;

import com.foo.activity.ActivitiesFileReader;
import com.foo.activity.Activity;
import com.foo.schedule.ScheduleConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Length2ActivitiesMapTests {

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
		Length2ActivitiesMap length2ActivitiesMap = new Length2ActivitiesMap(activities);

		length2ActivitiesMap.validate(configuration);
	}

	@Test
	public void totalTimeOutOfRangeFails() {
		Length2ActivitiesMap length2ActivitiesMap = new Length2ActivitiesMap(Arrays.asList());
		length2ActivitiesMap.mins2Count.clear();
		length2ActivitiesMap.mins2Count.put(100, Arrays.asList(new Activity("test1", 100)));

		assertThrows(IllegalArgumentException.class,
				() -> {
					length2ActivitiesMap.validate(configuration);
				});
	}
}

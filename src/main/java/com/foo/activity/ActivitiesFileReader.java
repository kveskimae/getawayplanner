package com.foo.activity;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivitiesFileReader {

	@Value("${activities.filename}")
	private Resource resource;

	public List<Activity> readActivitiesFromFile() {

		List<Activity> activities = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {

			String line;

			while ((line = br.readLine()) != null) {

				if (!StringUtils.isBlank(line)) {

					Activity activity = Activity.parseActivity(line);

					activities.add(activity);

				}

			}

		} catch (IOException e) {

			throw new RuntimeException(e);

		}

		return activities;
	}

}

package com.foo.activity;

import com.foo.exception.ActivityException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class ActivitiesFileReader implements ActivityConstants {

	@Value("${activities.filename}")
	private Resource resource;

	public List<Activity> readActivitiesFromFile() {

		if (!resource.exists()) {
			throw new ActivityException(
					String.format(
							"Activities file not found: %s",
							isNull(resource) ? "null" : resource.getDescription()
					)
			);
		}

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

			throw new ActivityException("An exception occurred while reading in the activities file", e);

		}

		return activities;
	}

}

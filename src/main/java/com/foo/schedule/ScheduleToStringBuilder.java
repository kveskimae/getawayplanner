package com.foo.schedule;

import com.foo.activity.Activity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;

public class ScheduleToStringBuilder {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

	private final Schedule schedule;

	private final ScheduleConfiguration configuration;

	private String result;

	private StringBuilder sb = new StringBuilder();

	private LocalTime nextStartTime, motivationPresentationStartTime;

	public ScheduleToStringBuilder(Schedule schedule, ScheduleConfiguration configuration) {

		this.schedule = schedule;

		this.configuration = configuration;

		this.motivationPresentationStartTime = findLatestEveningSessionEndTime();
	}

	private LocalTime findLatestEveningSessionEndTime() {
		LocalTime ret = null;

		for (int i = 0; i < configuration.noOfTeams; i++) {

			LocalTime startTime = getStartTime();

			 int allTracksMins = 0;

			 allTracksMins += schedule.morningTracks.get(i).stream().mapToInt(activity -> (int) activity.duration.toMinutes()).sum();

			 allTracksMins += configuration.lunchDurationMin;

			 allTracksMins += schedule.eveningTracks.get(i).stream().mapToInt(activity -> (int) activity.duration.toMinutes()).sum();

			LocalTime endTime = startTime.plusMinutes(allTracksMins);

			if (isNull(ret) || ret.isBefore(endTime)) {
				ret = endTime;
			}
		}

		return ret;
	}

	private LocalTime getStartTime() {
		return LocalTime.of(configuration.morningStartHr, configuration.morningStartMin);
	}

	public String build() {
		if (!isNull(result)) {

			return result;

		}

		for (int teamIdx = 0; teamIdx < configuration.noOfTeams; teamIdx++) {

			nextStartTime = getStartTime();

			sb.append(String.format("Team %d:\n", (teamIdx + 1)));

			addMorningTracks(teamIdx);

			addLunchBreak();

			addEveningTracks(teamIdx);

			addMotivationPresentation();

			sb.append("\n");
		}

		result = sb.toString();

		return result;
	}

	private void addMotivationPresentation() {

		sb.append(motivationPresentationStartTime.format(formatter));

		sb.append(" : ");

		sb.append("Staff Motivation Presentation").append("\n");
	}

	private void addEveningTracks(int teamIdx) {

		List<Activity> activities = schedule.eveningTracks.get(teamIdx);

		addActivities(activities);
	}

	private void addLunchBreak() {

		sb.append(nextStartTime.format(formatter));

		sb.append(" : ");

		sb.append(String.format("Lunch Break %dmin", configuration.lunchDurationMin));

		sb.append("\n");

		nextStartTime = nextStartTime.plusMinutes(configuration.lunchDurationMin);
	}

	private void addMorningTracks(int teamIdx) {

		List<Activity> activities = schedule.morningTracks.get(teamIdx);

		addActivities(activities);
	}

	private void addActivities(List<Activity> activities) {
		Iterator<Activity> it = activities.iterator();

		while (it.hasNext()) {

			Activity cur = it.next();

			sb.append(nextStartTime.format(formatter));

			sb.append(" : ");

			sb.append(cur.buildDisplayString()).append("\n");

			nextStartTime = nextStartTime.plusMinutes(cur.duration.toMinutes());

		}
	}


}

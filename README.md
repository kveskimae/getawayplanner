# Company Getaway Planner

This app plans a day of activities, which start in the morning with a morning track.

The company employees get divided into teams and each team will be performing various activities.

The morning track goes till lunch (for example, at noon).

Evening track continues after lunch and ends with the motivational presentation.

Teams (for example, team 1 and team 2) go through activities at the same time.

## Activities

Provided activities are by default in the file 

     resources/activities.txt


This is a text file with a list of activities:
* Activity lengths are either in minutes or 'sprint', which denotes 15 minutes;
* There can no gap between each activity in the morning track;
* Regarding previous: provided activities need to fill exactly for the lengths of different teams' morning tracks;
* Evening tracks are partitioned as equally as possible between the teams, but some teams may have to wait for the start of the evening presentation. 

## Configuration

The configuration for the planner is located in 

     resources/application.properties

The configuration parameters are following:
* activities.filename - file to search for activities;
* schedule.noOfTeams - no of teams to split activities between, default is 2;
* schedule.morning.start_hr - what hour the morning track starts;
* schedule.morning.start_min - what minute (after the starting hour) the morning track starts;
* schedule.morning.length.min - morning track length;
* schedule.lunch.length.min - length of lunch break;
* schedule.evening.length.min - evening track length;
* schedule.evening.flex.min - amount of flexible minutes after the evening track available for planning, before the motivational presentation starts.

## Schedule

The app runner creates a schedule for teams with the use of provided activities and configuration.

The runner starts from

    com.foo.GetAwayPlanner

The suggested schedule gets printed to program output.

This solution greedily uses the longer activities first, leaving short-timed activities for the day's end in the evening.

## Demo data

Using out-of-box configuration and activities:
* Activities start from 9am;
* Two teams go trough provided activities;
* Lunch is served at noon;
* Activities resume from 1pm and must finish in time for a presentation;
* The presentation can start no earlier than 4:00 and no later than 5:00;
* The presentation starts at 5:00 due to lots of activities.

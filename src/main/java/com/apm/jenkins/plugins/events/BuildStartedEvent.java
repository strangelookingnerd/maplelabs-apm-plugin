package com.apm.jenkins.plugins.events;

import com.apm.jenkins.plugins.DataModel.BuildData;

/**
 * This event should contain all the data to construct a build started event. With
 * the right message for APM.
 */
public class BuildStartedEvent extends AbstractAPMBuildEvent {

	public BuildStartedEvent(BuildData buildData) {
		super(buildData);

		int buildNumber = buildData.getBuildNumber(-1);
		String userId = buildData.getUserId();
		String jobName = buildData.getJobName("unknown");
		String buildUrl = buildData.getBuildUrl("unknown");
		
		// Build title
		// eg: `job_name build #1 started on hostname`
		String title = "Job " + jobName + " build #" + buildNumber + " started on " + super.getHost();
		setTitle(title);

		// Build Text
		// eg: User <userId> started the [job <jobName> with build number #<buildNumber>]"
		String text = "\nUser " + userId + " started the [job " + jobName + " build #" +
				buildNumber + "](" + buildUrl + ") " + "\n";
		setText(text);

		setPriority(Priority.LOW);
		setAlertType(AlertType.INFO);
	}
}


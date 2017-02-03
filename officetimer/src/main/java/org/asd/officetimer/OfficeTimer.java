package org.asd.officetimer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

import static org.asd.officetimer.messages.Messages.*;

/**
 * 
 * Main class encapsulating all the office timer responsibilities
 * 
 * 1. Start the timer when the user starts the program 2. Take display interval
 * as the input 3. At each interval, display the total time completed in office
 * 
 * @author abhay dandekar
 *
 */

public class OfficeTimer implements Callable<Integer> {

	final private Instant start, suggestedOutTime;
	final private long mandatoryOfficeHours = 0;
	final private long mandatoryOfficeMins = 1;
	final private StringBuilder buffer = new StringBuilder();

	public OfficeTimer() {
		this.start = Instant.now();
		this.suggestedOutTime = start.plus(mandatoryOfficeHours, ChronoUnit.HOURS).plus(mandatoryOfficeMins,
				ChronoUnit.MINUTES);
		System.out.println(msg2 + LocalDateTime.ofInstant(start, ZoneId.systemDefault()));
		System.out.println(msg3 + LocalDateTime.ofInstant(suggestedOutTime, ZoneId.systemDefault()));
	}

	public void displayOfficeInTime() {

		Duration diff = Duration.between(start, Instant.now());
		Duration remaining = Duration.between(Instant.now(), suggestedOutTime);

		buffer.setLength(0);
		if (Instant.now().isBefore(suggestedOutTime)) {
			buffer.append(msg);
			buffer.append(diff.toHours());
			buffer.append("hours ");
			buffer.append(diff.toMinutes() % 60);
			buffer.append("mins ");
			buffer.append(diff.getSeconds() % 60);
			buffer.append("secs ");
			buffer.append(System.lineSeparator());
			buffer.append("\t");
			buffer.append(msg4);
			buffer.append(remaining.toHours());
			buffer.append("hours ");
			buffer.append(remaining.toMinutes() % 60);
			buffer.append("mins ");
			buffer.append(remaining.getSeconds() % 60);
			buffer.append("secs ");
		} else {
			buffer.append(msg5);
		}
		System.out.println(buffer);
	}

	public Integer call() throws Exception {
		this.displayOfficeInTime();
		return null;
	}
}
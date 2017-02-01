package org.asd.officetimer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;

/**
 * 
 * Main class encapsulating all the office timer 
 * responsibilities
 * 
 * 1. Start the timer when the user starts the program
 * 2. Take display interval as the input 
 * 3. At each interval, display the total time completed in office
 * 
 * @author abhay dandekar
 *
 */

public class OfficeTimer implements Callable<Integer> {
	
	final private Instant start;
	final private long mandatoryOfficeHours = 7;
	final private long mandatoryOfficeMins = 30;
	final private String msg = "Your today's office in-time : ";
	final private String msg2 = "Your office check-in time is : ";
	final private String msg3 = "You can leave from office at : ";
	final private StringBuilder buffer = new StringBuilder();
		
	public OfficeTimer( ) {
		this.start = Instant.now();
		System.out.println(msg2 + LocalDateTime.ofInstant(start, ZoneId.systemDefault()));
		System.out.println(msg3 + LocalDateTime.ofInstant(start.plus(mandatoryOfficeHours, ChronoUnit.HOURS).plus(mandatoryOfficeMins, ChronoUnit.MINUTES), ZoneId.systemDefault()));
	}
	
	public void displayOfficeInTime(){

		Duration diff = Duration.between(start, Instant.now());
		buffer.setLength(0);
		buffer.append(msg);
		buffer.append(diff.toHours());
		buffer.append("hours ");
		buffer.append(diff.toMinutes() % 60);
		buffer.append("mins ");
		buffer.append(diff.getSeconds() % 60 );
		buffer.append("secs");
		System.out.println(buffer);
		// System.out.println(msg + " " + diff.toMinutes() + " mins " + " OR " + ( diff.getSeconds() % 60 )  + " secs");
	}

	public Integer call() throws Exception {
		this.displayOfficeInTime();
		return null;
	}
}

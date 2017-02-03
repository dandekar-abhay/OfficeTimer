package org.asd.officetimer;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static int defaultDisplayIntervalMin = 1;
	
	public static void main(String[] args) throws Exception {

		if (  args.length > 1 ){
			System.out.println("Usage : java -jar officetimer-0.0.1-SNAPSHOT.jar <Interval time, default "+defaultDisplayIntervalMin+" mins>");
			System.exit(-1);
		}
		
		if ( args.length == 1 ){
			try {
				defaultDisplayIntervalMin = Integer.parseInt(args[0]);
				System.out.println("Using the user provided interval of " + defaultDisplayIntervalMin + " mins");
			} catch (Exception e) {
				System.out.println("Could not parse the time interval " + args[0]);
			}
		}else if ( args.length == 0 ){
			System.out.println("Using default display time " + defaultDisplayIntervalMin + " mins");
		}
		
		final OfficeTimer officeTimer = new OfficeTimer();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask () {
			@Override
			public void run() {
				try {
					officeTimer.call();
				} catch (Exception e) {
					System.err.println("Exception while calling the timer");
					e.printStackTrace(System.err);
				}
			}
		};
		
		// 1 sec tester
		timer.schedule(task, 1000, 1000 );
		// timer.schedule(task, 1000, defaultDisplayIntervalMin * 60 * 1000);
	}
}

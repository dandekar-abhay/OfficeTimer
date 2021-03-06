package org.asd.officetimer;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static int defaultDisplayIntervalMin = 1;
	private static long defaultOffsetTimeMin = 0;
	
	
	public static void help(){
		System.out.println("Usage : java -jar officetimer-0.0.1-SNAPSHOT.jar <Interval time, default "+defaultDisplayIntervalMin+" mins> "
				+ "<offsetTime, default "+defaultOffsetTimeMin+" mins>");
		
		System.out.println("You can change the default mandatory times. Export the following env variables");
		System.out.println("TIMER_MANDATE_HOURS=<newMandatoryHours>");
		System.out.println("TIMER_MANDATE_MINS=<newMandatoryMinutes>");
		System.exit(-1);		
	}
	
	public static void main(String[] args) throws Exception {

		if (  args.length > 2){
			help();
		}else if ( args.length == 1 && "--help".equals(args[0])){
			help();
		}
		
		if (args.length == 2){
			defaultDisplayIntervalMin = Integer.parseInt(args[0]);
			defaultOffsetTimeMin = Long.parseLong(args[1]);
		}
		else if ( args.length == 1 ){
			try {
				defaultDisplayIntervalMin = Integer.parseInt(args[0]);
				
			} catch (Exception e) {
				System.out.println("Could not parse the time interval " + args[0]);
			}
		}
		
		System.out.println("Using display interval of " + defaultDisplayIntervalMin + " mins");
		System.out.println("Using offset time of " + defaultOffsetTimeMin + " mins");
		
		final OfficeTimer officeTimer = new OfficeTimer(defaultOffsetTimeMin);
		
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
		// timer.schedule(task, 1000, 1000 );
		timer.schedule(task, 1000, defaultDisplayIntervalMin * 60 * 1000);
	}
}

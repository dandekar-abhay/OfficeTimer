//package org.asd.officetimer.notifications.impl;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Map;
//
//import org.asd.officetimer.notifications.Notifier;
//
//public class LinuxGnomeNotifier extends Notifier {
//	
//	public void systemNotify(String message, int timeForMessage) throws Exception {
//
//		ProcessBuilder processBuilder = new ProcessBuilder("/usr/bin/notify-send", "-t", Integer.toString(timeForMessage),
//				message);
//		Map<String, String> env = processBuilder.environment();
//		env.put("DISPLAY", "localhost:0.0");
//		processBuilder.directory(new File("/tmp"));
//		Process p = null;
//		try {
//			System.out.println("Processing command : " + processBuilder.command());
//			p = processBuilder.start();
//			// System.out.println("Waiting for process to complete");
//			p.waitFor();
//			// System.out.println("Notification process completed");
//			if (p.exitValue() == 0) {
//				System.out.println("Notification sent successfully");
//			} else {
//				System.out.println("Notification sending problem " + p.exitValue());
//				System.out.println("Output msg : " + dumpStream(p.getInputStream()));
//				System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
//			}
//		} catch (InterruptedException ie) {
//			System.out.println("Some issue in notification process execution");
//			ie.printStackTrace();
//			System.out.println("Output msg : " + dumpStream(p.getInputStream()));
//			System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
//		} catch (IOException e) {
//			System.out.println("Some issue in notification process execution");
//			e.printStackTrace();
//			System.out.println("Output msg : " + dumpStream(p.getInputStream()));
//			System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
//			
//		}
//	}
//}
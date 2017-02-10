package org.asd.trashcode;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {

		// WORKING CODE FOR LINUX GNNOME NOTIFICATON
		/*
		ProcessBuilder processBuilder = new ProcessBuilder("/usr/bin/notify-send", "-t", Integer.toString(5),
				"Times up");
		Map<String, String> env = processBuilder.environment();
		env.put("DISPLAY", "localhost:0.0");
		processBuilder.directory(new File("/tmp"));
		Process p = null;
		try {
			System.out.println("Processing command : " + processBuilder.command());
			p = processBuilder.start();
			System.out.println("Waiting for process to complete");
			p.waitFor();
			System.out.println("Notification process completed");
			if (p.exitValue() == 0) {
				System.out.println("Notification sent successfully");
			} else {
				System.out.println("Notification sending problem " + p.exitValue());
				System.out.println("Output msg : " + dumpStream(p.getInputStream()));
				System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
			}
		} catch (InterruptedException ie) {
			System.out.println("Some issue in notification process execution");
			ie.printStackTrace();
			System.out.println("Output msg : " + dumpStream(p.getInputStream()));
			System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
		} catch (IOException e) {
			System.out.println("Some issue in notification process execution");
			e.printStackTrace();
			System.out.println("Output msg : " + dumpStream(p.getInputStream()));
			System.out.println("Error msg : " + dumpStream(p.getErrorStream()));
			
		}
		*/
		
		// Code using java notification
		if (SystemTray.isSupported()) {
			System.out.println("Wow its Supported !!!!");
			
		       SystemTray tray = SystemTray.getSystemTray();

		        //If the icon is a file
		        // Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
		        //Alternative (if the icon is on the classpath):
		        File iconFile = new File("icons/bulb.gif");
		        System.out.println("Iconfile found : " + iconFile.exists() );
		        		        
		        System.exit(0);
		        
		        Image image = Toolkit.getDefaultToolkit().createImage("resources/bulb.gif");
		        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
		        //Let the system resizes the image if needed
		        trayIcon.setImageAutoSize(true);
		        //Set tooltip text for the tray icon
		        trayIcon.setToolTip("System tray icon demo");
		        tray.add(trayIcon);
		        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
			
//            TrayIconDemo td = new TrayIconDemo();
//            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
		
	}

	static String dumpStream(InputStream inputStream) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.getProperty("line.separator"));
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

}

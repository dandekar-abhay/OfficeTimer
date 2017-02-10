package org.asd.officetimer.notifications;

import java.awt.SystemTray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.asd.officetimer.notifications.impl.JavaTrayIconNotifier;

/**
 * Interface for notifications
 * @author abhay
 *
 */

public abstract class Notifier {

	public abstract void systemNotify(String message, int timeForMessage) throws Exception;
	
	protected String dumpStream(InputStream inputStream) throws IOException{
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
	
	/**
	 * Static entry point for notifiers
	 * @return
	 */
	public static Notifier getNotifier(){
		if (SystemTray.isSupported()) {
			return new JavaTrayIconNotifier();
		}
		return new JavaTrayIconNotifier();
		
//		if (System.getProperty("os.version").toLowerCase() != "linux"){
//			return new LinuxGnomeNotifier();
//		}else {
//			return new LinuxGnomeNotifier();
//		}
	}
}

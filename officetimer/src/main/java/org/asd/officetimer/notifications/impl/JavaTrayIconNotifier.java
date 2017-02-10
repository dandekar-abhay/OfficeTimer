package org.asd.officetimer.notifications.impl;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.asd.officetimer.notifications.Notifier;

public class JavaTrayIconNotifier extends Notifier {

	@Override
	public void systemNotify(String message, int timeForMessage) throws Exception {

		SystemTray tray = SystemTray.getSystemTray();
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icons/bulb.png"));

			TrayIcon trayIcon = new TrayIcon(image, "OfficeTimerTray");
			trayIcon.setImageAutoSize(true);
			// Set tooltip text for the tray icon
			trayIcon.setToolTip("Party Time .. Go home, Enjoy");
			tray.add(trayIcon);
			trayIcon.displayMessage("Times Up", "Time-up, you can go home, chill !!! :)", MessageType.INFO);
			
		} catch (NullPointerException npe) {
			// do nothing
			// need to fix this issue, its not getting the icon from resources

		}
		
	}
}

package com.bfi.pfe.EBanking.client;

import com.bfi.pfe.EBanking.client.ui.AuthenticationScreen;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class EBanking implements EntryPoint {

	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			public void onUncaughtException(Throwable t) {
				System.err.println("--- UNCAUGHT EXCEPTION ---");
				t.printStackTrace();
			}
		});

		AuthenticationScreen authentification = new AuthenticationScreen();
		authentification.createForm().show();
	}
}

package com.bfi.pfe.EBanking.client.ui;

import com.bfi.pfe.EBanking.shared.model.User;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainArea extends VLayout {

	private Label label;

	public MainArea(User collab) {

		super();
		label = new Label();

		label.setContents("Main Area");
		label.setAlign(Alignment.CENTER);
		label.setOverflow(Overflow.HIDDEN);

	}

}
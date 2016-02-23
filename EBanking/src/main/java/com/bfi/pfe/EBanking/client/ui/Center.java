package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.Layout;

public class Center extends Layout {
	Center() {
		super();
		this.setAlign(Alignment.CENTER);
		this.setHeight100();
		this.setWidth100();
		// this.setShowEdges(true);
	}

	public void createForm(Canvas canvas) {

		this.setMembers(canvas);

	}
}

package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CreditCardRequestListGrid extends ListGrid {

	public CreditCardRequestListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField account = new ListGridField("account", "Account", 270);
		ListGridField type = new ListGridField("type", "Type", 140);
		ListGridField requestDate = new ListGridField("dateDemande",
				"Request date", 140);
		ListGridField status = new ListGridField("status", "Status", 140);

		setFields(account, type, requestDate, status);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}

}

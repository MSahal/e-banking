package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CheckListRequestGrid extends ListGrid {

	public CheckListRequestGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField account = new ListGridField("account", "Account", 200);
		ListGridField date = new ListGridField("dateRemise", "Remittante date",
				120);
		ListGridField status = new ListGridField("status", "Status", 70);
		ListGridField type = new ListGridField("type", "Type", 100);
		ListGridField amount = new ListGridField("amount", "Amount", 100);
		ListGridField benificiary = new ListGridField("benificiary",
				"Benificiary", 140);

		setFields(account, amount, type, date, status, benificiary);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}

}

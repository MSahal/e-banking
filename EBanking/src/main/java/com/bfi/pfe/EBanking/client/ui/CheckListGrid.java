package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CheckListGrid extends ListGrid {

	public CheckListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField account = new ListGridField("account", "Account", 270);
		ListGridField number = new ListGridField("number", "Number", 270);
		ListGridField date = new ListGridField("dateRemise", "Remittante date",
				140);
		ListGridField type = new ListGridField("type", "Type", 140);
		ListGridField isCrossed = new ListGridField("isCrossed", "Crossed", 140);
		ListGridField amount = new ListGridField("amount", "Amount", 140);

		setFields(account, number, amount, type, date, isCrossed);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}

}

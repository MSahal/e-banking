package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class BenificiaryListGrid extends ListGrid {

	public BenificiaryListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField name = new ListGridField("name", "Name", 270);
		ListGridField rib = new ListGridField("rib", "RIB", 270);
		ListGridField account = new ListGridField("account", "Account", 140);
		ListGridField status = new ListGridField("status", "Statut", 140);

		setFields(name, rib, account, status);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}
}

package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class AccountsListGrid extends ListGrid {

	public AccountsListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField id = new ListGridField("id", "Internal Identifier", 60);
		ListGridField rib = new ListGridField("rib", "RIB", 270);
		ListGridField openingDate = new ListGridField("openingDate",
				"Opening Date", 140);
		ListGridField type = new ListGridField("type", "Type", 140);
		ListGridField solde = new ListGridField("solde", "Balance", 140);

		setFields(id, rib, openingDate, type, solde);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}
}

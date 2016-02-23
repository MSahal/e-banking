package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class TransferListGrid extends ListGrid {

	public TransferListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField rib = new ListGridField("rib", "RIB", 270);
		ListGridField benificiary = new ListGridField("benificiary",
				"Benificiary", 140);
		ListGridField solde = new ListGridField("solde", "Balance", 140);
		ListGridField transferDate = new ListGridField("transferDate",
				"Transfer Date", 140);
		ListGridField status = new ListGridField("status", "Status", 140);

		setFields(rib, benificiary, solde, transferDate, status);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}
}

package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CreditCardListGrid extends ListGrid {

	public CreditCardListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField account = new ListGridField("account", "Account", 200);
		ListGridField number = new ListGridField("number", "Number", 140);
		ListGridField type = new ListGridField("type", "Type", 140);
		ListGridField dateEnd = new ListGridField("dateEnd", "Validity date",
				200);
		ListGridField status = new ListGridField("status", "Status", 180);

		setFields(account, number, type, dateEnd, status);
		setCanEdit(false);
		setSelectionType(SelectionStyle.SINGLE);

	}

}

package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class TransactionListGrid extends ListGrid {

	Label totalsLabel;

	public TransactionListGrid() {

	}

	public void createForm() {

		setWidth(768);
		setHeight(310);

		setLayoutAlign(Alignment.CENTER);
		setShowAllRecords(true);
		ListGridField date = new ListGridField("date", "Date", 140);
		ListGridField balance = new ListGridField("balance", "Balance", 270);
		ListGridField gived = new ListGridField("gived", "Benificiary", 270);
		ListGridField type = new ListGridField("type", "Type", 140);

		ComboBoxItem groupItem = new ComboBoxItem();
		groupItem.setAddUnknownValues(false);
		date.setType(ListGridFieldType.INTEGER);
		date.setCellAlign(Alignment.LEFT);
		setFields(date, balance, gived, type);
		gived.setEditorType(groupItem);
		setCanEdit(false);
		setAutoSaveEdits(false);
		setSelectionType(SelectionStyle.SINGLE);
		setAlign(Alignment.CENTER);

	}
}

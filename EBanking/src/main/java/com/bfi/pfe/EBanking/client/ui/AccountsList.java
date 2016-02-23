package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class AccountsList extends VLayout {
	private DynamicForm form = null;
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private AccountsListGrid accountListGrid = null;
	private UserDTO user;

	public AccountsList(UserDTO user) {
		form = new DynamicForm();
		form.setLayoutAlign(Alignment.CENTER);
		accountListGrid = new AccountsListGrid();
		accountListGrid.createForm();
		this.user = user;

	}

	public void createForm()

	{

		// data to return

		// getting all accounts
		ebankingService.getAccounts(user,
				new AsyncCallback<List<AccountDTO>>() {

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<AccountDTO> result) {

						if (accountListGrid != null
								&& accountListGrid.getRecords() != null
								&& accountListGrid.getRecords().length > 0) {

							for (ListGridRecord data : accountListGrid
									.getRecords()) {
								accountListGrid.removeData(data);
							}
						}

						if (result != null && !result.isEmpty()) {
							for (AccountDTO account : result) {
								ListGridRecord record = new ListGridRecord();
								record.setAttribute("id", account.getId());
								record.setAttribute("rib", account.getRib());
								record.setAttribute("openingDate",
										account.getOpeningDate());
								record.setAttribute("type", account.getType());
								record.setAttribute("solde", account.getSolde());
								accountListGrid.addData(record);
							}

						}

					}

				});

		SectionStackSection section = new SectionStackSection("Accounts List");
		section.setItems(accountListGrid);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(765);
		sectionStack.setHeight(accountListGrid.getHeight()
				+ accountListGrid.getHeight() / 10);
		sectionStack.setSections(section);

		accountListGrid.setCanHover(true);
		accountListGrid.setShowHover(true);
		accountListGrid.setShowHoverComponents(true);
		accountListGrid.setAlternateRecordStyles(true);
		accountListGrid.setAutoFetchData(true);

		setMembers(sectionStack);
		setMembersMargin(4);
		setAnimateMembers(true);
		setAutoHeight();
		setAutoWidth();
	}

}

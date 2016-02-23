package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.CreditCardRequestDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class CreditCardRequestList extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private CreditCardRequestListGrid creditCardListGrid = null;
	private UserDTO user;

	public CreditCardRequestList(UserDTO user) {
		creditCardListGrid = new CreditCardRequestListGrid();
		creditCardListGrid.createForm();
		this.user = user;

	}

	public void createForm()

	{

		if (creditCardListGrid != null
				&& creditCardListGrid.getRecords() != null
				&& creditCardListGrid.getRecords().length > 0) {

			for (ListGridRecord data : creditCardListGrid.getRecords()) {
				creditCardListGrid.removeData(data);
			}
		}

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

						if (result != null && !result.isEmpty()) {
							for (AccountDTO account : result) {
								ebankingService.getListofCreditCardRequestByAccountId(
										account.getId(),
										new AsyncCallback<List<CreditCardRequestDTO>>() {

											@Override
											public void onFailure(Throwable arg0) {
												// TODO Auto-generated method
												// stub

											}

											@Override
											public void onSuccess(
													List<CreditCardRequestDTO> result) {
												for (CreditCardRequestDTO card : result) {
													// TODO Auto-generated
													// method stub
													ListGridRecord record = new ListGridRecord();

													record.setAttribute(
															"account",
															card.getAccount()
																	.getRib());
													record.setAttribute("type",
															card.getType());
													record.setAttribute(
															"status",
															card.getStatus());
													record.setAttribute(
															"dateDemande",
															card.getDateDemande());
													creditCardListGrid
															.addData(record);
												}

											}
										});
							}

						}

					}

				});

		SectionStackSection section = new SectionStackSection(
				"Credit Card List");
		section.setItems(creditCardListGrid);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(765);
		sectionStack.setHeight(creditCardListGrid.getHeight()
				+ creditCardListGrid.getHeight() / 10);
		sectionStack.setSections(section);

		creditCardListGrid.setCanHover(true);
		creditCardListGrid.setShowHover(true);
		creditCardListGrid.setShowHoverComponents(true);
		creditCardListGrid.setAlternateRecordStyles(true);
		creditCardListGrid.setAutoFetchData(true);

		setMembers(sectionStack);
		setMembersMargin(4);
		setAnimateMembers(true);
		setAutoHeight();
		setAutoWidth();
	}

}

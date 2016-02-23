package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.TransferRequestDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class TransferList extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransferListGrid transferGrid = null;
	private ComboBoxItem account;

	public TransferList(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transferGrid = new TransferListGrid();
		transferGrid.createForm();
		this.user = user;

	}

	public void createForm() {

		if (transferGrid != null && transferGrid.getRecords() != null
				&& transferGrid.getRecords().length > 0) {

			for (ListGridRecord data : transferGrid.getRecords()) {
				transferGrid.removeData(data);
			}
		}

		account = new ComboBoxItem("account", "Account/RIB");
		ebankingService.getAccounts(user,
				new AsyncCallback<List<AccountDTO>>() {

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<AccountDTO> result) {
						String[] accountLov = new String[result.size()];
						int i = 0;
						// TODO Auto-generated method stub
						if (result != null && !result.isEmpty()) {
							for (AccountDTO accountDto : result) {
								accountLov[i] = (accountDto.getId() + "  /  " + accountDto
										.getRib()).toString();
								i++;
							}

						}
						account.setValueMap(accountLov);

					}

				});

		final DynamicForm form = new DynamicForm();

		account.setType("comboBox");
		account.setRequired(true);

		IButton search = new IButton("Search");
		search.setLayoutAlign(Alignment.RIGHT);

		final AccountDTO accountDto = new AccountDTO();
		final DateTimeFormat fmt = DateTimeFormat
				.getFormat("yyyy-MM-dd hh:mm:ss");

		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (transferGrid != null && transferGrid.getRecords() != null
						&& transferGrid.getRecords().length > 0) {

					for (ListGridRecord data : transferGrid.getRecords()) {
						transferGrid.removeData(data);
					}
				}

				String accountId = account.getValueAsString();
				if (accountId != null) {
					accountId = accountId.substring(0,
							accountId.indexOf('/') - 2);
				}

				accountDto.setId(Long.valueOf(accountId).longValue());
				ebankingService.getTransferListByAccount(Long
						.valueOf(accountId).longValue(),
						new AsyncCallback<List<TransferRequestDTO>>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(
									List<TransferRequestDTO> result) {
								if (result != null && !result.isEmpty()) {
									for (TransferRequestDTO transaction : result) {
										ListGridRecord record = new ListGridRecord();
										record.setAttribute("transferDate", fmt
												.format(transaction
														.getTransferDate()));
										record.setAttribute("solde",
												transaction.getSolde());
										record.setAttribute("rib", transaction
												.getBenificiary().getRib());
										record.setAttribute("benificiary",
												transaction.getBenificiary()
														.getFullName());
										record.setAttribute("status",
												transaction.getStatus());

										transferGrid.addData(record);

									}

								}

							}
						});

				// TODO Auto-generated method stub

			}
		});

		HLayout buttons = new HLayout();
		buttons.setLayoutAlign(Alignment.CENTER);
		buttons.addMember(search);
		buttons.setMargin(10);

		buttons.setMembersMargin(10);
		VLayout layout = new VLayout();
		layout.setAutoHeight();
		layout.setMembersMargin(10);
		layout.addMember(buttons);

		form.setFields(account);
		form.setTitleAlign(Alignment.LEFT);
		form.setMargin(10);
		VLayout searchCriteria = new VLayout();
		searchCriteria.setAutoWidth();
		searchCriteria.setMargin(10);

		searchCriteria.setIsGroup(true);
		searchCriteria.setWidth(740);
		searchCriteria.setHeight(60);
		searchCriteria.setPadding(5);
		searchCriteria.setAlign(Alignment.CENTER);

		searchCriteria.setMembers(form, layout);

		SectionStackSection section = new SectionStackSection(
				"Transactions List");
		section.setItems(searchCriteria, transferGrid);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(768);
		sectionStack.setHeight(transferGrid.getHeight()
				+ transferGrid.getHeight() / 10);
		sectionStack.setSections(section);
		sectionStack.setLayoutAlign(Alignment.CENTER);
		setMembers(sectionStack);

	}
}

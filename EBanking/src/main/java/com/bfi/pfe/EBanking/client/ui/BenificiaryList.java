package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.BenificiaryDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
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

public class BenificiaryList extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private BenificiaryListGrid benificiaryListGrid = null;
	private ComboBoxItem account;
	private UserDTO user;

	public BenificiaryList(UserDTO user) {
		benificiaryListGrid = new BenificiaryListGrid();
		benificiaryListGrid.createForm();
		setEdgeSize(1);
		setShowEdges(true);
		this.user = user;

	}

	public void createForm()

	{

		if (benificiaryListGrid != null
				&& benificiaryListGrid.getRecords() != null
				&& benificiaryListGrid.getRecords().length > 0) {

			for (ListGridRecord data : benificiaryListGrid.getRecords()) {
				benificiaryListGrid.removeData(data);
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

		// data to return

		// getting all accounts
		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (benificiaryListGrid != null
						&& benificiaryListGrid.getRecords() != null
						&& benificiaryListGrid.getRecords().length > 0) {

					for (ListGridRecord data : benificiaryListGrid.getRecords()) {
						benificiaryListGrid.removeData(data);
					}
				}

				String accountId = account.getValueAsString();
				if (accountId != null) {
					accountId = accountId.substring(0,
							accountId.indexOf('/') - 2);
				}

				accountDto.setId(Long.valueOf(accountId).longValue());
				ebankingService.getAllBenificiariesByAccount(
						Long.valueOf(accountId),
						new AsyncCallback<List<BenificiaryDTO>>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(List<BenificiaryDTO> result) {

								if (benificiaryListGrid != null
										&& benificiaryListGrid.getRecords() != null
										&& benificiaryListGrid.getRecords().length > 0) {

									for (ListGridRecord data : benificiaryListGrid
											.getRecords()) {
										benificiaryListGrid.removeData(data);
									}
								}

								if (result != null && !result.isEmpty()) {
									for (BenificiaryDTO account : result) {
										ListGridRecord record = new ListGridRecord();
										record.setAttribute("rib",
												account.getRib());
										record.setAttribute("name",
												account.getFullName());
										record.setAttribute("status",
												account.getStatus());
										record.setAttribute("account", account
												.getAccount().getRib());
										benificiaryListGrid.addData(record);
									}

								}

							}

						});

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
				"Benificiary List");
		section.setItems(searchCriteria, benificiaryListGrid);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(768);
		sectionStack.setHeight(benificiaryListGrid.getHeight()
				+ benificiaryListGrid.getHeight() / 10);
		sectionStack.setSections(section);
		sectionStack.setLayoutAlign(Alignment.CENTER);
		setMembers(sectionStack);
	}
}

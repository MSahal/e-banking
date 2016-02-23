package com.bfi.pfe.EBanking.client.ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.TransactionDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class TransactionList extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransactionListGrid transactionGrid = null;
	private ComboBoxItem account;
	private DateItem startDate;
	private DateItem endDate;

	public TransactionList(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transactionGrid = new TransactionListGrid();
		transactionGrid.createForm();
		this.user = user;

	}

	public void createForm() {

		final List<TransactionDTO> transactionsList = new ArrayList<TransactionDTO>();

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
		account.setRequiredMessage("Invalid account");
		startDate = new DateItem("groupe", "<nobr>Start date</nobr>");
		startDate.setRequired(true);
		endDate = new DateItem("profil", "End date");
		endDate.setType("date");

		endDate.setRequired(true);
		endDate.setRequiredMessage("Profil Invalide");

		IButton search = new IButton("Search");
		search.setLayoutAlign(Alignment.RIGHT);

		final AccountDTO accountDto = new AccountDTO();
		final DateTimeFormat fmt = DateTimeFormat
				.getFormat("yyyy-MM-dd hh:mm:ss");

		search.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (transactionGrid != null
						&& transactionGrid.getRecords() != null
						&& transactionGrid.getRecords().length > 0) {

					for (ListGridRecord data : transactionGrid.getRecords()) {
						transactionGrid.removeData(data);
					}
				}

				String accountId = account.getValueAsString();
				if (accountId != null) {
					accountId = accountId.substring(0,
							accountId.indexOf('/') - 2);
				}

				Timestamp start = new Timestamp(startDate.getValueAsDate()
						.getTime());
				Timestamp end = new Timestamp(endDate.getValueAsDate()
						.getTime());
				accountDto.setId(Long.valueOf(accountId).longValue());
				ebankingService.getTransactions(accountDto, start.toString(),
						end.toString(),
						new AsyncCallback<List<TransactionDTO>>() {

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(List<TransactionDTO> result) {
								if (result != null && !result.isEmpty()) {
									for (TransactionDTO transaction : result) {
										transactionsList.add(transaction);
										ListGridRecord record = new ListGridRecord();
										record.setAttribute("date", fmt
												.format(transaction.getDate()));
										record.setAttribute("balance",
												transaction.getMontant());
										record.setAttribute("giver",
												transaction.getOrdonnateur());
										record.setAttribute("gived",
												transaction.getBenificiaire());
										record.setAttribute("type",
												transaction.getType());

										transactionGrid.addData(record);

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
		buttons.setMargin(30);

		IButton print = new IButton("Print");
		print.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ebankingService.printRapport(user, startDate.getValueAsDate()
						.toString(), endDate.getValueAsDate().toString(), null,
						transactionsList, new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void arg0) {
								// TODO Auto-generated method stub
								SC.say("Document generated successfuly in C:");

							}

							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub
								SC.say("Internal error");

							}
						});
			}
		});

		print.setLayoutAlign(Alignment.CENTER);
		print.setShowRollOver(true);
		print.setShowDisabled(true);
		print.setShowDown(true);
		buttons.addMember(print);
		buttons.setMembersMargin(10);
		VLayout layout = new VLayout();
		layout.setAutoHeight();
		layout.setMembersMargin(30);
		layout.addMember(buttons);

		form.setFields(account, startDate, endDate);
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
		section.setItems(searchCriteria, transactionGrid);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(768);
		sectionStack.setHeight(transactionGrid.getHeight()
				+ transactionGrid.getHeight() / 10);
		sectionStack.setSections(section);
		sectionStack.setLayoutAlign(Alignment.CENTER);
		setMembers(sectionStack);

	}
}

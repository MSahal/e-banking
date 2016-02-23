package com.bfi.pfe.EBanking.client.ui;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.Benificiary;
import com.bfi.pfe.EBanking.shared.model.TransferRequest;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
import com.bfi.pfe.EBanking.shared.model.dto.BenificiaryDTO;
import com.bfi.pfe.EBanking.shared.model.dto.UserDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormErrorOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class TransferForm extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransactionListGrid transactionGrid = null;
	private DynamicForm form;
	private DynamicForm form2;
	private DynamicForm form3;
	static TransferRequest transferRequestDTO = null;
	static DateItem dateTransfer = new DateItem("dateTransfer", "Transfer date");
	static TextItem balance = new TextItem("balance", "Balance");;

	public TransferForm(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transactionGrid = new TransactionListGrid();
		transactionGrid.createForm();
		transferRequestDTO = new TransferRequest();
		this.user = user;
		form = buildForm();
		form2 = buildForm2();
		form3 = buildForm3();

	}

	public void createForm() {

		form.setGroupTitle("Debit account");
		form.setIsGroup(true);
		form.setWidth(740);
		form.setHeight(60);
		form.setPadding(5);
		form.setAlign(Alignment.CENTER);

		form2.setGroupTitle("Benificiary account");
		form2.setIsGroup(true);
		form2.setWidth(740);
		form2.setHeight(60);
		form2.setPadding(5);
		form2.setAlign(Alignment.CENTER);

		form3.setGroupTitle("Transfer amount");
		form3.setIsGroup(true);
		form3.setWidth(740);
		form3.setHeight(60);
		form3.setPadding(5);
		form3.setAlign(Alignment.CENTER);
		// create button and add handler
		Button registerButton = new Button("Save");
		registerButton.setAlign(Alignment.CENTER);
		registerButton.addClickHandler(new RegisterButtonClickHandler());

		VLayout forms = new VLayout();
		forms.setAutoWidth();
		forms.setMembersMargin(20);
		forms.setMargin(20);

		forms.setMembers(form, form2, form3, registerButton);

		SectionStackSection section = new SectionStackSection("New transfer");
		section.setItems(forms);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(768);

		sectionStack.setSections(section);
		sectionStack.setLayoutAlign(Alignment.CENTER);
		setMembers(sectionStack);

	}

	private DynamicForm buildForm2() {
		DynamicForm form = new DynamicForm();
		form.setWidth(768);
		form.setStyleName("formLayout");
		form.setErrorOrientation(FormErrorOrientation.RIGHT);

		// create form fields
		final ComboBoxItem accountToDebit = new ComboBoxItem("account", "RIB");

		ebankingService
				.getAllBenificiaries(new AsyncCallback<List<BenificiaryDTO>>() {

					@Override
					public void onFailure(Throwable arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(List<BenificiaryDTO> result) {
						String[] accountLov = new String[result.size()];
						int i = 0;
						// TODO Auto-generated method stub
						if (result != null && !result.isEmpty()) {
							for (BenificiaryDTO accountDto : result) {
								accountLov[i] = (accountDto.getRib())
										.toString();
								i++;
							}

						}
						accountToDebit.setValueMap(accountLov);

					}

				});
		accountToDebit.setWrapTitle(false);
		accountToDebit.setRequired(true);

		final TextItem owner = new TextItem("owner", "Owner");
		owner.setWrapTitle(false);
		owner.setRequired(false);
		owner.setAttribute("readOnly", "true");

		accountToDebit.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String rib = accountToDebit.getValueAsString();
				if (rib != null) {

					ebankingService.getBenificiaryByRib(rib,
							new AsyncCallback<BenificiaryDTO>() {

								@Override
								public void onFailure(Throwable arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(BenificiaryDTO arg0) {
									// TODO Auto-generated method stub
									owner.setValue(arg0.getFullName());
									Benificiary benif = new Benificiary();
									benif.setId(arg0.getId());
									transferRequestDTO.setBenificiary(benif);

								}

							});

				}

			}
		});

		// add form fields to form
		form.setFields(accountToDebit, owner);

		return form;
	}

	private DynamicForm buildForm3() {
		DynamicForm form = new DynamicForm();
		form.setWidth(768);
		form.setStyleName("formLayout");
		form.setErrorOrientation(FormErrorOrientation.RIGHT);

		balance.setWrapTitle(false);
		balance.setRequired(false);
		balance.setDefaultValue(0000);

		dateTransfer.setWrapTitle(false);
		dateTransfer.setRequired(false);

		balance.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				transferRequestDTO.setSolde((Double) balance.getValue());
			}
		});

		dateTransfer.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				transferRequestDTO.setTransferDate((Date) dateTransfer
						.getValueAsDate());
			}
		});

		// add form fields to form
		form.setFields(balance, dateTransfer);

		transferRequestDTO
				.setTransferDate((Date) dateTransfer.getValueAsDate());

		return form;
	}

	private DynamicForm buildForm() {
		DynamicForm form = new DynamicForm();
		form.setWidth(768);
		form.setStyleName("formLayout");
		form.setErrorOrientation(FormErrorOrientation.RIGHT);

		// create form fields
		final ComboBoxItem accountToDebit = new ComboBoxItem("account", "RIB");
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
								accountLov[i] = (accountDto.getRib())
										.toString();
								i++;
							}

						}
						accountToDebit.setValueMap(accountLov);

					}

				});
		accountToDebit.setWrapTitle(false);
		accountToDebit.setRequired(true);

		final TextItem balance = new TextItem("balance", "Balance");
		balance.setWrapTitle(false);
		balance.setRequired(false);
		balance.setAttribute("readOnly", "true");

		accountToDebit.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String rib = accountToDebit.getValueAsString();
				if (rib != null) {

					ebankingService.getAccountByRib(rib,
							new AsyncCallback<AccountDTO>() {

								@Override
								public void onFailure(Throwable arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(AccountDTO arg0) {
									// TODO Auto-generated method stub
									balance.setValue(arg0.getSolde());
									transferRequestDTO.setAccount(new Account(
											arg0));

								}

							});

				}

			}
		});

		// add form fields to form
		form.setFields(accountToDebit, balance);

		return form;
	}

	class RegisterButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Timestamp stamp = new Timestamp(dateTransfer.getValueAsDate()
					.getTime());
			Date date = new Date(stamp.getTime());

			transferRequestDTO.setStatus("En cours");
			transferRequestDTO.setSolde(Double.parseDouble(balance.getValue()
					.toString()));
			transferRequestDTO.setTransferDate(date);
			ebankingService.createTransferRequest(transferRequestDTO,
					new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub
							SC.say("Internal exception");

						}

						@Override
						public void onSuccess(Void arg0) {
							// TODO Auto-generated method stub
							SC.say("Transfer request created succefully");

						}
					});

		}

	}
}

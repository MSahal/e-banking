package com.bfi.pfe.EBanking.client.ui;

import java.util.Date;
import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.CreditCardRequest;
import com.bfi.pfe.EBanking.shared.model.dto.AccountDTO;
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
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class CreateCreditCardRequestForm extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransactionListGrid transactionGrid = null;
	private DynamicForm form;
	static CreditCardRequest creditCardRequest = null;
	static ComboBoxItem type = new ComboBoxItem("type", "Type");
	static ComboBoxItem accountToDebit = new ComboBoxItem("account",
			"Account / RIB");

	public CreateCreditCardRequestForm(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transactionGrid = new TransactionListGrid();
		transactionGrid.createForm();
		this.user = user;
		form = buildForm();
		creditCardRequest = new CreditCardRequest();
	}

	public void createForm() {

		form.setGroupTitle("Credit card data");
		form.setIsGroup(true);
		form.setWidth(740);
		form.setHeight(60);
		form.setPadding(5);
		form.setAlign(Alignment.CENTER);

		// create button and add handler
		Button registerButton = new Button("Save");
		registerButton.setAlign(Alignment.CENTER);
		registerButton.addClickHandler(new RegisterButtonClickHandler());

		VLayout forms = new VLayout();
		forms.setAutoWidth();
		forms.setMembersMargin(20);
		forms.setMargin(20);

		forms.setMembers(form, registerButton);

		SectionStackSection section = new SectionStackSection(
				"New credit card request");
		section.setItems(forms);
		SectionStack sectionStack = new SectionStack();
		sectionStack.setWidth(768);

		sectionStack.setSections(section);
		sectionStack.setLayoutAlign(Alignment.CENTER);
		setMembers(sectionStack);

	}

	private DynamicForm buildForm() {
		DynamicForm form = new DynamicForm();
		form.setWidth(768);
		form.setStyleName("formLayout");
		form.setNumCols(4);
		form.setErrorOrientation(FormErrorOrientation.RIGHT);

		// create form fields

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
						accountToDebit.setValueMap(accountLov);

					}

				});
		accountToDebit.setWrapTitle(false);
		accountToDebit.setRequired(true);

		type.setWrapTitle(false);
		type.setRequired(true);
		type.setValueMap("Carte Visa", "Caret Visa Gold", "Carte Mastercard",
				"Carte Salaire", "Carte Tayara");

		// add form fields to form
		form.setFields(accountToDebit, type);

		return form;
	}

	class RegisterButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			creditCardRequest.setStatus("ETUD");
			creditCardRequest.setDateDemande(new Date());
			creditCardRequest.setType(type.getValueAsString());
			Account account = new Account();
			String accountId = accountToDebit.getValueAsString();
			if (accountId != null) {
				accountId = accountId.substring(0, accountId.indexOf('/') - 2);
			}

			account.setId(Long.valueOf(accountId).longValue());
			creditCardRequest.setAccount(account);

			ebankingService.createCreditCardRequest(creditCardRequest,
					new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void arg0) {
							// TODO Auto-generated method stub
							SC.say("Credit card request created successfuly");

						}

						@Override
						public void onFailure(Throwable arg0) {
							SC.say("Internal error");

						}
					});

		}
	}
}

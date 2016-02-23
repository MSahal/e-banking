package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.CheckRequest;
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
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class CreateCheckRequestForm extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransactionListGrid transactionGrid = null;
	private DynamicForm form;
	static CheckRequest checkRequest = null;
	static ComboBoxItem accountToDebit = new ComboBoxItem("account", "RIB");
	static TextItem benificiary = new TextItem("benificiary", "Benificiary");
	static DateItem date = new DateItem("date", "Date");
	static IntegerItem solde = new IntegerItem("solde", "Amount (DT)");
	static ComboBoxItem type = new ComboBoxItem("type", "Type");

	public CreateCheckRequestForm(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transactionGrid = new TransactionListGrid();
		transactionGrid.createForm();
		this.user = user;
		form = buildForm();
		checkRequest = new CheckRequest();
	}

	public void createForm() {

		form.setGroupTitle("Check data");
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
				"New check request");
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
		form.setErrorOrientation(FormErrorOrientation.RIGHT);
		form.setNumCols(4);

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

		type.setValueMap("crossed check", "certified check", "bank check");

		// add form fields to form
		form.setFields(accountToDebit, benificiary, date, solde, type);

		return form;
	}

	class RegisterButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Account account = new Account();
			String accountId = accountToDebit.getValueAsString();
			if (accountId != null) {
				accountId = accountId.substring(0, accountId.indexOf('/') - 2);
			}

			account.setId(Long.valueOf(accountId).longValue());
			checkRequest.setAccount(account);

			checkRequest.setBenificiary(benificiary.getValueAsString());
			SC.say("" + date.getValueAsDate());
			checkRequest.setDateRemise(date.getValueAsDate());
			checkRequest.setSolde(Double.valueOf(solde.getValueAsInteger()));
			checkRequest.setStatus("ETUD");
			checkRequest.setType(type.getValueAsString());

			ebankingService.createCheckRequest(checkRequest,
					new AsyncCallback<Void>() {

						@Override
						public void onSuccess(Void arg0) {
							// TODO Auto-generated method stub
							SC.say("Check request created successfuly");

						}

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub
							SC.say("Internal error");

						}
					});

		}

	}
}

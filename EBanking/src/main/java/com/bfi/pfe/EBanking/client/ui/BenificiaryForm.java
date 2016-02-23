package com.bfi.pfe.EBanking.client.ui;

import java.util.List;

import com.bfi.pfe.EBanking.client.service.EbankingService;
import com.bfi.pfe.EBanking.client.service.EbankingServiceAsync;
import com.bfi.pfe.EBanking.shared.model.Account;
import com.bfi.pfe.EBanking.shared.model.Benificiary;
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
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class BenificiaryForm extends VLayout {
	private final EbankingServiceAsync ebankingService = GWT
			.create(EbankingService.class);
	private UserDTO user;
	private TransactionListGrid transactionGrid = null;
	private DynamicForm form;
	private DynamicForm form2;
	static Benificiary benificiary = null;
	static TextItem name = new TextItem("name", "Name");
	static TextItem rib = new TextItem("account", "RIB");
	static ComboBoxItem accountToDebit = new ComboBoxItem("account", "RIB");

	public BenificiaryForm(UserDTO user) {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		transactionGrid = new TransactionListGrid();
		transactionGrid.createForm();
		this.user = user;
		form = buildForm();
		form2 = buildForm2();
		benificiary = new Benificiary();

	}

	private DynamicForm buildForm() {
		DynamicForm form = new DynamicForm();
		form.setWidth(768);
		form.setStyleName("formLayout");
		form.setErrorOrientation(FormErrorOrientation.RIGHT);

		// create form fields

		rib.setWrapTitle(false);
		rib.setRequired(true);

		name.setWrapTitle(false);
		name.setRequired(false);

		// add form fields to form
		form.setFields(name, rib);

		return form;
	}

	public void createForm() {

		form.setGroupTitle("New benificiary");
		form.setIsGroup(true);
		form.setWidth(740);
		form.setHeight(60);
		form.setPadding(5);
		form.setAlign(Alignment.CENTER);

		form2.setGroupTitle("Debit account");
		form2.setIsGroup(true);
		form2.setWidth(740);
		form2.setHeight(60);
		form2.setPadding(5);
		form2.setAlign(Alignment.CENTER);

		// create button and add handler
		Button registerButton = new Button("Save");
		registerButton.setAlign(Alignment.CENTER);
		registerButton.addClickHandler(new RegisterButtonClickHandler());

		VLayout forms = new VLayout();
		forms.setAutoWidth();
		forms.setMembersMargin(20);
		forms.setMargin(20);

		forms.setMembers(form, form2, registerButton);

		SectionStackSection section = new SectionStackSection("New benificiary");
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

		// add form fields to form
		form.setFields(accountToDebit);

		return form;
	}

	class RegisterButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String accountId = accountToDebit.getValueAsString();
			if (accountId != null) {
				accountId = accountId.substring(0, accountId.indexOf('/') - 2);
			}

			benificiary.setFullName(name.getValueAsString());
			benificiary.setRib(rib.getValueAsString());
			benificiary.setStatus("ETUD");

			Account account = new Account();
			account.setId(Long.valueOf(accountId).longValue());
			benificiary.setAccount(account);
			ebankingService.createTransferRequest(benificiary,
					new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable arg0) {
							// TODO Auto-generated method stub
							SC.say("Internal error");

						}

						@Override
						public void onSuccess(Void arg0) {
							// TODO Auto-generated method stub
							SC.say("Benificiary created successfuly");

						}
					});

		}

	}
}

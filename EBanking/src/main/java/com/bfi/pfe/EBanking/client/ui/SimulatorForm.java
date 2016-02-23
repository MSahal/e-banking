package com.bfi.pfe.EBanking.client.ui;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class SimulatorForm extends VLayout {
	private DynamicForm form;
	final ComboBoxItem contractType = new ComboBoxItem("contractType",
			"Contract type");

	final ComboBoxItem duration = new ComboBoxItem("duration",
			"Duration (Year)");

	final IntegerItem apport = new IntegerItem("apport", "Amount (DT)");

	final TextItem teg = new TextItem("teg", "Interest rate");

	public SimulatorForm() {
		super();
		setEdgeSize(1);
		setShowEdges(true);
		form = buildForm();

	}

	public void createForm() {

		form.setGroupTitle("Contract Data");
		form.setIsGroup(true);
		form.setWidth(740);
		form.setHeight(60);
		form.setPadding(5);
		form.setAlign(Alignment.CENTER);

		// create button and add handler
		Button registerButton = new Button("Calculate");
		registerButton.setAlign(Alignment.CENTER);
		registerButton.addClickHandler(new RegisterButtonClickHandler());

		VLayout forms = new VLayout();
		forms.setAutoWidth();
		forms.setMembersMargin(20);
		forms.setMargin(20);

		forms.setMembers(form, registerButton);

		SectionStackSection section = new SectionStackSection("New simulation");
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
		// form.setColWidths("20", "250", "*");
		form.setNumCols(4);

		// create form fields

		contractType.setWrapTitle(false);
		contractType.setRequired(true);
		contractType.setValueMap("Personal loan", "Equipement lease",
				"Full service lease", "Revolving");

		duration.setWrapTitle(false);
		duration.setRequired(true);
		duration.setValueMap("1", "2", "3", "4", "5", "6", "7");

		teg.setValue("5%");
		teg.setAttribute("readOnly", "true");

		// add form fields to form
		form.setFields(contractType, duration, apport, teg);

		return form;
	}

	class RegisterButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			String message = "--------------------------------------------------------------------------------------------------------------\n\n\n";
			message = message
					+ "******************     Echeance (DT/Month):	"
					+ (Double.valueOf(apport.getValueAsInteger()) / (Double
							.valueOf(duration.getValueAsString()) * 12))
					+ Double.valueOf(apport.getValueAsInteger()) * 0.5
					+ "\n\n\n";
			message = message
					+ "--------------------------------------------------------------------------------------------------------------\n\n\n";
			message = message + "******************   Duration (Month) :	"
					+ Double.valueOf(duration.getValueAsString()) * 12
					+ "\n\n\n";
			message = message
					+ "--------------------------------------------------------------------------------------------------------------\n\n\n";

			final Window winModal = new Window();
			winModal.setWidth(500);
			winModal.setHeight(200);
			winModal.setTitle("Simulation Result");
			winModal.setShowMinimizeButton(false);
			winModal.setIsModal(true);
			winModal.setShowModalMask(true);
			winModal.centerInPage();

			DynamicForm form = new DynamicForm();
			form.setHeight100();
			form.setWidth100();
			form.setPadding(5);
			form.setLayoutAlign(VerticalAlignment.BOTTOM);
			TextAreaItem textItem = new TextAreaItem();
			textItem.setTitle("");
			textItem.setAttribute("readOnly", true);
			textItem.setValue(message);
			textItem.setWidth(500);
			textItem.setHeight(400);

			form.setFields(textItem);
			winModal.addItem(form);
			winModal.show();

		}

	}
}

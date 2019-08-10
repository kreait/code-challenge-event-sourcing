package contactsapp.boundary;

import org.requirementsascode.Model;

import contactsapp.boundary.internal.command_handler.HandleAddCompanyToContactList;
import contactsapp.boundary.internal.command_handler.HandleAddPersonToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;

/**
 * The use case model ties each type of command to its appropriate command
 * handler.
 * 
 * @author b_muth
 *
 */
class UseCaseModel {
	private static final Class<AddCompanyToContactList> addCompanyToContactList = AddCompanyToContactList.class;
	private static final Class<AddPersonToContactList> addPersonToContactList = AddPersonToContactList.class;

	public static Model build(HandleAddPersonToContactList handleAddPersonToContactList,
			HandleAddCompanyToContactList handleAddCompanyToContactList) {
		Model model = Model.builder()
			.user(addPersonToContactList).systemPublish(handleAddPersonToContactList)
			.user(addCompanyToContactList).systemPublish(handleAddCompanyToContactList)
		.build();
		return model;
	}
}

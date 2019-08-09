package contactsapp.boundary;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.driver_port.IReactToCommands;
import contactsapp.boundary.internal.event.ContactListCreated;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;
import contactsapp.command.CreateContactList;

/**
 * The boundary class is the only point of communication with left-side driver
 * adapters. It accepts commands, and calls the appropriate command handler.
 * 
 * On creation, this class wires up the dependencies between command types and
 * command handlers, by injecting the command handlers into a use case model.
 * 
 * After creation, this class sends each command it receives to the runner of
 * the use case model. The model runner then dispatches the command to the
 * appropriate command handler, which in turn calls the driven adapters.
 * 
 * @author b_muth
 *
 */
public class Boundary implements IReactToCommands {

	private ModelRunner modelRunner;

	public Boundary() {
		Model model = buildModel();
		modelRunner = new ModelRunner().run(model);
	}

	private Model buildModel() {
		// Inject command handler(s) into use case model, to tie them to command
		// types.
		Model model = UseCaseModel.build();
		return model;
	}

	@Override
	public Object reactTo(Object commandObject) {
		if (commandObject instanceof CreateContactList) {
			return createContactList(commandObject);
		}
		if (commandObject instanceof AddPersonToContactList) {
			return addPersonToContactList(commandObject);
		}
		if (commandObject instanceof AddCompanyToContactList) {
			return addCompanyToContactList(commandObject);
		}
		return null;
		// return modelRunner.reactTo(commandObject);
	}

	private Object createContactList(Object commandObject) {
		CreateContactList createContactList = (CreateContactList) commandObject;
		return new ContactListCreated(createContactList.getId());
	}
	
	private Object addPersonToContactList(Object commandObject) {
		AddPersonToContactList addPersonToContactList = (AddPersonToContactList) commandObject;
		PersonAddedToContactList personAddedToContactList = new PersonAddedToContactList(addPersonToContactList.getPersonName(),
				"CONTACT_LIST_1");
		return personAddedToContactList;
	}
	
	private Object addCompanyToContactList(Object commandObject) {
		AddCompanyToContactList addCompanyToContactList = (AddCompanyToContactList) commandObject;
		CompanyAddedToContactList companyAddedToContactList = new CompanyAddedToContactList(addCompanyToContactList.getCompanyName(),
				"CONTACT_LIST_1");
		return companyAddedToContactList;
	}
}
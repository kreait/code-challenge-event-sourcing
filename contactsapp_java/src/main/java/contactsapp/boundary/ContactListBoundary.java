package contactsapp.boundary;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;

/**
 * The boundary class is the only point of communication with the outside world.
 * It accepts commands, and calls the appropriate command handler.
 * 
 * On creation, this class wires up the dependencies between command types and
 * command handlers, by injecting the command handlers into a use case model.
 * 
 * After creation, this class sends each command it receives to the runner of
 * the use case model. The model runner then dispatches the command to the
 * appropriate command handler, and publishes them to the event store.
 * 
 * @author b_muth
 *
 */
public class ContactListBoundary {
	private ModelRunner modelRunner;
	private ContactList contactList;

	public ContactListBoundary() {
		this.contactList = new ContactList();
		this.modelRunner = new ModelRunner().run(buildModel());
	}

	private Model buildModel() {
		// Inject command handler(s) into use case model, to tie them to command
		// types.
		Model model = UseCaseModel.build();
		return model;
	}

	public Object reactTo(Object commandObject) {
		if (commandObject instanceof AddPersonToContactList) {
			return addPersonToContactList(commandObject);
		}
		if (commandObject instanceof AddCompanyToContactList) {
			return addCompanyToContactList(commandObject);
		}
		return null;
		// return modelRunner.reactTo(commandObject);
	}

	private Object addPersonToContactList(Object commandObject) {
		AddPersonToContactList addPersonToContactList = (AddPersonToContactList) commandObject;
		PersonAddedToContactList personAddedToContactList = new PersonAddedToContactList(
				addPersonToContactList.getPersonName());
		return personAddedToContactList;
	}

	private Object addCompanyToContactList(Object commandObject) {
		AddCompanyToContactList addCompanyToContactList = (AddCompanyToContactList) commandObject;
		CompanyAddedToContactList companyAddedToContactList = new CompanyAddedToContactList(
				addCompanyToContactList.getCompanyName());
		return companyAddedToContactList;
	}
}
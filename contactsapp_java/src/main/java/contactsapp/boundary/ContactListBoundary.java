package contactsapp.boundary;

import java.util.Optional;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.internal.command_handler.HandleAddCompanyToContactList;
import contactsapp.boundary.internal.command_handler.HandleAddPersonToContactList;
import contactsapp.boundary.internal.domain.ContactList;

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
		this.modelRunner = new ModelRunner().publishWith(contactList).run(buildModel());
	}

	/**
	 * Injects command handler(s) into use case model, to tie them to command types.
	 * 
	 * @return the use case model that has been build
	 */
	private Model buildModel() {
		HandleAddPersonToContactList handleAddPersonToContactList = new HandleAddPersonToContactList();
		HandleAddCompanyToContactList handleAddCompanyToContactList = new HandleAddCompanyToContactList();
		Model model = UseCaseModel.build(handleAddPersonToContactList, handleAddCompanyToContactList);
		return model;
	}

	public Optional<Object> reactTo(Object commandObject) {
		return modelRunner.reactTo(commandObject);
	}

	public ContactList getContactList() {
		return contactList;
	}
}
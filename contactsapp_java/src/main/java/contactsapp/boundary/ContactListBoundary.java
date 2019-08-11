package contactsapp.boundary;

import java.util.Optional;
import java.util.function.Consumer;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.internal.command_handler.HandleAddCompanyToContactList;
import contactsapp.boundary.internal.command_handler.HandleAddPersonToContactList;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.command.AddCompanyToContactList;
import contactsapp.command.AddPersonToContactList;

/**
 * The boundary class is the only point of communication with the outside world.
 * It accepts commands, and calls the appropriate command handler.
 * 
 * The command handler transforms the commands into events.
 * The events are handled by the event publisher specified as constructor argument.
 * 
 * @author b_muth
 *
 */
public class ContactListBoundary {
	private ContactList contactList;

	private ModelRunner commandHandlingModelRunner;
	private ModelRunner eventHandlingModelRunner;

	public ContactListBoundary(Consumer<Object> eventPublisher) {
		this.contactList = new ContactList();
		this.commandHandlingModelRunner = new ModelRunner().publishWith(eventPublisher).run(commandHandlingModel());
		this.eventHandlingModelRunner = new ModelRunner().run(eventHandlingModel());
	}

	/**
	 * Builds a model that ties command types to command handlers.
	 * 
	 * @return the model that has been built
	 */
	private Model commandHandlingModel() {
		Model model = Model.builder()
			.user(AddPersonToContactList.class).systemPublish(new HandleAddPersonToContactList())
			.user(AddCompanyToContactList.class).systemPublish(new HandleAddCompanyToContactList())
		.build();
		
		return model;
	}

	/**
	 * Builds a model that defines how the aggregate root (i.e. the ContactList
	 * instance) reacts to an event. The reaction determines the state change.
	 * 
	 * @return the model that has been built
	 */
	private Model eventHandlingModel() {
		Model model = Model.builder()
			.on(PersonAddedToContactList.class).system(contactList::addPerson)
			.on(CompanyAddedToContactList.class).system(contactList::addCompany)
		.build();

		return model;
	}

	/**
	 * Reacts to the specified command object by sending it to its command handler,
	 * if there is one, and returning its published event.
	 * 
	 * @param commandObject the command to send
	 * @return the event published by the command handler, or an empty optional if
	 *         none was published.
	 */
	public Optional<Object> reactToCommand(Object commandObject) {
		return commandHandlingModelRunner.reactTo(commandObject);
	}

	/**
	 * Reacts to the specified event by sending it to the appropriate method of the
	 * aggregate root, i.e. ContactList.
	 * 
	 * @param eventObject the event to send
	 */
	public void reactToEvent(Object eventObject) {
		eventHandlingModelRunner.reactTo(eventObject);
	}

	public ContactList getContactList() {
		return contactList;
	}
}
package contactsapp.boundary;

import java.util.function.Consumer;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.internal.command_handler.HandleAddCompanyToContactList;
import contactsapp.boundary.internal.command_handler.HandleAddPersonToContactList;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAddedToContactList;
import contactsapp.boundary.internal.event.PersonAddedToContactList;
import contactsapp.boundary.internal.event_handler.HandleCompanyAddedToContactList;
import contactsapp.boundary.internal.event_handler.HandlePersonAddedToContactList;
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
			.on(PersonAddedToContactList.class).system(new HandlePersonAddedToContactList(contactList))
			.on(CompanyAddedToContactList.class).system(new HandleCompanyAddedToContactList(contactList))
		.build();

		return model;
	}

	/**
	 * Reacts to the specified command object by sending it to its command handler,
	 * if there is one.
	 * 
	 * @param commandObject the command to send
	 */
	public void reactToCommand(Object commandObject) {
		commandHandlingModelRunner.reactTo(commandObject);
	}

	/**
	 * Reacts to the specified event by sending it to its event handler,
	 * if there is one.
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
package contactsapp.boundary;

import java.util.Optional;
import java.util.function.Consumer;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;
import org.requirementsascode.StepToBeRun;

import contactsapp.boundary.internal.command_handler.HandleAddCompany;
import contactsapp.boundary.internal.command_handler.HandleAddPerson;
import contactsapp.boundary.internal.command_handler.HandleEnterEmployment;
import contactsapp.boundary.internal.command_handler.HandleRenameContact;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.boundary.internal.event.ContactRenamed;
import contactsapp.boundary.internal.event.EmploymentEntered;
import contactsapp.boundary.internal.event.PersonAdded;
import contactsapp.boundary.internal.event_handler.HandleCompanyAdded;
import contactsapp.boundary.internal.event_handler.HandleContactRenamed;
import contactsapp.boundary.internal.event_handler.HandleEmploymentEntered;
import contactsapp.boundary.internal.event_handler.HandlePersonAdded;
import contactsapp.boundary.internal.query_handler.HandleFindContacts;
import contactsapp.boundary.internal.validation.ValidationError;
import contactsapp.command.AddCompany;
import contactsapp.command.AddPerson;
import contactsapp.command.EnterEmployment;
import contactsapp.command.RenameContact;
import contactsapp.query.FindContacts;

/**
 * The boundary class is the only point of communication with the outside world.
 * It accepts commands, and calls the appropriate command handler.
 * 
 * The command handler transforms the commands into events. The events are
 * handled by the event publisher specified as constructor argument.
 * 
 * @author b_muth
 *
 */
public class ContactListBoundary {
	private ContactList contactList;

	private ModelRunner commandHandlingModelRunner;
	private ModelRunner queryHandlingModelRunner;
	private ModelRunner eventHandlingModelRunner;

	private Object handledEvent;

	public ContactListBoundary(Consumer<Object> eventPublisher) {
		contactList = new ContactList();
		
		commandHandlingModelRunner = new ModelRunner()
			.handleWith(this::clearHandledEvent)
			.publishWith(eventPublisher)
			.run(commandHandlingModel());
		
		queryHandlingModelRunner = new ModelRunner().run(queryHandlingModel());
		
		eventHandlingModelRunner = new ModelRunner()
				.handleWith(this::saveHandledEvent)
				.run(eventHandlingModel());
	}

	// The following three methods serve to find out which event was actually
	// handled by the event handling model. 
	private void clearHandledEvent(StepToBeRun stepToBeRun) {
		handledEvent = null;
		stepToBeRun.run();
	}

	private void saveHandledEvent(StepToBeRun stepToBeRun) {
		stepToBeRun.getMessage().ifPresent(event -> handledEvent = event);
		stepToBeRun.run();
	}

	public Object getHandledEvent() {
		return handledEvent;
	}

	/**
	 * Builds a model that ties command types to command handlers.
	 * 
	 * @return the model that has been builtO
	 */
	private Model commandHandlingModel() {
		Model model = Model.builder()
			.user(AddPerson.class).systemPublish(new HandleAddPerson(contactList))
			.user(AddCompany.class).systemPublish(new HandleAddCompany(contactList))
			.user(RenameContact.class).systemPublish(new HandleRenameContact(contactList))
			.user(EnterEmployment.class).systemPublish(new HandleEnterEmployment(contactList))
			.on(ValidationError.class).systemPublish(err -> err) // Return validation errors
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
			.on(PersonAdded.class).system(new HandlePersonAdded(contactList))
			.on(CompanyAdded.class).system(new HandleCompanyAdded(contactList))
			.on(ContactRenamed.class).system(new HandleContactRenamed(contactList))
			.on(EmploymentEntered.class).system(new HandleEmploymentEntered(contactList))
		.build();

		return model;
	}

	/**
	 * Builds a model that ties queries to query handlers.
	 * 
	 * @return the model that has been built
	 */
	private Model queryHandlingModel() {
		Model model = Model.builder()
			.user(FindContacts.class).systemPublish(new HandleFindContacts(contactList))
		.build();

		return model;
	}

	/**
	 * Reacts to the specified command object by sending it to its command handler,
	 * if there is one.
	 * 
	 * @param command the command to send
	 * @return an error object if an error occured, else an empty optional
	 */
	public Optional<Object> reactToCommand(Object command) {
		return commandHandlingModelRunner.reactTo(command);
	}

	/**
	 * Reacts to the specified event by sending it to its event handler, if there is
	 * one.
	 * 
	 * @param event the event to send
	 */
	public void reactToEvent(Object event) {
		eventHandlingModelRunner.reactTo(event);
	}

	/**
	 * Performs the specified query by sending it to its query handler, if there is
	 * one. If the query handler returns an object, that is returned
	 * 
	 * @param query the query to send
	 * @return the query result, or else an empty optional.
	 */
	public Optional<Object> reactToQuery(Object query) {
		return queryHandlingModelRunner.reactTo(query);
	}
}
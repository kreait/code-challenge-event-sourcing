package contactsapp.boundary;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.driver_port.IReactToCommands;
import contactsapp.boundary.internal.event.ContactListCreated;

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
		//return modelRunner.reactTo(commandObject);
		return new ContactListCreated();
	}
}
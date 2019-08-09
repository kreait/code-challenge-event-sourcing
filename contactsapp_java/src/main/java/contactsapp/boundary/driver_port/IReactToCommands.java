package contactsapp.boundary.driver_port;

public interface IReactToCommands{
	/**
	 * Reacts to the specified command, and returns the published event.
	 * 
	 * @param command the command to handle
	 * @return the published event objecz
	 */
	Object reactTo(Object command);
}

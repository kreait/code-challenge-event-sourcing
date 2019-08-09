package contactsapp.boundary;

import org.requirementsascode.Model;

/**
 * The use case model ties each type of command to its appropriate command
 * handler interface.
 * 
 * @author b_muth
 *
 */
class UseCaseModel {

	public static Model build() {
		Model model = Model.builder().build();
		return model;
	}
}

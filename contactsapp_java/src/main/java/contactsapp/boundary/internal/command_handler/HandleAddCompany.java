package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.command.AddCompany;

public class HandleAddCompany implements Function<AddCompany, Object> {
	@Override
	public Object apply(AddCompany command) {
		CompanyAdded event = new CompanyAdded(command.getCompanyName());
		return event;
	}
}

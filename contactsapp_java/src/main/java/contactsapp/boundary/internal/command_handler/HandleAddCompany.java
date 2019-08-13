package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.CompanyAdded;
import contactsapp.command.AddCompany;

public class HandleAddCompany implements Function<AddCompany, Object> {
	private ContactList contactList;

	public HandleAddCompany(ContactList contactList) {
		this.contactList = contactList;
	}
	
	@Override
	public Object apply(AddCompany command) {
		String companyId = contactList.newContactId();
		CompanyAdded event = new CompanyAdded(companyId, command.getCompanyName());
		return event;
	}
}

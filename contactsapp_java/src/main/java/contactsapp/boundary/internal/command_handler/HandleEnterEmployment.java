package contactsapp.boundary.internal.command_handler;

import java.util.function.Function;

import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.event.EmploymentEntered;
import contactsapp.boundary.internal.event.MissingContact;
import contactsapp.command.EnterEmployment;

public class HandleEnterEmployment implements Function<EnterEmployment, Object>{
	private ContactList contactList;

	public HandleEnterEmployment(ContactList contactList) {
		this.contactList = contactList;
	}

	@Override
	public Object apply(EnterEmployment command) {
		String personId = command.getPersonId();
		String companyId = command.getCompanyId();

		if(!contactList.existsContact(personId)) {
			return new MissingContact(personId);
		}
		if(!contactList.existsContact(companyId)) {
			return new MissingContact(companyId);
		}
		
		EmploymentEntered employmentEntered = new EmploymentEntered(personId, companyId);
		return employmentEntered;
	}

}

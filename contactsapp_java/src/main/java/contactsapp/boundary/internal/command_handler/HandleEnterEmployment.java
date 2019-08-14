package contactsapp.boundary.internal.command_handler;

import java.util.Optional;
import java.util.function.Function;

import contactsapp.boundary.internal.domain.Company;
import contactsapp.boundary.internal.domain.Contact;
import contactsapp.boundary.internal.domain.ContactList;
import contactsapp.boundary.internal.domain.Person;
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
		Optional<Contact> person = contactList.getContact(personId);
		Optional<Contact> company = contactList.getContact(companyId);

		if(!person.isPresent()) {
			return new MissingContact(personId);
		}
		if(!(person.get() instanceof Person)) {
			return new ShouldBePerson(personId);
		}
		if(!company.isPresent()) {
			return new MissingContact(companyId);
		}
		if(!(company.get() instanceof Company)) {
			return new ShouldBeCompany(companyId);
		}
		
		EmploymentEntered employmentEntered = new EmploymentEntered(personId, companyId);
		return employmentEntered;
	}

}

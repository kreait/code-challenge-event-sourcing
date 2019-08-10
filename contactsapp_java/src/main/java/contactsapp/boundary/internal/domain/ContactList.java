package contactsapp.boundary.internal.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.requirementsascode.Model;
import org.requirementsascode.ModelRunner;

import contactsapp.boundary.internal.event.CompanyAddedToContactList;

public class ContactList implements Consumer<Object> {
	private List<Contact> contacts;
	private ModelRunner modelRunner;

	public ContactList() {
		this.contacts = new ArrayList<>();
		this.modelRunner = new ModelRunner().run(buildModel());
	}
	
	private Model buildModel() {
		Model model = Model.builder()
			.on(CompanyAddedToContactList.class).system(this::addCompany)
		.build();
		
		return model;
	}
	
	private void addCompany(CompanyAddedToContactList companyAddedToContactList) {
		String companyName = companyAddedToContactList.getCompanyName();
		contacts.add(new Company(companyName));
	}

	@Override
	public void accept(Object event) {
		modelRunner.reactTo(event);
	}

	public List<Contact> getContacts() {
		return contacts;
	}
}

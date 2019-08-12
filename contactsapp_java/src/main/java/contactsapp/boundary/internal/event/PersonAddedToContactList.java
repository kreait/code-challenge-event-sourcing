package contactsapp.boundary.internal.event;

public class PersonAddedToContactList {
	private String personName;

	public PersonAddedToContactList(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}

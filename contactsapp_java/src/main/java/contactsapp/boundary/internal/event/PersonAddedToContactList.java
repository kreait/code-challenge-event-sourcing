package contactsapp.boundary.internal.event;

public class PersonAddedToContactList {

	private String personName;

	public PersonAddedToContactList(String personName, String contactListIdentifier) {
		this.personName = personName;
	}

	public String getName() {
		return personName;
	}

	public String getPersonName() {
		return personName;
	}

}

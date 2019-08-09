package contactsapp.command;

public class AddPersonToContactList {
	private String personName;
	private String contactListIdentifier;

	public AddPersonToContactList(String personName, String contactListIdentifier) {
		this.personName = personName;
		this.contactListIdentifier = contactListIdentifier;
	}

	public String getPersonName() {
		return personName;
	}

	public String getContactListIdentifier() {
		return contactListIdentifier;
	}
}

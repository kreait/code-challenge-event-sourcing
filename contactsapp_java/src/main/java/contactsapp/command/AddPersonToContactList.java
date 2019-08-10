package contactsapp.command;

public class AddPersonToContactList {
	private String personName;

	public AddPersonToContactList(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}

package contactsapp.command;

public class RenameContact {
	private String contactId;
	private String newName;

	public RenameContact(String contactId, String newName) {
		this.contactId = contactId;
		this.newName = newName;
	}

	public String getContactId() {
		return contactId;
	}

	public String getNewName() {
		return newName;
	}
}

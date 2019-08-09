package contactsapp.command;

public class AddCompanyToContactList {
	private String companyName;
	private String contactListIdentifier;

	public AddCompanyToContactList(String companyName, String contactListIdentifier) {
		this.companyName = companyName;
		this.contactListIdentifier = contactListIdentifier;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getContactListIdentifier() {
		return contactListIdentifier;
	}
}

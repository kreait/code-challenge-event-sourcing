package contactsapp.boundary;

public class CompanyAddedToContactList {
	private String companyName;

	public CompanyAddedToContactList(String companyName, String contactListIdentifier) {
		this.companyName = companyName;
	}

	public String getName() {
		return companyName;
	}

}

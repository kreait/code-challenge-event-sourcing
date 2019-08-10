package contactsapp.boundary.internal.event;

public class CompanyAddedToContactList {
	private String companyName;

	public CompanyAddedToContactList(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

}

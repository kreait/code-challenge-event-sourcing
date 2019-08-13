package contactsapp.boundary.internal.command_handler;

public class EnterEmployment {
	private String personId;
	private String companyId;

	public EnterEmployment(String personId, String companyId) {
		this.personId = personId;
		this.companyId = companyId;
	}
	
	public String getPersonId() {
		return personId;
	}

	public String getCompanyId() {
		return companyId;
	}
}

package contactsapp.boundary.internal.command_handler;

public class EmploymentEntered {
	private String personId;
	private String companyId;

	public EmploymentEntered(String personId, String companyId) {
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

package contactsapp.boundary.internal.event;

public class EmploymentEntered {
	private String personId;
	private String companyId;
	private String role;

	public EmploymentEntered(String personId, String companyId, String role) {
		this.personId = personId;
		this.companyId = companyId;
		this.role = role;
	}

	public String getPersonId() {
		return personId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getRole() {
		return role;
	}
}

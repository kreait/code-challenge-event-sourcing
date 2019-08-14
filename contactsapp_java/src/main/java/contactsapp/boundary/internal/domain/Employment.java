package contactsapp.boundary.internal.domain;

public class Employment {
	private Person person;
	private Company company;
	private String role;

	public Employment(Person person, Company company, String role) {
		this.person = person;
		this.company = company;
		this.role = role;
	}
	
	public Person getPerson() {
		return person;
	}

	public Contact getCompany() {
		return company;
	}

	public String getRole() {
		return role;
	}
}

package contactsapp.boundary.internal.domain;

public class Person extends Contact {
	private Employment employment;

	Person(String id, String name) {
		super(id, name);
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	
	@Override
	public String toString() {
		return "Person [getName()=" + getName() + "]";
	}
}

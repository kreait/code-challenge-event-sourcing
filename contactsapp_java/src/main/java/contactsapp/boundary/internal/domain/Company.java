package contactsapp.boundary.internal.domain;

public class Company extends Contact{
	Company(String id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Company [getName()=" + getName() + "]";
	}
}

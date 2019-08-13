package contactsapp.boundary.internal.domain;

class Company extends Contact{
	public Company(String id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Company [getName()=" + getName() + "]";
	}
}

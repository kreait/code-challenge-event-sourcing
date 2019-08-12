package contactsapp.boundary.internal.domain;

class Company extends Contact{
	public Company(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Company [getName()=" + getName() + "]";
	}
}

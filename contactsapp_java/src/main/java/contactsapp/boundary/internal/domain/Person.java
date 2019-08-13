package contactsapp.boundary.internal.domain;

class Person extends Contact {
	public Person(String id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Person [getName()=" + getName() + "]";
	}
}

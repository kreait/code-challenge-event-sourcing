package contactsapp.boundary.internal.domain;

class Person extends Contact {
	public Person(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Person [getName()=" + getName() + "]";
	}
}

package contactsapp.boundary.internal.domain;

public class Person extends Contact {
	Person(String id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "Person [getName()=" + getName() + "]";
	}
}

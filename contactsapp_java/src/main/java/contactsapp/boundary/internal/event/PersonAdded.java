package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class PersonAdded extends TimestampedEvent{
	private String personName;
	
	public PersonAdded(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}

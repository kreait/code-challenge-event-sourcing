package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class PersonAdded extends TimestampedEvent{
	private String personId;
	private String personName;
	
	public PersonAdded(String personId, String personName) {
		this.personId = personId;
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}

	public String getPersonId() {
		return personId;
	}
}

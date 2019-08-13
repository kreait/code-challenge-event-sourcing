package contactsapp.boundary.internal.event;

import java.time.Instant;

import eventstore.TimestampedEvent;

public class PersonAdded extends TimestampedEvent{
	private String personName;
	
	public PersonAdded(String personName) {
		super(Instant.now());
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}

package contactsapp.boundary.internal.event;

import java.time.Instant;

import eventstore.TimestampedEvent;

public class PersonAdded extends TimestampedEvent{
	private String personName;
	
	public PersonAdded(String personName) {
		this(Instant.now(), personName);
	}

	public PersonAdded(Instant timestamp, String personName) {
		super(timestamp);
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}

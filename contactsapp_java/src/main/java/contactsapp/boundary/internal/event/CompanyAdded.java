package contactsapp.boundary.internal.event;

import java.time.Instant;

import eventstore.TimestampedEvent;

public class CompanyAdded extends TimestampedEvent{
	private String companyName;

	public CompanyAdded(String companyName) {
		this(Instant.now(), companyName);
	}
	
	public CompanyAdded(Instant timestamp, String companyName) {
		super(timestamp);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}
}

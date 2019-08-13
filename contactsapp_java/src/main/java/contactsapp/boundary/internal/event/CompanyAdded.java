package contactsapp.boundary.internal.event;

import java.time.Instant;

import eventstore.TimestampedEvent;

public class CompanyAdded extends TimestampedEvent{
	private String companyName;

	public CompanyAdded(String companyName) {
		super(Instant.now());
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}
}

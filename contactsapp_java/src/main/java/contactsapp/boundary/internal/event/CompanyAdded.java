package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class CompanyAdded extends TimestampedEvent{
	private String companyName;

	public CompanyAdded(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}
}

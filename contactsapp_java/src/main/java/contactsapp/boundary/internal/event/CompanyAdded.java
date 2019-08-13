package contactsapp.boundary.internal.event;

import eventstore.TimestampedEvent;

public class CompanyAdded extends TimestampedEvent{
	private String companyId;
	private String companyName;

	public CompanyAdded(String companyId, String companyName) {
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyId() {
		return companyId;
	}
}

package contactsapp.boundary.internal.event;

import java.time.Instant;

public class CompanyAdded extends BoundaryInternalEvent{
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyAdded other = (CompanyAdded) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		return true;
	}
}

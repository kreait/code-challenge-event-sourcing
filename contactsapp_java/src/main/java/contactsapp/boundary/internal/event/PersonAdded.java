package contactsapp.boundary.internal.event;

public class PersonAdded {
	private String personName;

	public PersonAdded(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
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
		PersonAdded other = (PersonAdded) obj;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		return true;
	}
}
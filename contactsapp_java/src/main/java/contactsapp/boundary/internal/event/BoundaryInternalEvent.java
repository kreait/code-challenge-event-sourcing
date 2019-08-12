package contactsapp.boundary.internal.event;

import java.time.Instant;

public class BoundaryInternalEvent {
	private Instant timestamp;

	public BoundaryInternalEvent(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}

package eventstore;

import java.time.Instant;

public class TimestampedEvent {
	private Instant timestamp;

	public TimestampedEvent(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}

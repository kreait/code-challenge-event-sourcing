package contactsapp.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TestEventStore implements Consumer<Object> {
	private List<Object> storedEvents;
	private List<Consumer<Object>> subscribers;
	
	public TestEventStore() {
		this.storedEvents = new ArrayList<>();
		this.subscribers = new ArrayList<>();
	}

	public void addSubscriber(Consumer<Object> subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void accept(Object event) {
		for (Consumer<Object> subscriber : subscribers) {
			storeEventAndNotifySubscriber(event, subscriber);
		}
	}

	private void storeEventAndNotifySubscriber(Object event, Consumer<Object> subscriber) {
		storedEvents.add(event);
		subscriber.accept(event);
	}
}

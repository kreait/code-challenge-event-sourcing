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
	
	public void replay() {
		for (Object storedEvent : storedEvents) {
			notifySubscribers(storedEvent);
		}
	}

	@Override
	public void accept(Object event) {
		storeEvent(event);
		notifySubscribers(event);
	}

	private void storeEvent(Object event) {
		storedEvents.add(event);
	}
	
	private void notifySubscribers(Object event) {
		for (Consumer<Object> subscriber : subscribers) {
			notifySubscriber(event, subscriber);
		}
	}

	private void notifySubscriber(Object event, Consumer<Object> subscriber) {
		subscriber.accept(event);
	}
}

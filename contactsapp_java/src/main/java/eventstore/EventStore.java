package eventstore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import contactsapp.boundary.internal.event.BoundaryInternalEvent;

public class EventStore implements Consumer<Object> {
	private List<Object> storedEvents;
	private List<Consumer<Object>> subscribers;
	
	public EventStore() {
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
	
	public void replayUntil(Instant instant) {
		for (Object storedEvent : storedEvents) {
			if(eventHappenedUntil(storedEvent, instant)) {
				notifySubscribers(storedEvent);
			}
		}
	}

	private boolean eventHappenedUntil(Object storedEvent, Instant instant) {
		if(!(storedEvent instanceof BoundaryInternalEvent)) {
			return true;
		}
		
		BoundaryInternalEvent boundaryInternalEvent = (BoundaryInternalEvent) storedEvent;
		return !boundaryInternalEvent.getTimestamp().isAfter(instant);
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

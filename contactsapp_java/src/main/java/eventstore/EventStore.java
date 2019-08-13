package eventstore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventStore implements Consumer<Object> {
	private List<TimestampedEvent> storedEvents;
	private List<Consumer<Object>> subscribers;
	
	public EventStore() {
		this.storedEvents = new ArrayList<>();
		this.subscribers = new ArrayList<>();
	}

	public void addSubscriber(Consumer<Object> subscriber) {
		subscribers.add(subscriber);
	}
	
	public void replay() {
		replayUntilConditionFalse(event -> true);
	}
	
	public void replayUntil(Instant instant) {
		replayUntilConditionFalse(event -> eventHappenedUntil(event, instant));
	}
	
	private void replayUntilConditionFalse(Predicate<TimestampedEvent> condition) {
		storedEvents.sort(Comparator.comparing(TimestampedEvent::getTimestamp));
		
		for (TimestampedEvent storedEvent : storedEvents) {
			if(!condition.test(storedEvent)) {
				break;
			}
			notifySubscribers(storedEvent);
		}
	}

	private boolean eventHappenedUntil(TimestampedEvent storedEvent, Instant instant) {
		return !storedEvent.getTimestamp().isAfter(instant);
	}

	@Override
	public void accept(Object event) {
		if(!(event instanceof TimestampedEvent)) {
			throw new IllegalArgumentException("This event store only accepts TimestampedEvent instances!");
		}
		
		TimestampedEvent timestampedEvent = (TimestampedEvent)event;
		storeEvent(timestampedEvent);
		notifySubscribers(timestampedEvent);
	}

	private void storeEvent(TimestampedEvent event) {
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

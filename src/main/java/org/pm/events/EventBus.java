package org.pm.events;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private List<Event> events = new ArrayList<>();
    private List<Subscriber> subscribers = new ArrayList<>();

    public void publish(Event event) {
        events.add(event);
        // Notify all subscribers
        for (Subscriber subscriber : subscribers) {
            subscriber.handle(event);
        }
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}


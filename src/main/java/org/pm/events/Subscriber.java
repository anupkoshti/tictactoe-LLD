package org.pm.events;

import java.util.function.Consumer;

public class Subscriber {
    private final Consumer<Event> handler;

    public Subscriber(Consumer<Event> handler) {
        this.handler = handler;
    }

    public void handle(Event event) {
        handler.accept(event);
    }
}

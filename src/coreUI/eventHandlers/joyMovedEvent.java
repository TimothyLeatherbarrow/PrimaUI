package coreUI.eventHandlers;

import javafx.event.Event;
import javafx.event.EventType;

public class joyMovedEvent extends Event {
    public static EventType JOY_MOVED = new EventType("JOY_MOVED");
    public static EventType JOY_FINISHED = new EventType("JOY_FINISHED");

    public joyMovedEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}

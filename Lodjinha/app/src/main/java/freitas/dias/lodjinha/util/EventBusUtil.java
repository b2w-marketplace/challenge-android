package freitas.dias.lodjinha.util;


import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.Vector;

public enum  EventBusUtil {

    INSTANCE;

    private final Vector<Object> eventQueueBuffer = new Vector<>();

    private boolean paused;

    public <T> void post(final T event) {
        if (paused) {
            eventQueueBuffer.add(event);
        } else {
            EventBus.getDefault().post(event);
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            Iterator<Object> eventIterator = eventQueueBuffer.iterator();
            while (eventIterator.hasNext()) {
                Object event = eventIterator.next();

                if (event != null) {
                    post(event);
                }

                eventIterator.remove();
            }
        }
    }
}

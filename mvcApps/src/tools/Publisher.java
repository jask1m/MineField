package tools;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    protected void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update();
        }
    }
}

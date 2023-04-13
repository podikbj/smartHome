package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

public class HandlerBreaking extends Handler {

    private static HandlerBreaking instance = null;

    private HandlerBreaking() {
    }

    /**
     * Return single instance of HandlerBreaking
     *
     * @return HandlerBreaking
     */
    public static HandlerBreaking getInstance() {

        if (instance == null) {
            instance = new HandlerBreaking();
        }
        return instance;
    }

    @Override
    protected void createMessage(int index, Event event) {
        for (Integer ind : indices) {
            if (ind == index) {
                StringBuilder sb = new StringBuilder();
                sb.append("   System sends a break alarm message");
                for (IObserver observer : observers) {
                    Event event1 = new Event();
                    event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
                    event1.setDevice(event.getDevice());
                    event1.setDeviceType(event.getDeviceType());
                    event1.setEventType(EventType.BREAKING_ALARM);
                    event1.setMessageTypeEvent(sb.toString());
                    observer.update(event1);
                }
            }
        }
    }

}
package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

public class HandlerBurglarAlarm extends Handler {

    private static HandlerBurglarAlarm instance = null;

    private void HandlerBurglarAlarm() {
    }

    /**
     * Return single instance of HandlerBurglarAlarm
     *
     * @return
     */
    public static HandlerBurglarAlarm getInstance() {

        if (instance == null) {
            instance = new HandlerBurglarAlarm();
        }
        return instance;
    }

    @Override
    protected void createMessage(int index, Event event) {
        for (Integer ind : indices) {
            if (ind == index) {
                StringBuilder sb = new StringBuilder();
                sb.append("   System sends a breaking and entering alarm message");
                for (IObserver observer : observers) {
                    Event event1 = new Event();
                    event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
                    event1.setDevice(event.getDevice());
                    event1.setDeviceType(event.getDeviceType());
                    event1.setEventType(EventType.BREAKING_ENTERING_ALARM);
                    event1.setMessageTypeEvent(sb.toString());
                    observer.update(event1);
                }
            }
        }
    }

}
package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

public class HandlerSmokeAlarm extends Handler {

    private static HandlerSmokeAlarm instance = null;

    private HandlerSmokeAlarm() {
    }

    /**
     * Return single instance of HandlerSmokeAlarm
     *
     * @return
     */
    public static HandlerSmokeAlarm getInstance() {

        if (instance == null) {
            instance = new HandlerSmokeAlarm();
        }
        return instance;
    }

    @Override
    protected void createMessage(int index, Event event) {
        for (Integer ind : indices) {
            if (ind == index) {
                StringBuilder sb = new StringBuilder();
                sb.append("   System sends a smoke alarm message");
                for (IObserver observer : observers) {
                    Event event1 = new Event();
                    event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
                    event1.setDevice(event.getDevice());
                    event1.setDeviceType(event.getDeviceType());
                    event1.setEventType(EventType.SMOKE_ALARM);
                    event1.setMessageTypeEvent(sb.toString());
                    observer.update(event1);
                }
            }
        }
    }
}

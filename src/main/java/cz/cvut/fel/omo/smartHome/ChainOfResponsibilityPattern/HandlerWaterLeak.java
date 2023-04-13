package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

public class HandlerWaterLeak extends Handler {

    private static HandlerWaterLeak instance = null;

    private HandlerWaterLeak() {
    }

    /**
     * Return single instance of HandlerWaterLeak
     *
     * @return
     */
    public static HandlerWaterLeak getInstance() {

        if (instance == null) {
            instance = new HandlerWaterLeak();
        }
        return instance;
    }

    @Override
    protected void createMessage(int index, Event event) {
        for (Integer ind : indices) {
            if (ind == index) {
                StringBuilder sb = new StringBuilder();
                sb.append("   System sends a water leak alarm message");
                for (IObserver observer : observers) {
                    Event event1 = new Event();
                    event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
                    event1.setDevice(event.getDevice());
                    event1.setDeviceType(event.getDeviceType());
                    event1.setEventType(EventType.WATER_LEAK_ALARM);
                    event1.setMessageTypeEvent(sb.toString());
                    observer.update(event1);
                }
            }
        }
    }

}
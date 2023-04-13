package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

public class HandlerGasLeak extends Handler {

    private static HandlerGasLeak instance = null;

    private HandlerGasLeak() {
    }

    /**
     * Return single instance of HandlerBurglarAlarm
     *
     * @return
     */
    public static HandlerGasLeak getInstance() {

        if (instance == null) {
            instance = new HandlerGasLeak();
        }
        return instance;
    }

    @Override
    protected void createMessage(int index, Event event) {
        for (Integer ind : indices) {
            if (ind == index) {
                StringBuilder sb = new StringBuilder();
                sb.append("   System sends a gas leak alarm message");
                Event event1 = new Event();
                for (IObserver observer : observers) {
                    event1 = new Event();
                    event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
                    event1.setDevice(event.getDevice());
                    event1.setDeviceType(event.getDeviceType());
                    event1.setEventType(EventType.GAS_LEAK_ALARM);
                    event1.setMessageTypeEvent(sb.toString());
                    observer.update(event1);
                }
            }
        }
    }
}
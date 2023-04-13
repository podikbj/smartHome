package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;

import java.util.Random;

public class Sensor extends ADevice {

    private final int activePeriod = 40;
    private final int idlePeriod = 3;
    private final double electricityConsumptionActive = 30;
    private final double electricityConsumptionIdle = 5;
    int stateLength = 0;
    private boolean isBreak = false;

    /**
     * Constructor for object Sensor/ It requires device name
     *
     * @param modelName
     */
    public Sensor(String modelName) {
        super(modelName);
    }

    @Override
    protected void makeStep() {
        stateLength++;
        Random random = new Random();
        int s = 0;
        int f = 60;
        int x = s + random.nextInt(f - s + 1);

        if (stateLength % 20 == 0) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.SENSOR);
            event.setEventType(EventType.DEFAULT);
            event.setDeviceTypeEvent("   Instance: " + name + "is opening window.");
            events.add(event);
        }

        IState state = this.getState();
        if (stateLength >= state.getPeriod()) {
            state.changeState();
            stateLength = 0;
        }
    }

    @Override
    protected void onActive() {
        currentElectricityActiveConsumption += electricityConsumptionActive;
    }

    @Override
    protected void onIdle() {
        currentElectricityIdleConsumption += electricityConsumptionIdle;
    }

    @Override
    protected int getActivePeriod() {
        return activePeriod;
    }

    /**
     * Period getter. Gets idle period life
     *
     * @return
     */
    @Override
    public int getIdlePeriod() {
        return idlePeriod;
    }

    /**
     * Sets that current device is breaked
     */
    @Override
    public void simulateEvent() {
        this.isBreak = true;
    }

    /**
     * If current device status is breaked generates new event
     */
    @Override
    public void checkDeviceStatus() {
        if (this.isBreak == true) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.SENSOR);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }
}
package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.IObserver;

import java.util.ArrayList;

public class BurglarAlarm extends ADevice {

    final private int activePeriod = 40;
    final private int idlePeriod = 3;
    final private double electricityConsumptionActive = 20;
    final private double electricityConsumptionIdle = 5;
    private boolean isAlarmed = false;

    private ArrayList<IObserver> observers = new ArrayList<>();

    /**
     * Constructor for object BurglarAlarm/ It requires device name
     *
     * @param modelName
     */
    public BurglarAlarm(String modelName) {
        super(modelName);
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
     * Sets that current device is alarmed
     */
    @Override
    public void simulateEvent() {
        this.isAlarmed = true;
    }

    /**
     * If current device status is alarmed generates new event
     */
    @Override
    public void checkDeviceStatus() {
        if (this.isAlarmed == true) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.BURGLAR_ALARM);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports an entering and breaking alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isAlarmed = false;
        }
    }


}

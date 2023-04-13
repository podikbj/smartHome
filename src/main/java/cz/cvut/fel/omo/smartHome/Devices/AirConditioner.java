package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;

public class AirConditioner extends ADevice {

    private final int activePeriod = 40;
    private final int idlePeriod = 3;
    private final double electricityConsumptionActive = 200;
    private final double electricityConsumptionIdle = 15;
    private boolean isBreak = false;

    /**
     * Constructor for object AirConditioner/ It requires device name
     *
     * @param modelName
     */
    public AirConditioner(String modelName) {
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
            event.setDeviceType(DeviceType.AIR_CONDITIONER);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }

}
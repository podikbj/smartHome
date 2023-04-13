package cz.cvut.fel.omo.smartHome.Devices;


import cz.cvut.fel.omo.smartHome.Events.Event;

public class ElectronicLock extends ADevice {

    private final int activePeriod = 40;
    private final int idlePeriod = 3;
    private final double electricityConsumptionActive = 15;
    private final double electricityConsumptionIdle = 3;
    private boolean isBreak = false;

    /**
     * Constructor for object ElectronicLock/ It requires device name
     *
     * @param modelName
     */
    public ElectronicLock(String modelName) {
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
            event.setDeviceType(DeviceType.ELECTRONC_LOCK);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }

}
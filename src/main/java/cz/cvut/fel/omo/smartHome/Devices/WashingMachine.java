package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;

public class WashingMachine extends ADevice {

    private final double waterConsumption = 40;
    private final int activePeriod = 60;
    private final int idlePeriod = 1;
    private final double electricityConsumptionActive = 50;
    private final double electricityConsumptionIdle = 10;
    private boolean isBreak = false;

    /**
     * Constructor for object WashingMachine/ It requires device name
     *
     * @param modelName
     */
    public WashingMachine(String modelName) {
        super(modelName);
    }

    @Override
    protected void onActive() {
        currentWaterConsumption += waterConsumption;
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

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Water: ").append(getCurrentWaterConsumption()).append(" l").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
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
            event.setDeviceType(DeviceType.WASHING_MACHINE);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }

}
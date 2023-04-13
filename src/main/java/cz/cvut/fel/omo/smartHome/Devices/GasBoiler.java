package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;

public class GasBoiler extends ADevice {

    private final double gasConsumtion = 50;
    private double currentWaterConsumption = 0;
    private double currentGasConsumption = 0;
    private final int activePeriod = 100;
    private final int idlePeriod = 5;
    private final double waterConsumption = 100;
    private final double electricityConsumptionActive = 70;
    private final double electricityConsumptionIdle = 10;
    private boolean isBreak = false;

    /**
     * Constructor for object GasBoiler/ It requires device name
     *
     * @param modelName
     */
    public GasBoiler(String modelName) {
        super(modelName);
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
    protected void onActive() {
        currentGasConsumption += gasConsumtion;
        currentWaterConsumption += waterConsumption;
        currentElectricityActiveConsumption += electricityConsumptionActive;
    }

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Water: ").append(getCurrentWaterConsumption()).append(" l").append("\r\n")
                .append(" Gas: ").append(getCurrentGasConsumption()).append(" g").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
    }

    protected double getCurrentGasConsumption() {
        return currentGasConsumption;
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
            event.setDeviceType(DeviceType.GAS_BOILER);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }
}
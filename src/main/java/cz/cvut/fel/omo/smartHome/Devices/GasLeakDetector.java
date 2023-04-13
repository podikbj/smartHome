package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;

public class GasLeakDetector extends ADevice {

    private final int GAS_CONSUMPTION = 300;
    private final int gasLeakLimit = 700;
    private int gasConsumtion = GAS_CONSUMPTION;
    private final int activePeriod = 40;
    private final int idlePeriod = 3;
    private final double electricityConsumptionActive = 25;
    private final double electricityConsumptionIdle = 15;

    /**
     * Constructor for object GasLeakDetector/ It requires device name
     *
     * @param modelName
     */
    public GasLeakDetector(String modelName) {
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

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Gas: ").append(getGasConsumption()).append(" l").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
    }

    protected int getGasConsumption() {
        return gasConsumtion;
    }

    /**
     * If current device gas consumption is bigger than gas leak limit generates new event
     */
    @Override
    public void checkDeviceStatus() {
        if (gasConsumtion > gasLeakLimit) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.GAS_LEAK_DETECTOR);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a gas leak alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            gasConsumtion = GAS_CONSUMPTION;
        }
    }

    /**
     * Sets gas consumption on overlimited value
     */
    @Override
    public void simulateEvent() {
        this.gasConsumtion = 1000;
    }

}
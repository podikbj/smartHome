package cz.cvut.fel.omo.smartHome.Devices;


import cz.cvut.fel.omo.smartHome.Events.Event;

/**
 * @author kira
 * @version 1.0
 * @created 22-Nov-2020 10:33:39 AM
 */
public class CoffeeMachine extends ADevice {

    private final int coffeeConsumption = 50;
    private final double waterConsumption = 8;
    private int activePeriod = 15;
    private int idlePeriod = 2;
    private final double electricityConsumptionActive = 15;
    private final double electricityConsumptionIdle = 3;
    private int currentCoffeeConsumption = 0;
    private boolean isBreak = false;

    /**
     * Constructor for object CoffeeMachine/ It requires device name
     *
     * @param modelName
     */
    public CoffeeMachine(String modelName) {
        super(modelName);
    }

    @Override
    protected void onActive() {
        currentCoffeeConsumption += coffeeConsumption;
        currentWaterConsumption += waterConsumption;
        currentElectricityActiveConsumption += electricityConsumptionActive;
    }

    @Override
    protected void onIdle() {
        currentElectricityIdleConsumption += electricityConsumptionIdle;
    }

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Water: ").append(getCurrentWaterConsumption()).append(" l").append("\r\n")
                .append(" Coffee: ").append(getCurrentCoffeeConsumption()).append(" g").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
    }

    protected int getCurrentCoffeeConsumption() {
        return this.currentCoffeeConsumption;
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
            event.setDeviceType(DeviceType.COFFEE_MACHINE);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a break alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            isBreak = false;
        }
    }
}
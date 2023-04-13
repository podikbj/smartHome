package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.IObserver;

import java.util.ArrayList;

public class WaterLeakDetector extends ADevice {

    private final int WATER_CONSUMPTION = 600;
    private final int waterLeakLimit = 1000;
    private int waterConsumption = WATER_CONSUMPTION;
    private final int activePeriod = 60;
    private final int idlePeriod = 1;
    private final double electricityConsumptionActive = 50;
    private final double electricityConsumptionIdle = 10;

    private ArrayList<IObserver> observers = new ArrayList<>();

    /**
     * Constructor for object WaterLeakDetector/ It requires device name
     *
     * @param modelName
     */
    public WaterLeakDetector(String modelName) {
        super(modelName);
    }

    @Override
    protected void onActive() {
        currentWaterConsumption += waterConsumption;
        currentElectricityActiveConsumption += electricityConsumptionActive;
    }

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Water: ").append(getWaterConsumption()).append(" l").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
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

    protected int getWaterLeakLimit() {
        return waterLeakLimit;
    }

    private int getWaterConsumption() {
        return this.waterConsumption;
    }

    /**
     * If current device water consumption is bigger than water leak limit generates new event
     */
    @Override
    public void checkDeviceStatus() {
        if (waterConsumption > waterLeakLimit) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.WATER_LEAK_DETECTOR);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a gas leak alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            waterConsumption = WATER_CONSUMPTION;
        }
    }

    /**
     * Sets water consumption on overlimited value
     */
    @Override
    public void simulateEvent() {
        this.waterConsumption = 1200;
    }

}
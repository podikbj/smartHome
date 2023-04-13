package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.IObserver;

import java.util.ArrayList;

public class SmokeAlarm extends ADevice {

    private final int SMOKE_CONSUMPTION = 7;
    private final int smokeLimit = 20;
    private int smokeConsumption = SMOKE_CONSUMPTION;
    private final int activePeriod = 40;
    private final int idlePeriod = 3;
    private final double electricityConsumptionActive = 30;
    private final double electricityConsumptionIdle = 5;


    private ArrayList<IObserver> observers = new ArrayList<>();

    /**
     * Constructor for object SmokeAlarm/ It requires device name
     */
    public SmokeAlarm(String modelName) {
        super(modelName);
    }

    @Override
    protected void onActive() {
        currentElectricityActiveConsumption += electricityConsumptionActive;
    }

    @Override
    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Smoke: ").append(getSmokeConsumption()).append(" mg").append("\r\n")
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

    protected int getSmokeConsumption() {
        return smokeConsumption;
    }

    /**
     * If current device smoke consuntion is bigger than smoke limit generates new event
     */
    @Override
    public void checkDeviceStatus() {
        if (smokeConsumption > smokeLimit) {
            Event event = new Event();
            event.setDevice(this);
            event.setDeviceType(DeviceType.SMOKE_ALARM);
            StringBuilder sb = new StringBuilder();
            sb.append("   Instance: ").append(name).append(" reports a smoke alarm.");
            event.setDeviceTypeEvent(sb.toString());
            notifyHandler(event);
            smokeConsumption = SMOKE_CONSUMPTION;
        }
    }

    /**
     * Sets smoke consumption on overlimited value
     */
    @Override
    public void simulateEvent() {
        this.smokeConsumption = 30;
    }

}
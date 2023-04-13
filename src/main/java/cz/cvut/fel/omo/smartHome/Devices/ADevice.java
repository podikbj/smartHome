package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.*;
import cz.cvut.fel.omo.smartHome.City.IVisited;
import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.*;

import java.util.ArrayList;

public abstract class ADevice extends AComponent implements IVisited {

    private IState state;

    private int stateLength = 0;

    protected double waterConsumption;
    protected double currentWaterConsumption;

    protected double electricityConsumptionActive;
    protected double electricityConsumptionIdle;
    protected double currentElectricityActiveConsumption;
    protected double currentElectricityIdleConsumption;
    protected String repairManual = "These is repair manual";

    protected ArrayList<Event> events = ReusablePool.getInstance().getEvents();

    protected int activePeriod;
    protected int idlePeriod;

    protected ADevice(String modelName) {
        super(modelName);
        state = new Blocked(this);
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[6]).replace("\0", "-");
    }

    @Override
    protected void makeStep() {
        stateLength++;
        IState temp_state = this.getState();
        if (stateLength >= state.getPeriod()) {
            temp_state.changeState();
            stateLength = 0;
        }
    }

    /**
     * Simulates interacting person with device
     */
    @Override
    public void interact() {
        stateLength++;
        int period = 0;
        int state_numbers = 1;
        ReusablePool reusablePool = ReusablePool.getInstance();

        IState temp_state = this.getState();
        if (temp_state.getStatesType() == StatesType.ACTIVE) {
            temp_state.changeState();
            stateLength = 0;
            StringBuilder sb = new StringBuilder();
            setCurrentIndent();
            sb.append(getCurrentIndent()).append("Instance: ").append(getName()).append(" is blocked ");
            reusablePool.releaseComponent(this);
            return;
        }
        if (temp_state.getStatesType() == StatesType.BLOCKED) {
            temp_state.changeState();
            StringBuilder sb = new StringBuilder();
            setCurrentIndent();
            sb.append(getCurrentIndent()).append("Instance: ").append(getName()).append(" is activated ");
        }

        while (state_numbers <= 2) {
            temp_state = this.getState();
            temp_state.setPeriod(getIdlePeriod());
            period = temp_state.getPeriod();
            while (stateLength <= period) {
                stateLength++;
                if (stateLength == period) {
                    temp_state.changeState();
                }
            }
            stateLength = 0;
            state_numbers++;
        }
        reusablePool.releaseComponent(this);
    }

    protected abstract void onActive();

    protected abstract void onIdle();

    /**
     * Calls metod visit() for current visitor
     *
     * @param visitor
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    protected void setState(IState state) {
        this.state = state;
    }

    /**
     * Returns state for current device
     *
     * @return
     */
    public IState getState() {
        return state;
    }

    protected String consumptionToString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getName()).append(" consumped: ").append("\r\n")
                .append(" Electricity on active state: ").append(getCurrentElectricityActiveConsumption()).append(" w").append("\r\n")
                .append(" Electricity on idle state: ").append(getCurrentElectricityIdleConsumption()).append(" w").append("\r\n");
        return sb.toString();
    }

    protected double getCurrentWaterConsumption() {
        return currentWaterConsumption;
    }

    protected double getCurrentElectricityActiveConsumption() {
        return currentElectricityActiveConsumption;
    }

    protected double getCurrentElectricityIdleConsumption() {
        return currentElectricityIdleConsumption;
    }

    protected abstract int getActivePeriod();

    protected abstract int getIdlePeriod();

    /**
     * Returns repair manual for current device
     *
     * @return
     */
    public String getRepairManual() {
        return repairManual;
    }

    protected void notifyHandler(Event event) {
        int index = SmartHomeFactory.getInstance().getADeviceArray().indexOf(this);

        Handler handler = HandlerGasLeak.getInstance();
        HandlerWaterLeak handlerWaterLeak = HandlerWaterLeak.getInstance();
        HandlerBurglarAlarm handlerBurglarAlarm = HandlerBurglarAlarm.getInstance();
        HandlerSmokeAlarm handlerSmokeAlarm = HandlerSmokeAlarm.getInstance();
        HandlerBreaking handlerBreaking = HandlerBreaking.getInstance();

        handler.setNext(handlerWaterLeak);
        handlerWaterLeak.setNext(handlerBurglarAlarm);
        handlerBurglarAlarm.setNext(handlerSmokeAlarm);
        handlerSmokeAlarm.setNext(handlerBreaking);
        handler.handle(index, event);
    }

    public abstract void simulateEvent();

    public abstract void checkDeviceStatus();

}


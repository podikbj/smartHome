package cz.cvut.fel.omo.smartHome.Devices;

public class Idle implements IState {

    private ADevice device;
    private int period = 0;

    /**
     * Constructor for object Idle means idle device state/ It requires current device
     *
     * @param device
     */
    public Idle(ADevice device) {
        this.device = device;
    }

    /**
     * Set the next state to the context
     */
    @Override
    public void changeState() {
        Active active = new Active(device);
        active.setPeriod(device.activePeriod);
        device.setState(active);
        device.onActive();
    }

    /**
     * Returns string that includes current state
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current state is Idle.");
        return sb.toString();
    }

    /**
     * Returns current state
     *
     * @return
     */
    @Override
    public StatesType getStatesType() {
        return StatesType.IDLE;
    }

    /**
     * Period getter. Get current time to live of the state
     *
     * @return
     */
    @Override
    public int getPeriod() {
        return period;
    }

    /**
     * Period setter
     *
     * @param period
     */
    @Override
    public void setPeriod(int period) {
        this.period = period;
    }
}
package cz.cvut.fel.omo.smartHome.Devices;

public class Blocked implements IState {

    private ADevice device;
    private int period = 0;

    /**
     * Constructor for object Blocked means blocked device state/ It requires current device
     *
     * @param device
     */
    public Blocked(ADevice device) {
        this.device = device;
    }

    /**
     * Set the next state to the context
     */
    @Override
    public void changeState() {
        Idle idle = new Idle(device);
        idle.setPeriod(device.getIdlePeriod());
        device.setState(idle);
        device.onIdle();
    }

    /**
     * Returns string that includes current state
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current state is Blocked.");
        return sb.toString();
    }

    /**
     * Returns current state
     *
     * @return
     */
    @Override
    public StatesType getStatesType() {
        return StatesType.BLOCKED;
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
package cz.cvut.fel.omo.smartHome.Devices;

public class Active implements IState {

    private ADevice device;
    private int period = 0;

    /**
     * Constructor for object Active means active device state/ It requires current device
     *
     * @param device
     */
    public Active(ADevice device) {
        this.device = device;
    }

    /**
     * Set the next state to the device
     */
    @Override
    public void changeState() {
        device.setState(new Blocked(device));
    }

    /**
     * Returns string that includes current state
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current state is Active.");
        return sb.toString();
    }

    /**
     * Returns current state
     *
     * @return
     */
    @Override
    public StatesType getStatesType() {
        return StatesType.ACTIVE;
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
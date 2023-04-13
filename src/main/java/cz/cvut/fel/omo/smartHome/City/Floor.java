package cz.cvut.fel.omo.smartHome.City;


import cz.cvut.fel.omo.smartHome.AComponent;

public class Floor extends AComponent {

    private House house;

    /**
     * Constructor for object Floore. It requires floore's name and current house
     *
     * @param name
     * @param house
     */
    public Floor(String name, House house) {
        super(name);
        this.house = house;
    }

    /**
     * Returns string that includes instance name
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getCurrentIndent()).append("Instance ").append(getName()).append(" includes:");
        return sb.toString();
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[3]).replace("\0", "-");
    }

    @Override
    protected void makeStep() {
    }

    @Override
    protected String consumptionToString() {
        return "";
    }

}
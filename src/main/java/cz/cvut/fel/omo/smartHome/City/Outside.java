package cz.cvut.fel.omo.smartHome.City;

import cz.cvut.fel.omo.smartHome.AComponent;
import cz.cvut.fel.omo.smartHome.City.City;

public class Outside extends AComponent {

    private City city;

    /**
     * Constructor for Outside. It requires Outside's name and city
     *
     * @param name
     * @param city
     */
    public Outside(String name, City city) {
        super(name);
    }

    /**
     * Returns string that includes instance name
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCurrentIndent()).append("Instance ").append(getName()).append(" includes:");
        return sb.toString();
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[2]).replace("\0", "-");
        ;
    }

    @Override
    protected void makeStep() {
    }

    @Override
    protected String consumptionToString() {
        return "";
    }

}

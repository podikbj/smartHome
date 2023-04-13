package cz.cvut.fel.omo.smartHome.City;

import cz.cvut.fel.omo.smartHome.AComponent;

public class House extends AComponent {

    private static House instance = null;
    private City city;

    private House(String name, City city) {
        super(name);
    }

    /**
     * Returns single instance of House
     *
     * @return House
     */
    public static House getInstance() {

        if (instance == null) {
            instance = new House("House", City.getInstance());
        }
        return instance;
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
    }

    @Override
    protected void makeStep() {
    }

    @Override
    protected String consumptionToString() {
        return "";
    }
}
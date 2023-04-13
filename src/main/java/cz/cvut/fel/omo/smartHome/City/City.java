package cz.cvut.fel.omo.smartHome.City;

import cz.cvut.fel.omo.smartHome.AComponent;

public class City extends AComponent {

    private static City instance = null;

    private City(String name) {
        super(name);
    }

    /**
     * Returns single instance of City
     *
     * @return
     */
    public static City getInstance() {

        if (instance == null) {
            instance = new City("Springwill");
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
        currentIndent = new String(new char[1]).replace("\0", "-");
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

package cz.cvut.fel.omo.smartHome.City;

public class WindowSmall extends BasicWindow {

    private String description = " having window 500x500 mm with";

    /**
     * Constructor for WindowSmall
     */
    public WindowSmall() {
    }

    @Override
    protected String getDescription() {
        return description;
    }

}
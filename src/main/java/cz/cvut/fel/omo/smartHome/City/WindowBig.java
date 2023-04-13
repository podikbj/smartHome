package cz.cvut.fel.omo.smartHome.City;

public class WindowBig extends BasicWindow {

    private String description = " having window 700x700 mm with";

    /**
     * Constructor for WindowBig
     */
    public WindowBig() {
    }

    @Override
    protected String getDescription() {
        return description;
    }

}
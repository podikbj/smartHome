package cz.cvut.fel.omo.smartHome.City;

public class BlindsWood extends BlindsDecorator {

    private String description = " wood blinds";

    /**
     * Constructor for BlindsPlastik. It requires current basicWindow
     *
     * @param basicWindow
     */
    public BlindsWood(BasicWindow basicWindow) {
        this.basicWindow = basicWindow;
    }

    @Override
    protected String getDescription() {
        return basicWindow.getDescription() + description;
    }
}

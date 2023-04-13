package cz.cvut.fel.omo.smartHome.City;

public class BlindsPlastik extends BlindsDecorator {

    private String description = " plastic blinds";

    /**
     * Constructor for BlindsPlastik. It requires current basicWindow
     *
     * @param basicWindow
     */
    public BlindsPlastik(BasicWindow basicWindow) {
        this.basicWindow = basicWindow;
    }

    @Override
    protected String getDescription() {
        return basicWindow.getDescription() + description;
    }
}

package cz.cvut.fel.omo.smartHome.City;

public abstract class BlindsDecorator extends BasicWindow {

    protected BasicWindow basicWindow;

    protected abstract String getDescription();

}
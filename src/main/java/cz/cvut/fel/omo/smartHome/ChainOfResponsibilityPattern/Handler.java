package cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern;

import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.SmartHomeFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Handler {

    private Handler next;
    protected ArrayList<IObserver> observers = SmartHomeFactory.getInstance().getObserversArray();
    protected List<Integer> indices = new ArrayList<Integer>();

    /**
     * Sets next handler in chain of responsibility/ It requires handler
     *
     * @param handler
     */
    public void setNext(Handler handler) {
        this.next = handler;
    }

    /**
     * Handles the event in chain of responsibility
     *
     * @param index device index in array
     * @param event event that the ani device genereted
     */
    public void handle(int index, Event event) {
        createMessage(index, event);
        if (next != null) {
            next.handle(index, event);
        }
    }

    protected abstract void createMessage(int index, Event event);

    protected void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    protected void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * Indices getter. Get set of device indices
     *
     * @return indices
     */
    public List<Integer> getIndices() {
        return indices;
    }

}
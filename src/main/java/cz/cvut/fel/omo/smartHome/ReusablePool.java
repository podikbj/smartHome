package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.City.IVisited;
import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;

import java.util.ArrayList;

public class ReusablePool {

    private static ReusablePool instance = null;
    private ArrayList<IVisitor> visitors = new ArrayList<IVisitor>();
    private ArrayList<IVisited> visitables = new ArrayList<IVisited>();
    private ArrayList<AComponent> reusableComponent;
    private ArrayList<StringBuilder> activities = new ArrayList<StringBuilder>();
    private ArrayList<Event> events = new ArrayList<Event>();

    private ReusablePool() {
        reusableComponent = new ArrayList<AComponent>();
    }

    /**
     * Returns single instance of ReusablePool
     *
     * @return
     */
    public static ReusablePool getInstance() {
        if (instance == null) {
            instance = new ReusablePool();
        }
        return instance;
    }

    protected void addComponent(AComponent component) {
        if (!component.isInUse()) {
            reusableComponent.add(component);
            component.setInUse(false);
        }
    }

    protected AComponent acquireComponent(String name) {

        for (AComponent element : reusableComponent) {
            {
                if (element.getName().equals(name) && !element.isInUse()) {
                    element.setInUse(true);
                    return element;
                }
            }
        }
        return null;
    }

    /**
     * Sets fild inUse in false
     *
     * @param subject
     */
    public void releaseComponent(AComponent subject) {

        int index = reusableComponent.indexOf(subject);
        if (index == -1) {
            return;
        }
        AComponent component = reusableComponent.get(index);
        component.setInUse(false);

    }

    protected ArrayList<AComponent> getReusableComponent() {
        return reusableComponent;
    }

    protected ArrayList<IVisitor> getVisitor() {
        return visitors;
    }

    protected ArrayList<IVisited> getVisitable() {
        return visitables;
    }

    /**
     * Getter for activies
     *
     * @return
     */
    public ArrayList<StringBuilder> getActivities() {
        return activities;
    }

    /**
     * Gettterb for events
     *
     * @return
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    protected void releaseAllComponents() {
        for (AComponent element : reusableComponent) {
            releaseComponent(element);
        }
    }
}
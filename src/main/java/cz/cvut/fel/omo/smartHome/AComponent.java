package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.Devices.IState;

import java.util.ArrayList;

public abstract class AComponent {

    protected boolean inUse = false;
    protected String name = " ";
    protected ArrayList<AComponent> children = new ArrayList<AComponent>();
    protected AComponent parent;
    protected String currentIndent = "";
    protected IState state;
    private static ArrayList<String> strPrinting = new ArrayList<>();

    protected AComponent(String name) {
        this.name = name;
    }

    /**
     * Getter for current instance name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * If instance is using in Object Pool returns True
     *
     * @return
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     * Setter for inUse.
     *
     * @param inUse
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    /**
     * Returns string that includes instance name
     *
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getCurrentIndent()).append("Instance: ").append(getName());
        return sb.toString();
    }

    protected void showComponentDetails() {

        for (AComponent comp : children) {
            String str1 = comp.toString();
            strPrinting.add(str1);
            comp.showComponentDetails();
        }
    }

    protected abstract void makeStep();

    protected abstract void setCurrentIndent();

    protected String getCurrentIndent() {
        return this.currentIndent;
    }

    protected AComponent getChild(String name) {
        for (AComponent component : children) {
            if (component.getName().equals(name)) {
                return component;
            }
            component.getChild(name);
        }
        return null;
    }

    /**
     * Add current instance to array children. Is used for adding instance to the tree
     *
     * @param component
     */
    public void addChild(AComponent component) {
        component.setParent(this);
        children.add(component);
    }

    /**
     * Remove instance from array children and from the tree
     *
     * @param component
     */
    public void removeChild(AComponent component) {
        children.remove(component);
    }

    protected String consumptionToString() {
        return "";
    }

    /**
     * Simulates interacting person with device
     */
    public void interact() {
    }

    protected void showConsumptionDetails() {

        for (AComponent comp : children) {
            String str = comp.consumptionToString();
            if (str != "") {
                strPrinting.add(str);
            }
            comp.showConsumptionDetails();
        }
    }

    protected void setParent(AComponent parent) {
        this.parent = parent;
    }

    /**
     * Getter for parent
     *
     * @return
     */
    public AComponent getParent() {
        return this.parent;
    }

    /**
     * Getter for device state
     *
     * @return
     */
    public IState getState() {
        return this.state;
    }

    protected static ArrayList<String> getStrPrinting() {
        return strPrinting;
    }

    /**
     * Gettter for children
     *
     * @return
     */
    public ArrayList<AComponent> getChildren() {
        return children;
    }
}

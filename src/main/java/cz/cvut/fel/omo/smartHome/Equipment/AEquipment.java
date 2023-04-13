package cz.cvut.fel.omo.smartHome.Equipment;

import cz.cvut.fel.omo.smartHome.AComponent;
import cz.cvut.fel.omo.smartHome.City.IVisited;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;

public abstract class AEquipment extends AComponent implements IVisited {

    /**
     * Common constructor for equipment. It requires equipment's name
     *
     * @param modelName
     */
    public AEquipment(String modelName) {
        super(modelName);
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[3]).replace("\0", "-");
    }

    /**
     * Returns string that includes instance name
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getCurrentIndent()).append("Instance: ").append(getName()).append(" includes:");
        return sb.toString();
    }

    @Override
    protected void makeStep() {
    }

    @Override
    protected String consumptionToString() {
        return "";
    }

    /**
     * Calls metod visit() for current visitor
     *
     * @param visitor
     */
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}

package cz.cvut.fel.omo.smartHome.Animals;

import cz.cvut.fel.omo.smartHome.AComponent;
import cz.cvut.fel.omo.smartHome.City.City;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;
import cz.cvut.fel.omo.smartHome.Reports;
import cz.cvut.fel.omo.smartHome.ReusablePool;

import java.util.ArrayList;
import java.util.Random;

public abstract class AAnimal extends AComponent implements IVisitor {

    private ArrayList<StringBuilder> activities = ReusablePool.getInstance().getActivities();

    /**
     * Common constructor for animals. It requires animal's name
     * @param name
     */
    public AAnimal(String name) {
        super(name);
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[6]).replace("\0", "-");
    }

    //@Override
    protected void makeStep() {
        Random random = new Random();
        int s = 0;
        int f = 100;
        int x = s + random.nextInt(f - s + 1);
        if (x >= 10) {
            return;
        } // not visits this time
        f = 2;
        x = s + random.nextInt(f - s + 1);
        StringBuilder sb = new StringBuilder();
        switch (x) {
            case 0: {
                sb.append("Instance: ").append(getName()).append(" is asking for food ").append("\n");
                break;
            }
            case 1: {
                sb.append("Instance: ").append(getName()).append(" says: ").append(this.getVoice()).append("\n");
            }
            case 2: {
                sb.append("Instance: ").append(getName()).append(this.getMove()).append("\n");
            }
        }
        activities.add(sb);

    }

    protected abstract String getVoice();

    protected abstract String getMove();

    /**
     * Simulates visiting/vacating room in the house/ It requires component /
     * @param component
     */
    @Override
    public void visit(AComponent component) {

        boolean isOutSide = (component.getParent() == City.getInstance().getChildren().get(1)) ? true : false;
        if (isOutSide) {
            return;
        } // not visits this time
        Random random = new Random();
        int s = 0;
        int f = 100;
        int x = s + random.nextInt(f - s + 1);
        if (x >= 20) {
            return;
        } // not visits this time

        if (component.getParent() != this.parent) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has vacated ").append(parent.getName());
            activities.add(sb);
            sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has visited ").append(component.getParent().getName());
            activities.add(sb);
            parent.setInUse(false);
            parent.removeChild(this);
            component.getParent().addChild(this);
        }
        makeStep();
    }

}

package cz.cvut.fel.omo.smartHome.Family;

import cz.cvut.fel.omo.smartHome.*;
import cz.cvut.fel.omo.smartHome.City.City;
import cz.cvut.fel.omo.smartHome.Devices.IState;
import cz.cvut.fel.omo.smartHome.Devices.StatesType;
import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.IObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Person extends AComponent implements IVisitor, IObserver {

    private FamilyMember familyMember;

    private HashMap<String, Integer> usingDeviceCounter = new HashMap<>();
    private ArrayList<StringBuilder> activities = ReusablePool.getInstance().getActivities();

    /**
     * Constructor for object Person. It requires family member type
     *
     * @param familyMember
     */
    public Person(FamilyMember familyMember) {
        super(familyMember.name());
        this.familyMember = familyMember;
    }

    protected FamilyMember getFamilyMember() {
        return this.familyMember;
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[6]).replace("\0", "-");
    }

    @Override
    protected void makeStep() {

        Random random = new Random();
        int s = 0;
        int f = 100;
        int x = s + random.nextInt(f - s + 1);
        if (x > 20) {
            return;
        }
        f = 2;
        x = s + random.nextInt(f - s + 1);
        StringBuilder sb = new StringBuilder();
        switch (x) {
            case 0: {
                sb.append("Instance: ").append(getName()).append(" is reading news.");
                activities.add(sb);
                break;
            }
            case 1: {
                sb.append("Instance: ").append(getName()).append(" is watching TV.");
                activities.add(sb);
                break;
            }
            case 2: {
                sb.append("Instance: ").append(getName()).append(" sneezes.");
                activities.add(sb);
                break;
            }
        }
    }

    private void visitDevice(AComponent device) {
        if (usingDeviceCounter.containsKey(device.getName())) {
            int value = usingDeviceCounter.get(device.getName());
            value++;
            usingDeviceCounter.put(device.getName(), value);
        } else {
            usingDeviceCounter.put(device.getName(), 1);
        }
        StringBuilder sb = new StringBuilder();
        IState state = device.getState();
        if (state.getStatesType() == StatesType.ACTIVE) {
            sb.append("Instance: ").append(getName()).append(" has blocked ").append(device.getName()).append("\n");
        }
        if (state.getStatesType() == StatesType.BLOCKED) {
            sb.append("Instance: ").append(getName()).append(" has activated ").append(device.getName()).append("\n");
        }
        activities.add(sb);
        device.interact();
    }

    private void visitOutside(AComponent component) {
        if (component != this.parent && !component.isInUse()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has vacated ").append(parent.getName());
            activities.add(sb);
            sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has visited ").append(component.getName()).append("\n");
            activities.add(sb);
            parent.setInUse(false);
            component.setInUse(true);
            parent.removeChild(this);
            component.addChild(this);
        }
    }

    /**
     * Simulates visiting/leaving room
     *
     * @param component
     */
    @Override
    public void visit(AComponent component) {
        Random random = new Random();
        int s = 0;
        int f = 100;
        int x = s + random.nextInt(f - s + 1);
        if (x >= 20) {
            return;
        } // not visits this time
        boolean isMoveOutside = (component.getParent() == City.getInstance().getChildren().get(1)) ? true : false;
        if (isMoveOutside) {
            visitOutside(component);
            return;
        }
        if (component.getParent() != this.parent && !component.isInUse()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has vacated ").append(parent.getName());
            activities.add(sb);
            sb = new StringBuilder();
            sb.append("Instance: ").append(getName()).append(" has visited ").append(component.getParent().getName());
            activities.add(sb);
        }
        parent.setInUse(false);
        parent.removeChild(this);
        component.getParent().addChild(this);
        visitDevice(component);
    }

    /**
     * Getter for amount of device using
     *
     * @return
     */
    public HashMap<String, Integer> getUsingCounter() {
        return usingDeviceCounter;
    }

    /**
     * Generates event and adds it to array list
     *
     * @param event
     */
    @Override
    public void update(Event event) {

        ArrayList<Event> events = ReusablePool.getInstance().getEvents();
        EventType eventType = event.getEventType();
        String str = "     Instance: " + name;
        Event event1 = new Event();
        event1.setEventType(event.getEventType());
        event1.setMessageTypeEvent(event.getMessageTypeEvent());
        event1.setDevice(event.getDevice());
        event1.setDeviceType(event.getDeviceType());
        event1.setDeviceTypeEvent(event.getDeviceTypeEvent());
        switch (eventType) {
            case BREAKING_ENTERING_ALARM: {
                switch (familyMember) {
                    case FATHER: {
                        str = str + " is calling the police.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case MOTHER: {
                        str = str + " is calling the children.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case SON1: {
                        str = str + " is looking for parrot.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                }
                break;
            }
            case WATER_LEAK_ALARM: {
                switch (familyMember) {
                    case FATHER: {
                        str = str + " is turning off the water valve.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case MOTHER: {
                        str = str + " is starting to clean.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case DAUGHTER1: {
                        str = str + " is looking for cat.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                }
                break;
            }
            case GAS_LEAK_ALARM: {
                switch (familyMember) {
                    case FATHER: {
                        str = str + " is calling emergency service.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case MOTHER: {
                        str = str + " is turning off the gas valve.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case GRANDFATHER1: {
                        str = str + " is looking for dog.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                }
                break;
            }
            case SMOKE_ALARM: {
                switch (familyMember) {
                    case GRANDMOTHER1: {
                        str = str + " is calling 911.";
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                }
                break;
            }
            case BREAKING_ALARM: {
                switch (familyMember) {
                    case GRANDMOTHER1: {
                        str = str + " is reading repair manual.";
                        String manual = event.getDevice().getRepairManual();
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                    case GRANDFATHER1: {
                        str = str + " is reading repair manual and is calling the repair service";
                        String manual = event.getDevice().getRepairManual();
                        event1.setPersonAction(str);
                        event1.setPerson(this);
                        events.add(event1);
                        break;
                    }
                }
                break;
            }
            case DEFAULT: {
                break;
            }
        }
    }

}
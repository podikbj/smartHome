package cz.cvut.fel.omo.smartHome.Devices;

import cz.cvut.fel.omo.smartHome.Devices.StatesType;

public interface IState {

    abstract void changeState();

    abstract String toString();

    abstract int getPeriod();

    abstract void setPeriod(int period);

    abstract StatesType getStatesType();
}

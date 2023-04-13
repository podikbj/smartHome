package cz.cvut.fel.omo.smartHome.City;

import cz.cvut.fel.omo.smartHome.Family.IVisitor;

public interface IVisited {
    void accept(IVisitor visitor);
}

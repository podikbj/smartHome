package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.City.IVisited;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IVisitedIterator {

    private ArrayList<IVisited> visitables_array;
    private int pos = 0;

    /**
     * Constructor for object IVisitedIterator. It requires visitor array
     *
     * @param visitables
     */
    public IVisitedIterator(ArrayList<IVisited> visitables) {
        this.visitables_array = visitables;
    }

    protected IVisited next() {

        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        IVisited visited = visitables_array.get(pos);
        pos += 1;
        return visited;
    }

    protected boolean isDone() {
        return pos == visitables_array.size() - 1;
    }

    protected boolean hasNext() {
        return !isDone();
    }

}

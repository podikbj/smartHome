package cz.cvut.fel.omo.smartHome.Animals;

import cz.cvut.fel.omo.smartHome.Animals.AAnimal;

/**
 * @author kira
 * @version 1.0
 * @created 22-Nov-2020 10:33:40 AM
 */
public class Parrot extends AAnimal {

    /**
     * constructor for Parrot. It requires parrot's name
     *
     * @param name
     */
    public Parrot(String name) {
        super(name);
    }

    @Override
    protected String getVoice() {
        return "My name is Buddy.";
    }

    @Override
    protected String getMove() {
        return " is flying.";
    }

}
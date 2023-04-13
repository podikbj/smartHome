package cz.cvut.fel.omo.smartHome.Animals;

import cz.cvut.fel.omo.smartHome.Animals.AAnimal;

public class Cat extends AAnimal {

    /**
     * constructor for Cat. It requires cat's name
     *
     * @param name
     */
    public Cat(String name) {
        super(name);
    }

    @Override
    protected String getVoice() {
        return "Meu.";
    }

    @Override
    protected String getMove() {
        return " is sleeping.";
    }

}
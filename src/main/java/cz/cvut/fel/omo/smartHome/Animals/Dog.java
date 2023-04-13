package cz.cvut.fel.omo.smartHome.Animals;

import cz.cvut.fel.omo.smartHome.Animals.AAnimal;
import cz.cvut.fel.omo.smartHome.Reports;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dog extends AAnimal {

    private static Logger logger = Logger.getLogger(Reports.class.getName());

    /**
     * constructor for Dog. It requires dog's name
     *
     * @param name
     */
    public Dog(String name) {
        super(name);
    }

    @Override
    protected String getVoice() {
        logger.setLevel(Level.ALL);
        try {
            Handler fileHandler = new FileHandler("smartHomeDog.log");
            logger.addHandler(fileHandler);
            logger.info("Metod getVoice starts");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Haf.";
    }

    @Override
    protected String getMove() {
        return " is playing.";
    }

}
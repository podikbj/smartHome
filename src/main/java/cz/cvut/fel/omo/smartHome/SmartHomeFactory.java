package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.Animals.Cat;
import cz.cvut.fel.omo.smartHome.Animals.Dog;
import cz.cvut.fel.omo.smartHome.Animals.Parrot;
import cz.cvut.fel.omo.smartHome.City.*;
import cz.cvut.fel.omo.smartHome.Devices.*;
import cz.cvut.fel.omo.smartHome.Equipment.Bike;
import cz.cvut.fel.omo.smartHome.Equipment.Ski;
import cz.cvut.fel.omo.smartHome.Family.FamilyMember;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;
import cz.cvut.fel.omo.smartHome.Family.Person;
import cz.cvut.fel.omo.smartHome.ChainOfResponsibilityPattern.*;

import java.util.ArrayList;

public class SmartHomeFactory {

    private static SmartHomeFactory instance = null;
    private ArrayList<IVisited> visitableArray = ReusablePool.getInstance().getVisitable();
    private ArrayList<IVisitor> visitorsArray = ReusablePool.getInstance().getVisitor();
    private ArrayList<ADevice> devices = new ArrayList<>();
    private ArrayList<Person> personsArray = new ArrayList<>();
    private ArrayList<IObserver> observersArray = new ArrayList<>();

    private SmartHomeFactory() {
    }

    /**
     * Returns single instance of SmartHomeFactory
     *
     * @return
     */
    public static SmartHomeFactory getInstance() {

        if (instance == null) {
            instance = new SmartHomeFactory();
        }
        return instance;
    }

    protected City makeCity(String name) {
        return City.getInstance();
    }

    protected House makeHouse(String name, City city) {
        return House.getInstance();
    }

    protected Outside makeOutside(String name, City city) {
        return new Outside(name, city);
    }

    protected Floor makeFloor(String name, House house) {
        return new Floor(name, house);
    }

    protected Room makeRoom(Floor floor, RoomType roomType, BasicWindow basicWindow) {
        return new Room(floor, roomType, basicWindow);
    }

    protected Person makePerson(FamilyMember familyMember) {
        Person person = new Person(familyMember);
        visitorsArray.add(person);
        personsArray.add(person);
        observersArray.add(person);
        return person;
    }

    protected Cat makeCat(String name) {
        Cat cat = new Cat(name);
        visitorsArray.add(cat);
        return cat;
    }

    protected Dog makeDog(String name) {
        Dog dog = new Dog(name);
        visitorsArray.add(dog);
        return dog;
    }

    protected Parrot makeParrot(String name) {
        Parrot parrot = new Parrot(name);
        visitorsArray.add(parrot);
        return parrot;
    }

    protected AirConditioner makeAirConditioner(String modelName) {
        AirConditioner airConditioner = new AirConditioner(modelName);
        visitableArray.add(airConditioner);
        devices.add(airConditioner);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(airConditioner));
        return airConditioner;
    }

    protected CoffeeMachine makeCoffeeMachine(String modelName) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(modelName);
        visitableArray.add(coffeeMachine);
        devices.add(coffeeMachine);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(coffeeMachine));
        return coffeeMachine;
    }

    protected ElectronicLock makeElectronicLock(String modelName) {
        ElectronicLock electronicLock = new ElectronicLock(modelName);
        visitableArray.add(electronicLock);
        devices.add(electronicLock);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(electronicLock));
        return electronicLock;
    }

    protected SmokeAlarm makeSmokeAlarm(String modelName) {
        SmokeAlarm smokeAlarm = new SmokeAlarm(modelName);
        devices.add(smokeAlarm);
        HandlerSmokeAlarm.getInstance().getIndices().add(devices.indexOf(smokeAlarm));
        return smokeAlarm;
    }

    protected BurglarAlarm makeBurglarAlarm(String modelName) {
        BurglarAlarm burglarAlarm = new BurglarAlarm(modelName);
        devices.add(burglarAlarm);
        HandlerBurglarAlarm.getInstance().getIndices().add(devices.indexOf(burglarAlarm));
        return burglarAlarm;
    }

    protected GasBoiler makeGasBoiler(String modelName) {
        GasBoiler gasBoiler = new GasBoiler(modelName);
        visitableArray.add(gasBoiler);
        devices.add(gasBoiler);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(gasBoiler));
        return gasBoiler;
    }

    protected WaterLeakDetector makeWaterLeakDetector(String modelName) {
        WaterLeakDetector waterLeakDetector = new WaterLeakDetector(modelName);
        devices.add(waterLeakDetector);
        HandlerWaterLeak.getInstance().getIndices().add(devices.indexOf(waterLeakDetector));
        return waterLeakDetector;
    }

    protected GasLeakDetector makeGasLeakDetector(String modelName) {
        GasLeakDetector gasLeakDetector = new GasLeakDetector(modelName);
        devices.add(gasLeakDetector);
        HandlerGasLeak.getInstance().getIndices().add(devices.indexOf(gasLeakDetector));
        return gasLeakDetector;
    }

    protected WashingMachine makeWashingMachine(String modelName) {
        WashingMachine washingMachine = new WashingMachine(modelName);
        visitableArray.add(washingMachine);
        devices.add(washingMachine);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(washingMachine));
        return washingMachine;
    }

    protected Sensor makeSensor(String modelName) {
        Sensor sensor = new Sensor(modelName);
        visitableArray.add(sensor);
        devices.add(sensor);
        HandlerBreaking.getInstance().getIndices().add(devices.indexOf(sensor));
        return sensor;
    }

    protected Ski makeSki(String modelName) {
        Ski ski = new Ski(modelName);
        visitableArray.add(ski);
        return ski;
    }

    protected Bike makeBike(String modelName) {
        Bike bike = new Bike(modelName);
        visitableArray.add(bike);
        return bike;
    }

    /**
     * Getter for devices
     *
     * @return
     */
    public ArrayList<ADevice> getADeviceArray() {
        return this.devices;
    }

    protected ArrayList<Person> getPersonArrayList() {
        return personsArray;
    }

    /**
     * Getter for observers
     *
     * @return
     */
    public ArrayList<IObserver> getObserversArray() {
        return observersArray;
    }
}
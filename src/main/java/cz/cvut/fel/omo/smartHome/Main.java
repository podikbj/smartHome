package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.City.*;
import cz.cvut.fel.omo.smartHome.Devices.ADevice;
import cz.cvut.fel.omo.smartHome.Family.FamilyMember;
import cz.cvut.fel.omo.smartHome.Family.IVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Handler;

public class Main {
    public static void main(String[] args) throws IOException {

        ReusablePool reusablePool = ReusablePool.getInstance();
        SmartHomeFactory smartHomeFactory = SmartHomeFactory.getInstance();

        reusablePool.addComponent(smartHomeFactory.makeSmokeAlarm("SmokeAlarm SSS-1")); //3
        reusablePool.addComponent(smartHomeFactory.makeSmokeAlarm("SmokeAlarm SSS-2"));
        reusablePool.addComponent(smartHomeFactory.makeSmokeAlarm("SmokeAlarm SSS-3"));
        reusablePool.addComponent(smartHomeFactory.makeBurglarAlarm("BurglarAlarm BBB-1")); // 3
        reusablePool.addComponent(smartHomeFactory.makeBurglarAlarm("BurglarAlarm BBB-2")); //
        reusablePool.addComponent(smartHomeFactory.makeBurglarAlarm("BurglarAlarm BBB-3")); //
        reusablePool.addComponent(smartHomeFactory.makeWaterLeakDetector("WaterLeakDetector WWW")); //1
        reusablePool.addComponent(smartHomeFactory.makeGasLeakDetector("GasLeakDetector LLL")); // 1
        reusablePool.addComponent(smartHomeFactory.makeSensor("Sensor CCC"));
        reusablePool.addComponent(smartHomeFactory.makeAirConditioner("AirConditioner CCC-1"));
        reusablePool.addComponent(smartHomeFactory.makeAirConditioner("AirConditioner CCC-2"));
        reusablePool.addComponent(smartHomeFactory.makeAirConditioner("AirConditioner CCC-3"));
        reusablePool.addComponent(smartHomeFactory.makeCoffeeMachine("CoffeeMachine MMM-1")); //3
        reusablePool.addComponent(smartHomeFactory.makeCoffeeMachine("CoffeeMachine MMM-2"));
        reusablePool.addComponent(smartHomeFactory.makeCoffeeMachine("CoffeeMachine MMM-3"));
        reusablePool.addComponent(smartHomeFactory.makeElectronicLock("ElectronicLock EEE-1"));//3
        reusablePool.addComponent(smartHomeFactory.makeElectronicLock("ElectronicLock EEE-2"));
        reusablePool.addComponent(smartHomeFactory.makeElectronicLock("ElectronicLock EEE-3"));
        reusablePool.addComponent(smartHomeFactory.makeGasBoiler("GasBoiler GGG")); // 1
        reusablePool.addComponent(smartHomeFactory.makeWashingMachine("WashingMachine Boch-1"));
        reusablePool.addComponent(smartHomeFactory.makeWashingMachine("WashingMachine Boch-2"));// 2
        reusablePool.addComponent(smartHomeFactory.makeBike("Road bike"));
        reusablePool.addComponent(smartHomeFactory.makeBike("Mountain bike"));
        reusablePool.addComponent(smartHomeFactory.makeSki("Ski Sender"));
        reusablePool.addComponent(smartHomeFactory.makeSki("Ski Sheeva"));

        reusablePool.addComponent(smartHomeFactory.makeCat("Cat Simba"));
        reusablePool.addComponent(smartHomeFactory.makeDog("Dog Oscar"));
        reusablePool.addComponent(smartHomeFactory.makeParrot("Parrot Buddy"));

        City city = smartHomeFactory.makeCity("Springwill");
        Outside outside = smartHomeFactory.makeOutside("Outside", city);
        House house = smartHomeFactory.makeHouse("House", city);
        Floor floor1 = smartHomeFactory.makeFloor("Floor#1", house);
        Floor floor2 = smartHomeFactory.makeFloor("Floor#2", house);

        BasicWindow windowBig = new WindowBig();
        BasicWindow windowSmall = new WindowSmall();

        BasicWindow windowBigWoodBlinds = new BlindsWood(windowBig);
        BasicWindow windowBigPlasticBlinds = new BlindsPlastik(windowBig);
        BasicWindow windowSmallWoodBlinds = new BlindsWood(windowSmall);
        BasicWindow windowSmallPlasticBlinds = new BlindsPlastik(windowSmall);

        Room kitchen = smartHomeFactory.makeRoom(floor1, RoomType.KITCHEN, windowBigWoodBlinds);
        Room livingroom = smartHomeFactory.makeRoom(floor1, RoomType.LIVINGROOM, windowBigWoodBlinds);
        Room laundryroom = smartHomeFactory.makeRoom(floor1, RoomType.LAUNDRYROOM, windowSmallPlasticBlinds);
        Room bedroom = smartHomeFactory.makeRoom(floor2, RoomType.BEDROOM, windowBigPlasticBlinds);
        Room garage = smartHomeFactory.makeRoom(floor1, RoomType.GARAGE, windowSmallWoodBlinds);
        Room hall = smartHomeFactory.makeRoom(floor1, RoomType.HALL, windowBigWoodBlinds);

        reusablePool.addComponent(city);
        reusablePool.addComponent(house);
        reusablePool.addComponent(outside);
        reusablePool.addComponent(floor1);
        reusablePool.addComponent(floor2);
        reusablePool.addComponent(kitchen);
        reusablePool.addComponent(livingroom);
        reusablePool.addComponent(laundryroom);
        reusablePool.addComponent(bedroom);
        reusablePool.addComponent(garage);
        reusablePool.addComponent(hall);

        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.MOTHER));
        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.FATHER));
        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.GRANDMOTHER1));
        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.GRANDFATHER1));
        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.SON1));
        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.DAUGHTER1));

        kitchen.addChild(reusablePool.acquireComponent("CoffeeMachine MMM-1"));
        kitchen.addChild(reusablePool.acquireComponent("CoffeeMachine MMM-2"));
        kitchen.addChild(reusablePool.acquireComponent("CoffeeMachine MMM-3"));
        kitchen.addChild(reusablePool.acquireComponent("SmokeAlarm SSS-1"));
        kitchen.addChild(reusablePool.acquireComponent("Cat Simba"));
        kitchen.addChild(reusablePool.acquireComponent("AirConditioner CCC-3"));
        kitchen.addChild(reusablePool.acquireComponent("MOTHER"));

        laundryroom.addChild(reusablePool.acquireComponent("WashingMachine Boch-1"));
        laundryroom.addChild(reusablePool.acquireComponent("WashingMachine Boch-2"));
        laundryroom.addChild(reusablePool.acquireComponent("GasBoiler GGG"));
        laundryroom.addChild(reusablePool.acquireComponent("SmokeAlarm SSS-2"));
        laundryroom.addChild(reusablePool.acquireComponent("AirConditioner CCC-2"));

        livingroom.addChild(reusablePool.acquireComponent("Dog Oscar"));
        livingroom.addChild(reusablePool.acquireComponent("GRANDMOTHER1"));
        livingroom.addChild(reusablePool.acquireComponent("GRANDFATHER1"));
        livingroom.addChild(reusablePool.acquireComponent("SON1"));

        garage.addChild(reusablePool.acquireComponent("FATHER"));
        garage.addChild(reusablePool.acquireComponent("WaterLeakDetector WWW"));
        garage.addChild(reusablePool.acquireComponent("GasLeakDetector LLL"));
        garage.addChild(reusablePool.acquireComponent("BurglarAlarm BBB-1"));
        garage.addChild(reusablePool.acquireComponent("ElectronicLock EEE-3"));

        hall.addChild(reusablePool.acquireComponent("BurglarAlarm BBB-2"));
        hall.addChild(reusablePool.acquireComponent("BurglarAlarm BBB-3"));
        hall.addChild(reusablePool.acquireComponent("ElectronicLock EEE-1"));
        hall.addChild(reusablePool.acquireComponent("ElectronicLock EEE-2"));
        hall.addChild(reusablePool.acquireComponent("Sensor CCC"));

        bedroom.addChild(reusablePool.acquireComponent("DAUGHTER1"));
        bedroom.addChild(reusablePool.acquireComponent("Parrot Buddy"));
        bedroom.addChild(reusablePool.acquireComponent("SmokeAlarm SSS-3"));
        bedroom.addChild(reusablePool.acquireComponent("AirConditioner CCC-1"));

        floor1.addChild(reusablePool.acquireComponent("HALL"));
        floor1.addChild(reusablePool.acquireComponent("GARAGE"));
        floor1.addChild(reusablePool.acquireComponent("KITCHEN"));
        floor1.addChild(reusablePool.acquireComponent("LAUNDRYROOM"));
        floor1.addChild(reusablePool.acquireComponent("LIVINGROOM"));
        floor2.addChild(reusablePool.acquireComponent("BEDROOM"));

        house.addChild(reusablePool.acquireComponent("Floor#1"));
        house.addChild(reusablePool.acquireComponent("Floor#2"));

        outside.addChild(reusablePool.acquireComponent("Road bike"));
        outside.addChild(reusablePool.acquireComponent("Mountain bike"));
        outside.addChild(reusablePool.acquireComponent("Ski Sender"));
        outside.addChild(reusablePool.acquireComponent("Ski Sheeva"));

        city.addChild(house);
        city.addChild(outside);

        //////////////////////////+++++++++++++++++++++++++//////
        /*
         *  Second configuration
         **/
/////////////++++++++++++++++++++++++++++++++++++++++++++++//////

//        ReusablePool reusablePool = ReusablePool.getInstance();
//        SmartHomeFactory smartHomeFactory = SmartHomeFactory.getInstance();
//
//        reusablePool.addComponent(smartHomeFactory.makeWaterLeakDetector("WaterLeakDetector WWW")); //3
//        reusablePool.addComponent(smartHomeFactory.makeAirConditioner("AirConditioner CCC-1"));
//        reusablePool.addComponent(smartHomeFactory.makeElectronicLock("ElectronicLock EEE-2"));
//        reusablePool.addComponent(smartHomeFactory.makeSki("Ski Sheeva"));
//
//        City city = smartHomeFactory.makeCity("Springwill");
//        House house = smartHomeFactory.makeHouse("House", city);
//        Outside outside = smartHomeFactory.makeOutside("Outside", city);
//        Floor floor1 = smartHomeFactory.makeFloor("Floor#1", house);
//        BasicWindow windowBig = new WindowBig();
//        BasicWindow windowSmall = new WindowSmall();
//
//        BasicWindow windowBigWoodBlinds = new BlindsWood(windowBig);
//        BasicWindow windowBigPlasticBlinds = new BlindsPlastik(windowBig);
//        BasicWindow windowSmallWoodBlinds = new BlindsWood(windowSmall);
//        BasicWindow windowSmallPlasticBlinds = new BlindsPlastik(windowSmall);
//
//        Room kitchen = smartHomeFactory.makeRoom(floor1, RoomType.KITCHEN, windowBigWoodBlinds);
//        Room livingroom = smartHomeFactory.makeRoom(floor1, RoomType.LIVINGROOM, windowBigWoodBlinds);
//
//        reusablePool.addComponent(city);
//        reusablePool.addComponent(house);
//        reusablePool.addComponent(outside);
//        reusablePool.addComponent(floor1);
//        reusablePool.addComponent(kitchen);
//        reusablePool.addComponent(livingroom);
//
//        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.MOTHER));
//        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.FATHER));
//        reusablePool.addComponent(smartHomeFactory.makePerson(FamilyMember.GRANDFATHER1));
//
//        kitchen.addChild(reusablePool.acquireComponent("WaterLeakDetector WWW"));
//        kitchen.addChild(reusablePool.acquireComponent("AirConditioner CCC-1"));
//        kitchen.addChild(reusablePool.acquireComponent("FATHER"));
//
//        livingroom.addChild(reusablePool.acquireComponent("ElectronicLock EEE-2"));
//        livingroom.addChild(reusablePool.acquireComponent("MOTHER"));
//        livingroom.addChild(reusablePool.acquireComponent("GRANDFATHER1"));
//
//        floor1.addChild(reusablePool.acquireComponent("KITCHEN"));
//        floor1.addChild(reusablePool.acquireComponent("LIVINGROOM"));
//        outside.addChild(reusablePool.acquireComponent("Ski Sheeva"));
//        house.addChild(reusablePool.acquireComponent("Floor#1"));
//        city.addChild(house);
//        city.addChild(outside);
        /////////++++++/////////

        ArrayList<AComponent> allComponent = reusablePool.getReusableComponent();
        ArrayList<IVisited> visitables = reusablePool.getVisitable();
        IVisitedIterator it_visitables = new IVisitedIterator(visitables);

        ArrayList<IVisitor> visitors = reusablePool.getVisitor();
        ArrayList<ADevice> devices = smartHomeFactory.getADeviceArray();

        reusablePool.releaseAllComponents();

        int lifetime = 60;

        while (lifetime > 0) {
            for (AComponent component : allComponent) {
                component.makeStep();
            }

            for (; it_visitables.hasNext(); ) {
                IVisited element = it_visitables.next();
                for (IVisitor visitor : visitors) {
                    element.accept(visitor);
                }
            }

            if (lifetime == 30) {

                for (ADevice device : devices) {
                    device.simulateEvent();
                    device.checkDeviceStatus();
                }

            }

            lifetime--;
        }

        reusablePool.releaseComponent(city);

        Reports reports = Reports.getInstance();
        reports.printHouseConfigurationReport();
        reports.printEventReport();
        reports.printActivityAndUsageReport();
        reports.printConsumptionReport();

        File inputFile = new File("input.txt");
        int a = 0;
        int b = 0;
        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));) {
            String line = br.readLine();
            String[] itemsLine = line.split(" ");
            a = Integer.valueOf(itemsLine[0]);
            b = Integer.valueOf(itemsLine[1]);


        } catch (IOException ex) {

            ex.printStackTrace();
        }

        File outputFile = new File("output.txt");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            writer.println(a + b);

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }

    }
}

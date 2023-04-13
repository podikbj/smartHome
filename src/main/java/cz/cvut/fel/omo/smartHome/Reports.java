package cz.cvut.fel.omo.smartHome;

import cz.cvut.fel.omo.smartHome.Devices.DeviceType;
import cz.cvut.fel.omo.smartHome.Events.Event;
import cz.cvut.fel.omo.smartHome.Events.EventType;
import cz.cvut.fel.omo.smartHome.Family.Person;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Reports {

    private static Reports instance = null;
    private AComponent component;
    private ReusablePool reusablePool;
    private boolean firstIsInUse = false;
    private static Logger logger = Logger.getLogger(Reports.class.getName());
    private static Handler fileHandler = null;

    private Reports() throws IOException {
        this.reusablePool = ReusablePool.getInstance();
        this.component = reusablePool.acquireComponent("Springwill");
        logger.setLevel(Level.ALL);
        fileHandler = new FileHandler("smartHomeReports.log");
        logger.addHandler(fileHandler);
    }

    /**
     * Returns single instance of Reports
     *
     * @return
     * @throws IOException
     */
    public static Reports getInstance() throws IOException {

        if (instance == null) {
            instance = new Reports();
        }
        return instance;
    }

    protected void printHouseConfigurationReport() throws IOException {

        File myFile = new File("Reports.txt");

        if (firstIsInUse == false) {
            try {
                PrintWriter writer = new PrintWriter(myFile);
                logger.info("Clearing the contents of the Reports.txt file starts");
                writer.print("");
                writer.close();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error occur in file cleaning", e);
            }
            firstIsInUse = true;
        }

        try {
            PrintWriter writer = new PrintWriter(myFile);
            logger.info("Printing HouseConfigurationReport to Reports.txt file starts");
            ArrayList<String> array_str = AComponent.getStrPrinting();
            array_str.clear();
            writer.println("============================================================");
            writer.println("HOUSE CONFIGURATION REPORT");
            writer.println("============================================================");
            String str1 = component.toString();
            writer.println(str1);
            component.showComponentDetails();

            for (String str : array_str) {
                writer.println(str);
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occur in printing ConfigurationReport", ex);
            ex.printStackTrace();
        }
    }

    protected void printConsumptionReport() {

        File myFile = new File("Reports.txt");

        if (firstIsInUse == false) {
            try {
                PrintWriter writer = new PrintWriter(myFile);
                logger.info("Clearing the contents of the Reports.txt file starts");
                writer.print("");
                writer.close();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error occur in file cleaning", e);
            }
            firstIsInUse = true;
        }

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile, true)));
            logger.info("Printing ConsumptionReport to Reports.txt file starts");
            ArrayList<String> array_str = AComponent.getStrPrinting();
            array_str.clear();
            writer.println("============================================================");
            writer.println("HOUSE CONSUMPTION REPORT");
            writer.println("============================================================");
            component.showConsumptionDetails();
            for (String str : array_str) {
                writer.println(str);
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occur in printing", ex);
            ex.printStackTrace();
        }
    }

    protected void printEventReport() {
        File myFile = new File("Reports.txt");

        if (firstIsInUse == false) {
            try {
                PrintWriter writer = new PrintWriter(myFile);
                writer.print("");
                logger.info("Clearing the contents of the Reports.txt file starts");
                writer.close();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error occur in file cleaning", e);
            }
            firstIsInUse = true;
        }

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile, true)));
            logger.info("Printing EventReport to Reports.txt file starts");
            writer.println("============================================================");
            writer.println("HOUSE EVENT REPORT");
            writer.println("============================================================");

            ArrayList<Event> events = reusablePool.getEvents();
            StringBuilder sb;

            for (EventType et : EventType.values()) {
                List<Event> el_1 = events.stream().filter(p -> p.getEventType() == et)
                        .collect(Collectors.toList());
                writer.println(et.name() + " event happened;");
                for (DeviceType dt : DeviceType.values()) {
                    List<Event> el_2 = el_1.stream().filter(p -> p.getDeviceType() == dt).
                            collect(Collectors.toList());
                    if (el_2.size() != 0) {
                        writer.println(" Event initiated by device of type " + dt);
                        for (Event element : el_2) {
                            writer.println(element.getDeviceTypeEvent());
                            writer.println(element.getMessageTypeEvent());
                            writer.println(element.getPersonAction());
                        }
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occur in printing EventReport", ex);
            ex.printStackTrace();
        }

    }

    protected void printActivityAndUsageReport() {
        File myFile = new File("Reports.txt");
        if (firstIsInUse == false) {
            try {
                PrintWriter writer = new PrintWriter(myFile);
                writer.print("");
                logger.info("Clearing the contents of the Reports.txt file starts");
                writer.close();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error occur in file cleaning", e);
            }
            firstIsInUse = true;
        }

        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(myFile, true)));
            logger.info("Printing ActivityAndUsageReport to Reports.txt file starts");
            writer.println("============================================================");
            writer.println("HOUSE ACTIVITY REPORT");
            writer.println("============================================================");
            ArrayList<StringBuilder> activities = reusablePool.getActivities();
            for (StringBuilder element : activities) {
                String str = element.toString();
                writer.println(str);
            }
            writer.println("============================================================");
            writer.println("HOUSE USAGE REPORT");
            writer.println("============================================================");
            ArrayList<Person> persons = SmartHomeFactory.getInstance().getPersonArrayList();
            for (Person person : persons) {
                writer.println("Instance " + person.getName() + " used device: ");
                HashMap<String, Integer> hm = person.getUsingCounter();
                Iterator hmIterator = hm.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry) hmIterator.next();
                    String name = ((String) mapElement.getKey());
                    int number = ((int) mapElement.getValue());
                    writer.println("  " + name + " " + number + " times.");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occur in printing", ex);
            ex.printStackTrace();
        }
    }

    protected static Logger getLogger() {
        return logger;
    }

    protected static Handler getFileHandler() {
        return fileHandler;
    }
}
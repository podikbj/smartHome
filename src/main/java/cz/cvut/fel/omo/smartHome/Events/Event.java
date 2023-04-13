package cz.cvut.fel.omo.smartHome.Events;

import cz.cvut.fel.omo.smartHome.Devices.ADevice;
import cz.cvut.fel.omo.smartHome.Devices.DeviceType;
import cz.cvut.fel.omo.smartHome.Family.Person;

public class Event {

    private EventType eventType = EventType.DEFAULT;
    private Person person = null;
    private ADevice device = null;
    private DeviceType deviceType = DeviceType.DEFAULT;
    private String personAction = "";
    private String deviceTypeEvent = "";
    private String messageTypeEvent = "";

    /**
     * Getter for event type
     *
     * @return
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Setter for even type. Sets current event type
     *
     * @param eventType
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Setter for person. Sets current person
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Getter for device
     *
     * @return
     */
    public ADevice getDevice() {
        return device;
    }

    /**
     * Setter for device. Sets current device
     *
     * @param device
     */
    public void setDevice(ADevice device) {
        this.device = device;
    }

    /**
     * Getter for person action
     *
     * @return
     */
    public String getPersonAction() {
        return personAction;
    }

    /**
     * Setter for person action. Sets current action
     *
     * @param personAction
     */
    public void setPersonAction(String personAction) {
        this.personAction = personAction;
    }

    /**
     * Getter for device type
     *
     * @return
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * Setter for device type. Sets current device type
     *
     * @param deviceType
     */
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Getter for device type event
     *
     * @return
     */
    public String getDeviceTypeEvent() {
        return deviceTypeEvent;
    }

    /**
     * Setter for device type event. Sets current device type event
     *
     * @param deviceTypeEvent
     */
    public void setDeviceTypeEvent(String deviceTypeEvent) {
        this.deviceTypeEvent = deviceTypeEvent;
    }

    /**
     * Getter for message type event
     *
     * @return
     */
    public String getMessageTypeEvent() {
        return messageTypeEvent;
    }

    /**
     * Setter for message type event. Set current message type event
     *
     * @param messageTypeEvent
     */
    public void setMessageTypeEvent(String messageTypeEvent) {
        this.messageTypeEvent = messageTypeEvent;
    }
}

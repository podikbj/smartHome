package cz.cvut.fel.omo.smartHome.City;

import cz.cvut.fel.omo.smartHome.AComponent;

public class Room extends AComponent {

    private Floor floor;
    private RoomType roomType;
    private BasicWindow basicWindow;

    /**
     * Constructor for Room. It requires current floore, room's type and current basicWindow
     *
     * @param floor
     * @param roomType
     * @param basicWindow
     */
    public Room(Floor floor, RoomType roomType, BasicWindow basicWindow) {
        super(roomType.name());
        this.floor = floor;
        this.roomType = roomType;
        this.basicWindow = basicWindow;
    }

    protected RoomType getRoomType() {
        return this.roomType;
    }

    /**
     * Returns string that includes instance name
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        setCurrentIndent();
        sb.append(getCurrentIndent()).append("Instance ").append(getName()).append(basicWindow.getDescription())
                .append(" includes:");
        return sb.toString();
    }

    @Override
    protected void setCurrentIndent() {
        currentIndent = new String(new char[4]).replace("\0", "-");
    }

    @Override
    protected void makeStep() {
    }

}
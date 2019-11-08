package android.com.br.dummyreminder.to;

import java.io.Serializable;

public class Item implements ObjectTO, Serializable {

    public Item() { }

    private int ID;
    private long groupID;
    private String name;
    private String date;
    private int weekdays;
    private int hour;
    private int minute;
    private boolean isActive;
    private boolean isTriggered;

    public Item(int ID, int groupID, String name, String date, int weekdays,
                 int hour, int minute, boolean isActive, boolean isTriggered) {
        this.ID = ID;
        this.groupID = groupID;
        this.name = name;
        this.date = date;
        this.weekdays = weekdays;
        this.hour = hour;
        this.minute = minute;
        this.isActive = isActive;
        this.isTriggered = isTriggered;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(int weekdays) {
        this.weekdays = weekdays;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean triggered) {
        isTriggered = triggered;
    }
}

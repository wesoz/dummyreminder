package android.com.br.dummyreminder.to;

import android.com.br.dummyreminder.Utils;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class Item extends ObjectTO implements IObjectTO, Serializable, Comparator<Item> {

    public Item() {
        this.setID(new ObjectId().toString());
        this.setCreationDate(Utils.now());
    }

    private String _ID;
    private String _name;
    private Date _date;
    private int _weekdays;
    private int _hour;
    private int _minute;
    private boolean _isActive;
    private boolean _isTriggered;
    private Date _creationDate;

    public Item(String name, Date date, int weekdays,
                int hour, int minute, boolean isActive, boolean isTriggered) {
        this._ID = null;
        this._name = name;
        this._date = date;
        this._weekdays = weekdays;
        this._hour = hour;
        this._minute = minute;
        this._isActive = isActive;
        this._isTriggered = isTriggered;
        this._creationDate = Utils.now();
    }

    public Item(String ID, String name, Date date, int weekdays,
                 int hour, int minute, boolean isActive, boolean isTriggered) {
        this(name, date, weekdays, hour, minute, isActive, isTriggered);
        this._ID = ID;
    }

    public Item(String ID, String name, Date date, int weekdays,
                int hour, int minute, boolean isActive, boolean isTriggered, Date creationDate) {
        this(ID, name, date, weekdays, hour, minute, isActive, isTriggered);
        this._creationDate = creationDate;
    }

    @Override
    public void setID(String ID) { this._ID = ID; }

    @Override
    public String getID() {
        return this._ID;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        this._date = date;
    }

    public int getWeekdays() {
        return _weekdays;
    }

    public void setWeekdays(int weekdays) {
        this._weekdays = weekdays;
    }

    public int getHour() {
        return _hour;
    }

    public void setHour(int hour) {
        this._hour = hour;
    }

    public int getMinute() {
        return _minute;
    }

    public void setMinute(int minute) {
        this._minute = minute;
    }

    public boolean isActive() {
        return _isActive;
    }

    public void setActive(boolean active) {
        _isActive = active;
    }

    public boolean isTriggered() {
        return _isTriggered;
    }

    public void setTriggered(boolean triggered) {
        _isTriggered = triggered;
    }

    public Date getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this._creationDate = creationDate;
    }

    public String getFormattedTime() {
        String hour = "00" + this.getHour();
        String minute = "00" + this.getMinute();
        return hour.substring(hour.length() - 2) + ":" + minute.substring(minute.length() - 2);
    }

    @Override
    public int compare(Item o1, Item o2) {
        return (o1.getHour() + o1.getMinute()) - (o2.getHour() + o2.getMinute());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Item item = (Item)o;
        if (item.getID() == null) return false;
        if (this.getID() == null) return false;
        return this.getID().equals(item.getID());
    }
}

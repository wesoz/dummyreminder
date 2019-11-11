package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.Item;
import android.com.br.dummyreminder.to.IObjectTO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements IObjectDAO {

    private DBHelper dbHelper;

    public ItemDAO (Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void close() {
        this.dbHelper.close();
    }

    @Override
    public String getCollectionName() {
        return null;
    }

    @Override
    public long insert(IObjectTO object) {
        Item item = (Item)object;
        ContentValues values = new ContentValues();
        values.put(DBContract.Item.ID_GROUP, item.getGroupID());
        values.put(DBContract.Item.NAME, item.getName());
        values.put(DBContract.Item.DATE, item.getDate());
        values.put(DBContract.Item.WEEKDAYS, item.getWeekdays());
        values.put(DBContract.Item.HOUR, item.getHour());
        values.put(DBContract.Item.MINUTE, item.getMinute());
        values.put(DBContract.Item.IS_ACTIVE, item.isActive());
        values.put(DBContract.Item.IS_TRIGGERED, item.isTriggered());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long newRowID = db.insert(DBContract.Item.TABLE_NAME, null, values);

        return newRowID;
    }

    @Override
    public int update(IObjectTO object) {
        Item item = (Item) object;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Item.NAME, item.getName());
        values.put(DBContract.Item.DATE, item.getDate());
        values.put(DBContract.Item.WEEKDAYS, item.getWeekdays());
        values.put(DBContract.Item.HOUR, item.getHour());
        values.put(DBContract.Item.MINUTE, item.getMinute());
        values.put(DBContract.Item.IS_ACTIVE, item.isActive());
        values.put(DBContract.Item.IS_TRIGGERED, item.isTriggered());

        String selection = DBContract.Item.ID + " = ?";
        String[] selectionArgs = { String.valueOf(item.getID()) };

        int count = db.update(
                DBContract.Item.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    @Override
    public List<IObjectTO> select() {
        List<IObjectTO> objects = new ArrayList<>();
        String[] columns = DBContract.Item.getColumnNames();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DBContract.Item.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor.getColumnCount() > 0) {
            cursor.moveToFirst();
            while(cursor.moveToNext()) {
                Item object = new Item();

                object.setID(cursor.getInt(cursor.getColumnIndex(DBContract.Item.ID)));
                object.setGroupID(cursor.getInt(cursor.getColumnIndex(DBContract.Item.ID_GROUP)));
                object.setName(cursor.getString(cursor.getColumnIndex(DBContract.Item.NAME)));
                object.setDate(cursor.getString(cursor.getColumnIndex(DBContract.Item.DATE)));
                object.setWeekdays(cursor.getInt(cursor.getColumnIndex(DBContract.Item.WEEKDAYS)));
                object.setHour(cursor.getInt(cursor.getColumnIndex(DBContract.Item.HOUR)));
                object.setMinute(cursor.getInt(cursor.getColumnIndex(DBContract.Item.MINUTE)));
                object.setActive(cursor.getInt(cursor.getColumnIndex(DBContract.Item.IS_ACTIVE)) != 0);
                object.setTriggered(cursor.getInt(cursor.getColumnIndex(DBContract.Item.IS_TRIGGERED)) != 0);

                objects.add(object);
            }
        }

        db.close();

        return objects;
    }

    @Override
    public Item select(int ID) {
        return null;
    }

    @Override
    public void delete(int ID) {

    }
}

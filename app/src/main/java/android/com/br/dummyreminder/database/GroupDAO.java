package android.com.br.dummyreminder.database;

import android.com.br.dummyreminder.to.Group;
import android.com.br.dummyreminder.to.ObjectTO;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GroupDAO implements ObjectDAO {

    private DBHelper dbHelper;

    public GroupDAO (Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public void close() {
        this.dbHelper.close();
    }

    @Override
    public long insert(ObjectTO object) {

        Group group = (Group)object;
        ContentValues values = new ContentValues();
        values.put(DBContract.Group.NAME, group.getName());
        values.put(DBContract.Group.DESCRIPTION, group.getDescription());
        values.put(DBContract.Group.IS_ACTIVE, group.isActive());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long newRowID = db.insertOrThrow(DBContract.Group.TABLE_NAME, null, values);
        db.close();

        return newRowID;
    }

    @Override
    public int update(ObjectTO object) {
        Group group = (Group) object;
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Group.NAME, group.getName());
        values.put(DBContract.Group.DESCRIPTION, group.getDescription());
        values.put(DBContract.Group.IS_ACTIVE, group.isActive());

        String selection = DBContract.Group.ID + " = ?";
        String[] selectionArgs = { String.valueOf(group.getID()) };

        int count = db.update(
                DBContract.Group.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    @Override
    public List<ObjectTO> select() {

        List<ObjectTO> objects = new ArrayList<>();
        String[] columns = DBContract.Group.getColumnNames();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DBContract.Group.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Group object = new Group();

                object.setID(cursor.getInt(cursor.getColumnIndex(DBContract.Group.ID)));
                object.setName(cursor.getString(cursor.getColumnIndex(DBContract.Group.NAME)));
                object.setDescription(cursor.getString(cursor.getColumnIndex(DBContract.Group.DESCRIPTION)));
                object.setActive(cursor.getInt(cursor.getColumnIndex(DBContract.Group.IS_ACTIVE)) != 0);

                objects.add(object);
            }
        }

        db.close();

        return objects;
    }

    @Override
    public ObjectTO select(int ID) {
        return null;
    }

    @Override
    public void delete(int ID) {

    }
}

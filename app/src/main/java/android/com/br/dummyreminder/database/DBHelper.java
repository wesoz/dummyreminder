package android.com.br.dummyreminder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DummyReminder.db";
    private static final String SQL_CREATE_GROUP = DBContract.Group.getCreateScript();
    private static final String SQL_CREATE_ITEM = DBContract.Item.getCreateScript();
    private static final String SQL_DELETE_GROUP = "DROP TABLE IF EXISTS " + DBContract.Group.TABLE_NAME;
    private static final String SQL_DELETE_ITEM = "DROP TABLE IF EXISTS " + DBContract.Item.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_GROUP);
        db.execSQL(SQL_CREATE_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITEM);
        db.execSQL(SQL_DELETE_GROUP);

        onCreate(db);
    }
}

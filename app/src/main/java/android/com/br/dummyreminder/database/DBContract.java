package android.com.br.dummyreminder.database;

import android.provider.BaseColumns;

public final class DBContract {

    private DBContract() {}

    public static class Item implements BaseColumns {
        public static final String TABLE_NAME = "tbItem";
        public static final String ID = "idItem";
        public static final String ID_GROUP = "idGroup";
        public static final String NAME = "nmItem";
        public static final String DESCRIPTION = "dsItem";
        public static final String DATE = "vlDate";
        public static final String WEEKDAYS = "vlWeekdays";
        public static final String HOUR = "nrHour";
        public static final String MINUTE = "nrMinute";
        public static final String IS_ACTIVE = "flActive";
        public static final String IS_TRIGGERED = "flTriggered";

        public static String getCreateScript() {
            String SQL_CREATE =
                    "CREATE TABLE " + TABLE_NAME + " (" +
                            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            ID_GROUP + " INTEGER, " +
                            NAME + " TEXT, " +
                            DESCRIPTION + " TEXT, " +
                            DATE + " TEXT, " +
                            WEEKDAYS + " INTEGER, " +
                            HOUR + " INTEGER, " +
                            MINUTE + " INTEGER, " +
                            IS_ACTIVE + " INTEGER, " +
                            IS_TRIGGERED + " INTEGER, " +
                            " FOREIGN KEY (" + ID_GROUP + ") REFERENCES " + Group.TABLE_NAME + "(" + Group.ID + "));";

            return SQL_CREATE;
        }

        public static String[] getColumnNames() {
            String[] columns = {
                ID,
                ID_GROUP,
                NAME,
                DESCRIPTION,
                DATE,
                WEEKDAYS,
                HOUR,
                MINUTE,
                IS_ACTIVE,
                IS_TRIGGERED,
            };

            return columns;
        }
    }

    public static class Group implements BaseColumns {
        public static final String TABLE_NAME = "tbGroup";
        public static final String ID = "idGroup";
        public static final String NAME = "nmGroup";
        public static final String DESCRIPTION = "dsGroup";
        public static final String IS_ACTIVE = "flActive";

        public static String getCreateScript() {
           String SQL_CREATE =
                    "CREATE TABLE " + TABLE_NAME + " (" +
                            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                            NAME + " TEXT, " +
                            DESCRIPTION + " TEXT, " +
                            IS_ACTIVE + " INTEGER );";

           return SQL_CREATE;
        }


        public static String[] getColumnNames() {
            String[] columns = {
                    ID,
                    NAME,
                    DESCRIPTION,
                    IS_ACTIVE
            };

            return columns;
        }
    }
}

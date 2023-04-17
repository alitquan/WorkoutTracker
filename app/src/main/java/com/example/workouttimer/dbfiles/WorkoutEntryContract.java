package com.example.workouttimer.dbfiles;

import android.provider.BaseColumns;

public final class WorkoutEntryContract {

    private WorkoutEntryContract() {}

    public static class WorkoutEntry implements BaseColumns {
        public static final String TABLE_NAME = "workouts";
        public static final String COL_NAME_ONE ="name";
        public static final String COL_NAME_TWO ="numberOfExercises";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " ("+
                    WorkoutEntry._ID + " INTEGER PRIMARY KEY, " +
                    WorkoutEntry.COL_NAME_ONE + " TEXT, " +
                    WorkoutEntry.COL_NAME_TWO + " INT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME;
}

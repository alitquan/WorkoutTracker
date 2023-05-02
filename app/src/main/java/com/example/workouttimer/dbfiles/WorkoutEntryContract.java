package com.example.workouttimer.dbfiles;

import android.provider.BaseColumns;

public final class WorkoutEntryContract {

    private WorkoutEntryContract() {}

    public static class WorkoutEntry implements BaseColumns {

        public static final String TABLE_NAME     ="exercises";
        public static final String COL_NAME_ONE   ="exercise_name";
        public static final String COL_NAME_TWO   ="reps";
        public static final String COL_NAME_THREE ="workout";
        public static final String COL_NAME_FOUR  ="chronology";

        public static final String TABLE_NAME_TWO = "workouts";
        public static final String COL2_NAME_ONE = "workout_name";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME + " ("+
                WorkoutEntry._ID + " INTEGER PRIMARY KEY, " +
                WorkoutEntry.COL_NAME_ONE + " TEXT, " +
                WorkoutEntry.COL_NAME_TWO+ " INT, " +
                WorkoutEntry.COL_NAME_THREE + " TEXT, " +
                WorkoutEntry.COL_NAME_FOUR +" INT"   + ");";

    public static final String SQL_CREATE_ENTRIES_WORKOUTS =
            "CREATE TABLE " + WorkoutEntry.TABLE_NAME_TWO + " (" +
                    WorkoutEntry._ID + " INTEGER PRIMARY KEY, " +
                    WorkoutEntry.COL2_NAME_ONE + " TEXT);";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME +"; "+
            "DROP TABLE IF EXISTS " + WorkoutEntry.TABLE_NAME_TWO +"; ";
}

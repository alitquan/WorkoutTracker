package com.example.workouttimer.dbfiles;

import static com.example.workouttimer.dbfiles.WorkoutEntryContract.SQL_CREATE_ENTRIES;
import static com.example.workouttimer.dbfiles.WorkoutEntryContract.SQL_CREATE_ENTRIES_WORKOUTS;
import static com.example.workouttimer.dbfiles.WorkoutEntryContract.SQL_DELETE_ENTRIES;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "WorkingTable.db";
    SQLiteDatabase db;

    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // linking the helper
        db = this.getWritableDatabase();
        this.returnAllWorkouts();


        // adding to the database
//        ContentValues values = new ContentValues();
//        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pushups");
//        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 1);
//        long newRowId = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS exercises");
        db.execSQL("DROP TABLE IF EXISTS workouts");
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_WORKOUTS);
//        this.workoutDbTestInit();
//        this.insertDataOne();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS exercises");
        db.execSQL("DROP TABLE IF EXISTS workouts");
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_WORKOUTS);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onDestroy() {
        db.close();
    }

    public void addWorkout (String workout) {
        String query =
                "INSERT INTO " + WorkoutEntryContract.WorkoutEntry.TABLE_NAME_TWO + "("+ WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE +")" +
                "VALUES (\"" + workout + "\");";
        db.execSQL(query);
    }

    public void addWorkout2 (String workout) {
        ContentValues values = new ContentValues();
        values.put(WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE,workout);
        long newRowId = db.insert (WorkoutEntryContract.WorkoutEntry.TABLE_NAME_TWO,null,values);
    }


    // insert data into exercises table
    public void insertDataOne () {
        ContentValues values = new ContentValues();
        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pushups");
        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 1);
        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_THREE,"CHEST");
        long newRowId = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values);
        Log.e("DB", "inserted one");

        ContentValues values2 = new ContentValues();
        values2.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pullups");
        values2.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 2);
        values2.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_THREE,"BACK");
        long newRowId2 = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values2);
        Log.e("DB", "inserted two");
    }

    public void workoutDbTestInit() {
        ContentValues values = new ContentValues();
        values.put(WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE, "Arms");
        long newRowId = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME_TWO,null,values);

        ContentValues values2 = new ContentValues();
        values.put(WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE, "Mobility");
        long newRowId2 = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME_TWO,null,values);

    }

    public String [] returnAllExercises () {
        String [] projection = {WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE,WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO,WorkoutEntryContract.WorkoutEntry.COL_NAME_THREE};
        String retRow = "";
        String order = WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE + " ASC";
        Cursor cursor = db.query(
                WorkoutEntryContract.WorkoutEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                order
        );
        int cnt = cursor.getCount();
        int ele = 0;
        String [] retVal = new String [cnt];

        while (cursor.moveToNext()) {
            Log.d("returnAllExercises", "New Row");
            @SuppressLint("Range") String col1Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE));
            @SuppressLint("Range") String col2Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO));
            @SuppressLint("Range") String col3Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL_NAME_THREE));
            Log.d("returnAllExercises",col1Val);
            Log.d("returnAllExercises",col2Val);
            retRow = col1Val+","+col2Val+","+col3Val;
            retVal[ele++] = retRow;
        }
        return retVal;
    }


    public String [] returnAllWorkouts() {

        String [] projection = {WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE,};
        String retRow = "";
        String order = WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE + " ASC";
        Cursor cursor = db.query(
                WorkoutEntryContract.WorkoutEntry.TABLE_NAME_TWO,
                projection,
                null,
                null,
                null,
                null,
                order
        );
        int cnt = cursor.getCount();
        int ele = 0;
        String [] retVal = new String [cnt];

        while (cursor.moveToNext()) {
            Log.d("returnAllWorkouts", "Returning new row");
            @SuppressLint("Range") String col1Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL2_NAME_ONE));
            retRow = col1Val;
            retVal[ele++] = retRow;
        }

        for (int i = 0; i < retVal.length;i++) {
            Log.d("returnAllWorkouts " + i, retVal[i]);
        }
        return retVal;

    }

//    public String [] returnAllExercises(String workout) {
//
//    }





}

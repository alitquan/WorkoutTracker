package com.example.workouttimer.dbfiles;

import static com.example.workouttimer.dbfiles.WorkoutEntryContract.SQL_CREATE_ENTRIES;
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
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WorkingTable.db";
    SQLiteDatabase db;

    public WorkoutDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // linking the helper
        db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
        this.insertDataOne();

        // adding to the database
//        ContentValues values = new ContentValues();
//        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pushups");
//        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 1);
//        long newRowId = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onDestroy() {
        db.close();
    }


    public void insertDataOne () {
        ContentValues values = new ContentValues();
        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pushups");
        values.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 1);
        long newRowId = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values);
        Log.e("DB", "inserted one");

        ContentValues values2 = new ContentValues();
        values2.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE, "Pullups");
        values2.put(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO, 2);
        long newRowId2 = db.insert(WorkoutEntryContract.WorkoutEntry.TABLE_NAME, null, values2);
        Log.e("DB", "inserted two");

    }

    public String [] returnAll () {
        String [] projection = {WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE,WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO};
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
            Log.d("WorkoutDBHelper", "New Row");
            @SuppressLint("Range") String col1Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL_NAME_ONE));
            @SuppressLint("Range") String col2Val = cursor.getString(cursor.getColumnIndex(WorkoutEntryContract.WorkoutEntry.COL_NAME_TWO));
            Log.d("WorkoutDBHelper",col1Val);
            Log.d("WorkoutDBHelper",col2Val);
            retRow = col1Val+","+col2Val;
            retVal[ele++] = retRow;
        }
        return retVal;
    }





}

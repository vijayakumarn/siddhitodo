package com.siddhilabs.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vijaykumarn on 06-May-15.
 */
public class TodoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "siddhitodo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TodoDBContract.TodoTable.TABLE_NAME + " (" +
                    TodoDBContract.TodoTable._ID + " INTEGER PRIMARY KEY " +
                    ", " + TodoDBContract.TodoTable.COLUMN_NAME_TODO_TEXT + " TEXT" +
                    ")";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS "+ TodoDBContract.TodoTable.TABLE_NAME;

    public TodoDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}

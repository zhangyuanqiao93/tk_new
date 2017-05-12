package com.tkkj.tkeyes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fang on 2017/5/12.
 */

public class DataOpenhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inbox";
    private static final String MESSAGES_TABLE_CREATE = "create table FRAM_MODEL (id integer primary key, FRAM_ID text,DATA text);";

/*     db.execSQL("CREATE TABLE " + constraint + "\"FRAM_MODEL\" (" + //
            "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
            "\"FRAM_ID\" TEXT," + // 1: framId
            "\"USER_ID\" TEXT," + // 2: userId
            "\"DATA\" TEXT," + // 3: data
            "\"POSTED\" INTEGER NOT NULL ," + // 4: posted
            "\"TIMESTAMP\" TEXT);"); // 5: timestamp
}*/

    DataOpenhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MESSAGES_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
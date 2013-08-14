package com.hodanet.sanre.common.db;

import android.database.sqlite.SQLiteDatabase;

import com.hodanet.sanre.common.application.MyApplication;

public class DataBaseManager {

    protected static DataBaseHelper dbDataBaseHelper;
    protected static SQLiteDatabase sqlite;

    public DataBaseManager(){
        getDataBaseManager();
    }

    public static void getDataBaseManager() {
        if (dbDataBaseHelper == null) {
            dbDataBaseHelper = new DataBaseHelper(MyApplication.getInstance());
        }
        if (sqlite == null && dbDataBaseHelper != null) {
            sqlite = dbDataBaseHelper.getWritableDatabase();
        }
        return;
    }
}

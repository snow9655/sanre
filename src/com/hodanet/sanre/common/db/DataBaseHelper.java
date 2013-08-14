package com.hodanet.sanre.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int    VERSION = 1;
    private static final String DBNAME  = "sanre.db";

    public DataBaseHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // db.execSQL("create table if not exists tb_albumlist (pid integer primary key,albumid varchar(100),albumname varchar(50),albumimage varchar(60),introduce varchar(200),freetrack varchar(100),recordnum integer,recordpro integer,updatetime long)");
        db.execSQL("create table if not exists tb_temperature (pid integer primary key,ltemp varchar(20),ntemp varchar(20),htemp varchar(20),updatetime long ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS tb_albumlist");
        onCreate(db);

    }

}

package com.hodanet.sanre.business.service;

import android.content.ContentValues;
import android.database.Cursor;

import com.hodanet.sanre.business.model.TemperatureModel;
import com.hodanet.sanre.common.db.DataBaseManager;

public class SanreSQLiteService extends DataBaseManager {

    private static SanreSQLiteService sanreSQLiteService;

    public static SanreSQLiteService getInstance() {
        if (sanreSQLiteService == null) {
            sanreSQLiteService = new SanreSQLiteService();
        }
        return sanreSQLiteService;
    }

    private static final String TABLE_TEMPERATURE = "tb_temperature";

    public void addToTemperature(TemperatureModel temp) {
        ContentValues values = new ContentValues();
        values.put("ltemp", temp.getLtemp());
        values.put("ntemp", temp.getNtemp());
        values.put("htemp", temp.getHtemp());
        values.put("updatetime", System.currentTimeMillis());
        sqlite.insertOrThrow(TABLE_TEMPERATURE, null, values);
    }

    public TemperatureModel getTemperature() {
        TemperatureModel temp = new TemperatureModel();
        String[] columns = null;
        String selection = "";
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "updatetime desc";
        Cursor cursor = sqlite.query(TABLE_TEMPERATURE, columns, selection, selectionArgs, groupBy, having, orderBy);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                temp.setLtemp(cursor.getString(cursor.getColumnIndex("ltemp")));
                temp.setNtemp(cursor.getString(cursor.getColumnIndex("ntemp")));
                temp.setHtemp(cursor.getString(cursor.getColumnIndex("htemp")));
                temp.setTime(cursor.getLong(cursor.getColumnIndex("updatetime")));
            }
        }
        cursor.close();
        cursor = null;
        return temp;
    }
}

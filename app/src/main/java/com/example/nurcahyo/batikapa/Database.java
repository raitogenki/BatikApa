package com.example.nurcahyo.batikapa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.Random;

/**
 * Created by Nurcahyo on 12/27/2016.
 * Source:
 * Android SQLiteAssetHelper
 * https://github.com/jgilfelt/android-sqlite-asset-helper
 */
public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "Batik_Apa.db";
    private static final int DATABASE_VERSION = 1;
    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getBatikInfo(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        cursor = db.rawQuery("SELECT * FROM info WHERE id_batik =? ",new String[]{""+id+""});
        cursor.moveToFirst();
        return cursor;
    }
}

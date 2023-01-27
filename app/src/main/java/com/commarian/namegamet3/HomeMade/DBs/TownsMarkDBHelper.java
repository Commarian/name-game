package com.commarian.namegamet3.HomeMade.DBs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TownsMarkDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TownsMarkDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "TownsMarkDB";


    public TownsMarkDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public boolean SQLExist(@NotNull String what, String letter){
        try{
            letter = letter.toLowerCase();
            String query;
            //CAN USE '%' for wildcard searching before word or after word meaning it will find containing what
            if(what.length() > 5){
                query = "SELECT * FROM " + TABLE_NAME + " WHERE " + letter + " LIKE'" +"%"+ what.substring(0, 6) +"%" + "' LIMIT 10";
            }else{
                query = "SELECT * FROM " + TABLE_NAME + " WHERE " + letter + " LIKE'" + what + "' LIMIT 1";
            }
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor;
            if(db != null){
                cursor = db.rawQuery(query, null);
                boolean x = cursor.getCount() > 0;
                cursor.close();
                return x;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("FUCKED IN TownsMarkDBHelper + " + e);
            return false;
        }
    }



}

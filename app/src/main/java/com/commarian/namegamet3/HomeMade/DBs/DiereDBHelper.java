package com.commarian.namegamet3.HomeMade.DBs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiereDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DiereDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "DiereDB";


    public DiereDBHelper(@Nullable Context context) {
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
            //System.out.println("FUCKED IN DiereDBHelper + " + e);
            return false;
        }
    }

    public String GetRand(int letter_int, int rand){
        //System.out.println("DiereBHELPER GETRAND rand + " + rand);
        String seqid = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + "a" + " LIKE'" + "%%" + "'";
        Cursor c = null;
        if(db != null){
            c = db.rawQuery(query, null);
            if (c.getCount() > 0) {
                c.move(rand);
                try{
                    if(c.getString(letter_int) != null){
                        while(c.getString(letter_int).equalsIgnoreCase("")){
                            //System.out.println("IN DB + " + c.getString(letter_int) );
                            c.moveToPrevious();
                        }
                        seqid = c.getString(letter_int);
                        System.out.println("In DB..getSequenceID..."+seqid);
                        c.close();
                        return seqid;
                    }else{
                        return "";
                    }
                }catch(Exception e){
                    System.out.println("WARNING CUSTOM DiereDB FUCKED " + e);
                    c.close();
                    return "";
                }
            }else{
                c.close();
                return "";
            }
        }
        return "";
    }


}

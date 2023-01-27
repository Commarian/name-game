package com.commarian.namegamet3.HomeMade.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import static com.commarian.namegamet3.HomeMade.Startup.MainActivity.gameLang;


public class WordUploader {
    private static final FirebaseDatabase wdb = FirebaseDatabase.getInstance("https://w0rds.firebaseio.com/");

    public static void UploadWords(String Naam, String Van, String Dier, String Dorp){
        boolean a = Naam.length() > 1;
        boolean b = Van.length() > 1;
        boolean c = Dier.length() > 1;
        boolean d = Dorp.length() > 1;
        DatabaseReference wdba;

        if (gameLang.equals("afr")){
            if(a){
                wdba = wdb.getReference("naam");
                wdba.child(Naam).setValue(ServerValue.increment(1));
            }
            if(b){
                wdba = wdb.getReference("van");
                wdba.child(Van).setValue(ServerValue.increment(1));
            }
            if(c){
                wdba = wdb.getReference("dier");
                wdba.child(Dier).setValue(ServerValue.increment(1));
            }
            if(d){
                wdba = wdb.getReference("dorp");
                wdba.child(Dorp).setValue(ServerValue.increment(1));
            }

        }else{
            if(a){
                wdba = wdb.getReference("naam");
                wdba.child(Naam).setValue(ServerValue.increment(1));
            }
            if(b){
                wdba = wdb.getReference("van");
                wdba.child(Van).setValue(ServerValue.increment(1));
            }
            if(c){
                wdba = wdb.getReference("animal");
                wdba.child(Dier).setValue(ServerValue.increment(1));
            }
            if(d){
                wdba = wdb.getReference("town");
                wdba.child(Dorp).setValue(ServerValue.increment(1));
            }
        }
    }

}

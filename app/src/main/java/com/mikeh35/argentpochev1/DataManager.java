package com.mikeh35.argentpochev1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*Crea Classe DataManager extends SQLiteOpenHelper + implementation onCreate et onUpgrade
Crea constructeur avec super et 4 paramètres

*/
public class DataManager  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ArgentPoche.db";
    private static final int DATABASE_VERSION = 1;

    public DataManager (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Ecriture du create table
        String strSql = " create table T_Argent ( "
                +" id integer primary key autoincrement, "
                +" name text not null, "
                +" montant integer not null, "
                +"time integer not null )";

        db.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteTable (){
        String strSql = "delete from T_Argent";
        this.getWritableDatabase().execSQL(strSql);
    }

    public void insertArgent (String name, int montant){
        String strSql = "insert into T_Argent (name, montant, time) values ('"
                + name + "',"
                + montant + ","
                + new Date().getTime()//getTime type Long
                +")";
        this.getWritableDatabase().execSQL(strSql);
    }

    public int getTotal(String nom){
        int total = 0;
        String str = "'" + nom + "'";
        String strSql = "select montant from T_Argent where name = " + str ;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while(! cursor.isAfterLast()){
            total = total + cursor.getInt(0);
            cursor.moveToNext();
        }
    return total;
    }

    public List<Prenoms> getPrenoms(){
        List<Prenoms> prenoms = new ArrayList<>();
        String strSql = "select Distinct name from T_Argent";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Prenoms prenom = new Prenoms(cursor.getString(0));
            prenoms.add(prenom);
            cursor.moveToNext();
        }
        cursor.close();
        return prenoms;
    }

    public List<MontantData> readAll(){
        List<MontantData> montants = new ArrayList<>();
        String strSql = "select name, montant, time from T_Argent";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToLast();
        while(! cursor.isBeforeFirst()){
            MontantData montant = new MontantData(cursor.getString(0), cursor.getInt(1), new Date(cursor.getLong(2)));
            montants.add(montant);
            cursor.moveToPrevious();
        }

        cursor.close();
        return montants;

    }
}

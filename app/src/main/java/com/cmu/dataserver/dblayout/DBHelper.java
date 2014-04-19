package com.cmu.dataserver.dblayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.cmu.dataserver.entities.appointment.Appointment;
import com.cmu.dataserver.entities.patient.Patient;
import com.cmu.dataserver.entities.patient.PatientInterface;

/**
 * Created by SafenZhai on 4/13/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static int VERSION = 1;
    private final static String DB_NAME = "database.db";
    private SQLiteDatabase db;

    public DBHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, int version){
        this(context, name, null, version);
    }

    public DBHelper(Context context){
        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL("DROP TABLE IF EXISTS appointment");
        db.execSQL("DROP TABLE IF EXISTS department");
        db.execSQL("DROP TABLE IF EXISTS doctor");
        db.execSQL("DROP TABLE IF EXISTS document");
        db.execSQL("DROP TABLE IF EXISTS hospital");
        db.execSQL("DROP TABLE IF EXISTS patient");

        db.execSQL("CREATE TABLE hospital (HosID INTEGER PRIMARY KEY, HosName VARCHAR, " +
                "HosAdd VARCHAR, HosLocation VARCHAR, HosPhone VARCHAR)");
        db.execSQL("CREATE TABLE department (depID INTEGER PRIMARY KEY, hosID INTEGER, " +
                "DepName VARCHAR)");
        db.execSQL("CREATE TABLE doctor (DocID INTEGER PRIMARY KEY, DepID INTEGER, " +
                "DocName VARCHAR, DocPhone VARCHAR, DocSpeciality VARCHAR)");
        db.execSQL("CREATE TABLE patient (PatID INTEGER PRIMARY KEY, PatPsw INTEGER, " +
                "PatName VARCHAR, PatAge VARCHAR, PatGender VARCHAR, PatInsurance VARCHAR)");
        db.execSQL("CREATE TABLE appointment (AppID INTEGER PRIMARY KEY, DocID INTEGER, PatID INTEGER" +
                "AppDate VARCHAR, AppTime VARCHAR, AppAvailability INTEGER)");
        db.execSQL("CREATE TABLE document (DocuID INTEGER PRIMARY KEY, AppID INTEGER, " +
                "DocuContent VARCHAR )");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("update Database");

    }

    public void insert(ContentValues values, String table){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(table, null, values);
        db.close();
    }


    //query: table name, the searching column, searching value
    public Cursor query(String table){

        SQLiteDatabase db = getReadableDatabase();


        //get cursor

        Cursor c = db.query(table, null, null, null, null, null, null, null);
        return c;

    }

    public void delete(String table, String whereClause, String[]whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(table, whereClause, whereArgs); // db.delete("hospital",("HosId="+HosID),null);
    }

    public void update(ContentValues values, String table, String whereClause, String[]whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.update(table, values, whereClause, whereArgs); // db.update("hospital", cv, ("HosId=" + HosID), null);
    }



    public void close(){
        if(db != null){
            db.close();
        }
    }




}

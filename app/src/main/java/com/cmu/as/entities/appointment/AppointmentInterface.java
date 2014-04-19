package com.cmu.as.entities.appointment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.cmu.as.dblayout.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class AppointmentInterface  {

    DBHelper dbHelper;

    private Context mContext;

    public AppointmentInterface(Context context){
        mContext = context;
    }




    public void insertApp(Appointment a) {

        dbHelper = new DBHelper(mContext);

        ContentValues values = new ContentValues();
        values.put("AppID", a.getAppID());
        values.put("DocID", a.getDocID());
        values.put("PatID", a.getPatID());
        values.put("AppDate",a.getAppDate());
        values.put("AppTime", a.getAppTime());
        values.put("AppAvailability", a.getAppAvailability());

        dbHelper.insert(values,"appointment");

    }

    public void deleteApp(String AppID){

        dbHelper = new DBHelper(mContext);
        dbHelper.delete("appointment","AppID = ?", new String[]{AppID});

    }


    public void updateApp(Appointment a){

        dbHelper = new DBHelper(mContext);

        ContentValues values = new ContentValues();
        values.put("AppID", a.getAppID());
        values.put("DocID", a.getDocID());
        values.put("PatID", a.getPatID());
        values.put("AppDate",a.getAppDate());
        values.put("AppTime", a.getAppTime());
        values.put("AppAvailability", a.getAppAvailability());

        dbHelper.update(values,"appointment","AppID = ?", new String[]{Integer.toString(a.getAppID())});

    }



    public List<Appointment> getAppList() {
        dbHelper = new DBHelper(mContext);
        List<Appointment> appList = new ArrayList<Appointment>();



        Cursor c = dbHelper.query("appointment");
        while (c.moveToNext()){
            int AppID = c.getInt(c.getColumnIndex("AppID"));
            int DocID = c.getInt(c.getColumnIndex("DocID"));
            int PatID = c.getInt(c.getColumnIndex("PatID"));



            String AppDate = c.getString(c.getColumnIndex("AppDate"));
            String AppTime = c.getString(c.getColumnIndex("AppTime"));
            int AppAvailability = c.getInt(c.getColumnIndex("AppAvailability"));

            Appointment a = new Appointment();

            a.setAppID(AppID);
            a.setDocID(DocID);
            a.setPatID(PatID);
            a.setAppDate(AppDate);
            a.setAppTime(AppTime);
            a.setAppAvailability(AppAvailability);


            appList.add(a);
        }

        return appList;
    }


    //        db.execSQL("CREATE TABLE appointment (AppID INTEGER PRIMARY KEY, DocID INTEGER, PatID INTEGER" +
    //    "AppDate VARCHAR, AppTime VARCHAR, AppAvailability INTEGER)");


}

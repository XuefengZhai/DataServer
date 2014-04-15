package com.cmu.dataserver.entities.appointment;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.cmu.dataserver.app.R;
import com.cmu.dataserver.dblayout.DBHelper;
import com.cmu.dataserver.entities.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class AppointmentInterface extends ActionBarActivity {

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_interface);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appointment_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertApp(Appointment a) {

        dbHelper = new DBHelper(this);

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

        dbHelper = new DBHelper(this);
        dbHelper.delete("appointment","where AppID = ?", new String[]{AppID});

    }


    public void updateApp(Appointment a){

        dbHelper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put("AppID", a.getAppID());
        values.put("DocID", a.getDocID());
        values.put("PatID", a.getPatID());
        values.put("AppDate",a.getAppDate());
        values.put("AppTime", a.getAppTime());
        values.put("AppAvailability", a.getAppAvailability());

        dbHelper.update(values,"appointment","where AppID = ?", new String[]{Integer.toString(a.getAppID())});

    }



    public List<Appointment> getAppList() {
        dbHelper = new DBHelper(this);
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

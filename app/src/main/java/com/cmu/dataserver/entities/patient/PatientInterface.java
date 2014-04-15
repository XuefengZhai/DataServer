package com.cmu.dataserver.entities.patient;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.cmu.dataserver.app.R;
import com.cmu.dataserver.dblayout.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PatientInterface extends ActionBarActivity {

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_interface);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_interface, menu);
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


    public void insertPatient(Patient p) {

        dbHelper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put("PatID", p.getPatID());
        values.put("PatPsw", p.getPatPsw());
        values.put("PatName", p.getPatName());
        values.put("PatAge",p.getPatAge());
        values.put("PatGender", p.getPatGender());
        values.put("PatInsurance", p.getPatInsurance());

        dbHelper.insert(values,"patient");

    }

    public void deletePatient(String PatID){

        dbHelper = new DBHelper(this);
        dbHelper.delete("patient","where PatID = ?", new String[]{PatID});
    }

    public void updatePatient(Patient p){

        dbHelper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put("PatID", p.getPatID());
        values.put("PatPsw", p.getPatPsw());
        values.put("PatName", p.getPatName());
        values.put("PatAge",p.getPatAge());
        values.put("PatGender", p.getPatGender());
        values.put("PatInsurance", p.getPatInsurance());

        dbHelper.update(values,"patient","where PatID = ?", new String[]{Integer.toString(p.getPatID())});

    }

    public List<Patient> getPatientList() {
        dbHelper = new DBHelper(this);
        List<Patient> patientList = new ArrayList<Patient>();



        Cursor c = dbHelper.query("patient");
        while (c.moveToNext()){
            int PatID = c.getInt(c.getColumnIndex("PatID"));

            String PatPsw = c.getString(c.getColumnIndex("PatPsw"));
            String PatName = c.getString(c.getColumnIndex("PatName"));
            int PatAge = c.getInt(c.getColumnIndex("PatAge"));

            String PatGender = c.getString(c.getColumnIndex("PatGender"));
            String PatInsurance = c.getString(c.getColumnIndex("PatInsurance"));

            Patient p = new Patient();

            p.setPatID(PatID);
            p.setPatPsw(PatPsw);
            p.setPatName(PatName);
            p.setPatAge(PatAge);
            p.setPatGender(PatGender);
            p.setPatInsurance(PatInsurance);

            patientList.add(p);
        }

        return patientList;

    }


}










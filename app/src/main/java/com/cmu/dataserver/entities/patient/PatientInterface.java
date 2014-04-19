package com.cmu.dataserver.entities.patient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.cmu.dataserver.app.R;
import com.cmu.dataserver.dblayout.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PatientInterface {

    DBHelper dbHelper;

    private Context mContext;

    public PatientInterface(Context context){
        mContext = context;
    }



    public void insertPatient(Patient p) {

        dbHelper = new DBHelper(mContext);

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

        dbHelper = new DBHelper(mContext);
        dbHelper.delete("patient","PatID=?", new String[]{PatID});
    }

    public void updatePatient(Patient p){

        dbHelper = new DBHelper(mContext);

        ContentValues values = new ContentValues();
        values.put("PatID", p.getPatID());
        values.put("PatPsw", p.getPatPsw());
        values.put("PatName", p.getPatName());
        values.put("PatAge",p.getPatAge());
        values.put("PatGender", p.getPatGender());
        values.put("PatInsurance", p.getPatInsurance());

        dbHelper.update(values,"patient","PatID=?", new String[]{Integer.toString(p.getPatID())});

    }

    public List<Patient> getPatientList() {
        dbHelper = new DBHelper(mContext);
        List<Patient> patientList = new ArrayList<Patient>();



        Cursor c = dbHelper.query("patient");
        while (c.moveToNext()){
            int PatID = c.getInt(c.getColumnIndex("PatID"));

            String PatPsw = c.getString(c.getColumnIndex("PatPsw"));
            String PatName = c.getString(c.getColumnIndex("PatName"));
            String PatAge = c.getString(c.getColumnIndex("PatAge"));

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










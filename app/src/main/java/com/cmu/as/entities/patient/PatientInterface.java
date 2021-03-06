package com.cmu.as.entities.patient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.cmu.as.dblayout.DBHelper;

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
        //values.put("PatPsw", p.getPatPsw());
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
        //values.put("PatPsw", p.getPatPsw());
        values.put("PatName", p.getPatName());
        values.put("PatAge",p.getPatAge());
        values.put("PatGender", p.getPatGender());
        values.put("PatInsurance", p.getPatInsurance());

        dbHelper.update(values,"patient","PatID=?", new String[]{p.getPatID()});

    }

    public List<Patient> getPatientList() {
        dbHelper = new DBHelper(mContext);
        List<Patient> patientList = new ArrayList<Patient>();



        Cursor c = dbHelper.query("patient");
        while (c.moveToNext()){
            String PatID = c.getString(c.getColumnIndex("PatID"));

            //String PatPsw = c.getString(c.getColumnIndex("PatPsw"));
            String PatName = c.getString(c.getColumnIndex("PatName"));
            String PatAge = c.getString(c.getColumnIndex("PatAge"));

            String PatGender = c.getString(c.getColumnIndex("PatGender"));
            String PatInsurance = c.getString(c.getColumnIndex("PatInsurance"));

            Patient p = new Patient();

            p.setPatID(PatID);
            //p.setPatPsw(PatPsw);
            p.setPatName(PatName);
            p.setPatAge(PatAge);
            p.setPatGender(PatGender);
            p.setPatInsurance(PatInsurance);

            patientList.add(p);
        }

        return patientList;

    }


}










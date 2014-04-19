package com.cmu.dataserver.entities.doctor;

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

public class DoctorInterface {
    DBHelper dbHelper;

    private Context mContext;

    public DoctorInterface(Context context){
        mContext = context;
    }


    public void insertDoctor(Doctor d){

        dbHelper = new DBHelper(mContext);

        ContentValues values = new ContentValues();
        values.put("DocID", d.getDocID());
        values.put("DepID", d.getDepID());
        values.put("DocName", d.getDocName());
        values.put("DocPhone", d.getDocPhone());
        values.put("DocSpeciality", d.getDocSpeciality());

        dbHelper.insert(values,"doctor");

    }

    public void deleteDoctor(String DocID){

        dbHelper = new DBHelper(mContext);
        dbHelper.delete("doctor","DocID = ?", new String[]{DocID});
    }

    public void updateDoctor(Doctor d){

        dbHelper = new DBHelper(mContext);

        ContentValues values = new ContentValues();
        values.put("DocID", d.getDocID());
        values.put("DepID", d.getDepID());
        values.put("DocName", d.getDocName());
        values.put("DocPhone", d.getDocPhone());
        values.put("DocSpeciality", d.getDocSpeciality());

        dbHelper.update(values,"doctor","DocID = ?", new String[]{Integer.toString(d.getDocID())});

    }

    public List<Doctor> getDoctorList(){

        /*    private int DocID;
    private int DepID;
    private String DocName;
    private String DocPhone;
    private String DocSpeciality;
*/

        dbHelper = new DBHelper(mContext);
        List<Doctor> doctorList = new ArrayList<Doctor>();

        Cursor c = dbHelper.query("doctor");
        while (c.moveToNext()){
            int DocID = c.getInt(c.getColumnIndex("DocID"));
            int DepID = c.getInt(c.getColumnIndex("DepID"));

            String DocName = c.getString(c.getColumnIndex("DocName"));
            String DocPhone = c.getString(c.getColumnIndex("DocPhone"));
            String DocSpeciality = c.getString(c.getColumnIndex("DocSpeciality"));

            Doctor d = new Doctor();

            d.setDocID(DocID);
            d.setDepID(DepID);
            d.setDocName(DocName);
            d.setDocPhone(DocPhone);
            d.setDocSpeciality(DocSpeciality);

            doctorList.add(d);
        }
        return doctorList;

    }

}

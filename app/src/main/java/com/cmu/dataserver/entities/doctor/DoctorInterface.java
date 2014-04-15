package com.cmu.dataserver.entities.doctor;

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

public class DoctorInterface extends ActionBarActivity {
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_interface);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor_interface, menu);
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


    public void insertDoctor(Doctor d){

        dbHelper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put("DocID", d.getDocID());
        values.put("DepID", d.getDepID());
        values.put("DocName", d.getDocName());
        values.put("DocPhone", d.getDocPhone());
        values.put("DocSpeciality", d.getDocSpeciality());

        dbHelper.insert(values,"doctor");

    }

    public void deleteDoctor(String DocID){

        dbHelper = new DBHelper(this);
        dbHelper.delete("doctor","where DocID = ?", new String[]{DocID});
    }

    public void updateDoctor(Doctor d){

        dbHelper = new DBHelper(this);

        ContentValues values = new ContentValues();
        values.put("DocID", d.getDocID());
        values.put("DepID", d.getDepID());
        values.put("DocName", d.getDocName());
        values.put("DocPhone", d.getDocPhone());
        values.put("DocSpeciality", d.getDocSpeciality());

        dbHelper.update(values,"doctor","where DocID = ?", new String[]{Integer.toString(d.getDocID())});

    }

    public List<Doctor> getDoctorList(){

        /*    private int DocID;
    private int DepID;
    private String DocName;
    private String DocPhone;
    private String DocSpeciality;
*/

        dbHelper = new DBHelper(this);
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

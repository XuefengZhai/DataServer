package com.cmu.as.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cmu.as.dblayout.DBHelper;
import com.cmu.as.entities.patient.Patient;
import com.cmu.as.entities.patient.PatientInterface;
import com.cmu.as.socket.ServerThread;
import com.cmu.dataserver.app.R;

import java.util.List;


public class DataService extends ActionBarActivity {

    DBHelper dbHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_service);

        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);

        dbHelper = new DBHelper(this);
        dbHelper.onCreate(db);

        //Dummy Data here

        Patient p = new Patient();
        p.setPatID("3344");
        p.setPatInsurance("12345");
        p.setPatGender("Male");
        p.setPatAge("34");
        p.setPatName("David");

        PatientInterface PI = new PatientInterface(this);

        PI.insertPatient(p);

        p.setPatID("5566");
        p.setPatInsurance("12345");
        p.setPatGender("Male");
        p.setPatAge("34");
        p.setPatName("David");

        PI.insertPatient(p);



        List<Patient> PA;
        PA = PI.getPatientList();


        for(Patient ptest: PA){
        Log.i("",ptest.getPatName());
        Log.i("",ptest.getPatAge());
        Log.i("",ptest.getPatGender());
        Log.i("",ptest.getPatInsurance());
        Log.i("", (ptest.getPatID()));}

        PI.deletePatient("3344");


        PA = PI.getPatientList();


        for(Patient ptest: PA){
            Log.i("",ptest.getPatName());
            Log.i("",ptest.getPatAge());
            Log.i("",ptest.getPatGender());
            Log.i("",ptest.getPatInsurance());
            Log.i("", (ptest.getPatID()));}



        p.setPatID("5566");
        p.setPatInsurance("9999");
        p.setPatGender("Mal999e");
        p.setPatAge("39994");
        p.setPatName("D999avid");

        PI.updatePatient(p);

        PA = PI.getPatientList();

        for(Patient ptest: PA){
            Log.i("",ptest.getPatName());
            Log.i("",ptest.getPatAge());
            Log.i("",ptest.getPatGender());
            Log.i("",ptest.getPatInsurance());
            Log.i("", (ptest.getPatID()));}

        new ServerThread().start();

        db.close();
        dbHelper.close();

        /*
        ptest = PA.get(0);

        Log.i("",ptest.getPatName());
        Log.i("",ptest.getPatAge());
        Log.i("",ptest.getPatGender());
        Log.i("",ptest.getPatInsurance());
        Log.i("",ptest.getPatPsw());
        Log.i("", Integer.toString(ptest.getPatID()));
        */


        /*
        this.hos_init();
        this.hos_create(1,"KKK","PPP","Here","2341234");

        TextView text1 = (TextView)findViewById(R.id.tv1);
        TextView text2 = (TextView)findViewById(R.id.tv2);
        String show = this.hos_read("KKK");
        this.hos_update(1,"LLL","TTT","There","66667777");

        text1.setText(show);

        String show2 = this.hos_read("LLL");

        text2.setText(show2);

        */

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data, menu);
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

    /*
    public void hos_init(){
        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE hospital (HosID INTEGER PRIMARY KEY, HosName VARCHAR, " +
                "HosAdd VARCHAR, HosLocation VARCHAR, HosPhone VARCHAR)");
        db.close();

    }

    public void hos_create(int HosID,String HosName, String HosAdd, String HosLocation, String HosPhone){

        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);

        ContentValues cv = new ContentValues();
        cv.put("HosID", HosID);
        cv.put("HosName", HosName);
        cv.put("HosAdd",HosAdd);
        cv.put("HosLocation",HosLocation);
        cv.put("HosPhone",HosPhone);
        db.insert("hospital", null, cv);
        db.close();

    }

    public String hos_read(String HosName){

        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);
        String result = "";

        String sql = ("SELECT HosName from hospital where HosName = ?");
        Cursor c;
        c = db.rawQuery(sql,new String[] {HosName});
        c.moveToFirst();
        result=result+"HosName:"+c.getString(0)+" ";
        c.close();

        String sql1 = ("SELECT HosAdd from hospital where HosName =?");
        Cursor c1;
        c1 = db.rawQuery(sql1,new String[] {HosName});
        c1.moveToFirst();
        result=result+"HosAdd:"+c1.getString(0)+" ";
        c1.close();

        String sql2 = ("SELECT HosLocation from hospital where HosName = ?");
        Cursor c2;
        c2 = db.rawQuery(sql2,new String[] {HosName});
        c2.moveToFirst();
        result=result+"HosLocation:"+c2.getString(0)+" ";
        c2.close();

        String sql3 = ("SELECT HosPhone from hospital where HosName = ?");
        Cursor c3;
        c3 = db.rawQuery(sql3,new String[] {HosName});
        c3.moveToFirst();
        result=result+"HosName:"+c3.getString(0);
        c3.close();

        db.close();

        return result;

    }

    public void hos_update(int HosID, String HosName, String HosAdd, String HosLocation, String HosPhone){

        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);

        ContentValues cv = new ContentValues();
        cv.put("HosName", HosName);
        cv.put("HosAdd",HosAdd);
        cv.put("HosLocation",HosLocation);
        cv.put("HosPhone", HosPhone);
        db.update("hospital", cv, ("HosId=" + HosID), null);

        db.close();

    }

    public void hos_delete(int HosID){
        SQLiteDatabase db = openOrCreateDatabase("database.db", Context.MODE_PRIVATE, null);

        db.delete("hospital",("HosId="+HosID),null);

        db.close();


    }
*/




}

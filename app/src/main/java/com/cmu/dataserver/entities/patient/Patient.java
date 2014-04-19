package com.cmu.dataserver.entities.patient;

/**
 * Created by SafenZhai on 4/14/14.
 */
public class Patient {

    //        db.execSQL("CREATE TABLE patient (PatID INTEGER PRIMARY KEY, PatPsw INTEGER, " +
    //    "PatName VARCHAR, PatAge INTEGER, PatGender VARCHAR, PatInsurance VARCHAR)");

    private int PatID;
    private String PatPsw;
    private String PatName;
    private String PatAge;
    private String PatGender;
    private String PatInsurance;


    public int getPatID() {
        return PatID;
    }

    public void setPatID(int patID) {
        PatID = patID;
    }

    public String getPatPsw() {
        return PatPsw;
    }

    public void setPatPsw(String patPsw) {
        PatPsw = patPsw;
    }

    public String getPatName() {
        return PatName;
    }

    public void setPatName(String patName) {
        PatName = patName;
    }

    public String getPatAge() {
        return PatAge;
    }

    public void setPatAge(String patAge) {
        PatAge = patAge;
    }

    public String getPatGender() {
        return PatGender;
    }

    public void setPatGender(String patGender) {
        PatGender = patGender;
    }

    public String getPatInsurance() {
        return PatInsurance;
    }

    public void setPatInsurance(String patInsurance) {
        PatInsurance = patInsurance;
    }





}

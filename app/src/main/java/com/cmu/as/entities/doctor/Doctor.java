package com.cmu.as.entities.doctor;

/**
 * Created by SafenZhai on 4/14/14.
 */
public class Doctor {
    //       db.execSQL("CREATE TABLE doctor (DocID INTEGER PRIMARY KEY, DepID INTEGER, " +
     //       "DocName VARCHAR, DocPhone VARCHAR, DocSpeciality VARCHAR)");


    private int DocID;
    private int DepID;
    private String DocName;
    private String DocPhone;
    private String DocSpeciality;

    public int getDocID() {
        return DocID;
    }

    public void setDocID(int docID) {
        DocID = docID;
    }

    public int getDepID() {
        return DepID;
    }

    public void setDepID(int depID) {
        DepID = depID;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getDocPhone() {
        return DocPhone;
    }

    public void setDocPhone(String docPhone) {
        DocPhone = docPhone;
    }

    public String getDocSpeciality() {
        return DocSpeciality;
    }

    public void setDocSpeciality(String docSpeciality) {
        DocSpeciality = docSpeciality;
    }




}


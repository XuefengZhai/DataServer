package com.cmu.as.socket;

import android.util.Log;

import com.cmu.as.entities.patient.Patient;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SafenZhai on 4/19/14.
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream out;

    public void run(){

        try{
            Log.i("","Enter Run");
            // Open a server socket listening on port 8888
            serverSocket = new ServerSocket(8888);
            clientSocket = serverSocket.accept();

            Log.i("","After accept");


            out = new ObjectOutputStream(clientSocket.getOutputStream());

            Patient pat = new Patient();

            pat.setPatAge("21");

            pat.setPatName("Batman");

            pat.setPatGender("Male");

            pat.setPatInsurance("110119");

            out.writeObject(pat);

            out.close();

            serverSocket.close();

            clientSocket.close();

            out.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }



}

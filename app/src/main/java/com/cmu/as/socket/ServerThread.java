package com.cmu.as.socket;

import android.util.Log;

import com.cmu.as.entities.patient.Patient;

import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

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
            InetAddress addr = InetAddress.getByName(getLocalIpAddress());
            serverSocket = new ServerSocket(8888, 0, addr);
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

    private String getLocalIpAddress() throws Exception {
        String resultIpv6 = "";
        String resultIpv4 = "";

        for (Enumeration en = NetworkInterface.getNetworkInterfaces();
             en.hasMoreElements();) {

            NetworkInterface intf = (NetworkInterface)en.nextElement();
            for (Enumeration enumIpAddr = intf.getInetAddresses();
                 enumIpAddr.hasMoreElements();) {

                InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                if(!inetAddress.isLoopbackAddress()){
                    if (inetAddress instanceof Inet4Address) {
                        resultIpv4 = inetAddress.getHostAddress().toString();
                    } else if (inetAddress instanceof Inet6Address) {
                        resultIpv6 = inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        return ((resultIpv4.length() > 0) ? resultIpv4 : resultIpv6);
    }




}

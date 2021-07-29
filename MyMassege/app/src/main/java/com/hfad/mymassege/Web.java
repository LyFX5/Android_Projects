package com.hfad.mymassege;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Web {

    public void foo(){
        byte[] data = new byte[1024];
       try{
           //InetAddress is = new InetAddress()
           DatagramSocket ds = new DatagramSocket(6666);
           DatagramPacket dp = new DatagramPacket(data,data.length);
           ds.send(dp);
       }catch (IOException ex){
           ex.printStackTrace();
       }
    }

}

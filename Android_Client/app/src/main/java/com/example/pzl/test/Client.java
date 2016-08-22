package com.example.pzl.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {


    public void start(String string) throws Exception{

        Writer writer;
        Socket client = new Socket();
        InetSocketAddress addr = new InetSocketAddress("192.168.1.102",11111);
        client.connect(addr);

        writer = new OutputStreamWriter(client.getOutputStream());
        writer.write(string);
        writer.flush();
        Thread.sleep(10);

        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String mes = null;
        mes = reader.readLine();
        System.out.println("from server:" + mes);

        reader.close();
        writer.close();
        client.close();
    }

/*    public String Getmes(){
        String string ;
        return string;
    }
*/

    public static void main(String args[])throws Exception{







    }
}

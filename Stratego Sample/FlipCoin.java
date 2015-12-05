// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:52:25 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   FlipCoin.java

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class FlipCoin extends Thread
{

    public FlipCoin(Socket socket, Socket socket1)
    {
        System.err.println("FlipCoin: demonstration version 1.0");
        if(Stratego.isVerbose())
            Stratego.traceIn("FlipCoin", "constructor");
        readSocket = socket;
        writeSocket = socket1;
        Random random = new Random();
        myToss = random.nextInt();
        start();
        try
        {
            DataInputStream datainputstream = new DataInputStream(socket.getInputStream());
            opponentToss = datainputstream.readInt();
        }
        catch(IOException ioexception)
        {
            System.err.println("Elect Master: read error.  " + ioexception);
        }
        if(Stratego.isVerbose())
            Stratego.traceOut("FlipCoin", "constructor");
    }

    public void run()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("FlipCoin", "run");
        try
        {
            DataOutputStream dataoutputstream = new DataOutputStream(writeSocket.getOutputStream());
            dataoutputstream.writeInt(myToss);
        }
        catch(IOException ioexception)
        {
            System.err.println("Elect Master: write error.  " + ioexception);
        }
        if(Stratego.isVerbose())
            Stratego.traceOut("FlipCoin", "run");
    }

    public boolean imFirst()
    {
        return myToss > opponentToss;
    }

    private Socket readSocket;
    private Socket writeSocket;
    private int myToss;
    private int opponentToss;
}
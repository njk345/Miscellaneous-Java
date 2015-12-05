// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:51:54 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConnectionManager.java

import java.awt.Dialog;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

public class ConnectionManager extends Thread
{

    public ConnectionManager(String s)
    {
        readSocket = null;
        writeSocket = null;
        System.err.println("ConnectionManager: demonstration version 1.0");
        start();
        boolean flag = false;
        int i = 0;
        NotifyDialog notifydialog = new NotifyDialog("Connection Manager", "Waiting to connect with " + s);
        while(!flag) 
            try
            {
                writeSocket = new Socket(s, 8002);
                writeSocket.setTcpNoDelay(true);
                writeSocket.setSoLinger(true, 60);
                flag = true;
            }
            catch(SocketException socketexception)
            {
                if(i++ > 100)
                {
                    System.err.println("Failed to connect with " + s + " after " + i + " attempts");
                    flag = true;
                }
            }
            catch(UnknownHostException unknownhostexception)
            {
                System.err.println("ConnectionManager: " + unknownhostexception);
                flag = true;
            }
            catch(IOException ioexception)
            {
                System.err.println("ConnectionManager: " + ioexception);
                flag = true;
            }
        notifydialog.dispose();
    }

    public void run()
    {
        try
        {
            ServerSocket serversocket = new ServerSocket(8002);
            readSocket = serversocket.accept();
        }
        catch(UnknownHostException unknownhostexception)
        {
            System.err.println("Connection Manager: " + unknownhostexception);
        }
        catch(IOException ioexception)
        {
            System.err.println("Connection Manager: " + ioexception);
        }
    }

    public Socket getReadSocket()
    {
        return readSocket;
    }

    public Socket getWriteSocket()
    {
        return writeSocket;
    }

    private Socket readSocket;
    private Socket writeSocket;
}
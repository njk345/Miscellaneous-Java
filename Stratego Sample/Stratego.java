// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:55:44 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Stratego.java

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintStream;
import java.net.*;
import javax.swing.JOptionPane;

public class Stratego
{

    public static void main(String args[])
    {
        new Stratego(args);
    }

    public Stratego(String as[])
    {
        opponentIPAddress = "";
        System.out.println("Stratego: version 1.0");
        System.out.println("Programmed by Brad Wall and Don Collier");
        setGameSize();
        if(as.length == 0)
        {
            getLocalIP();
            String as1[] = {
                "Play", "Demo", "Trace", "Debug", "Quit", "Help"
            };
            int i = JOptionPane.showOptionDialog(null, "Please select a game mode", "Stratego", 1, 3, null, as1, as1[0]);
            switch(i)
            {
            case 0: // '\0'
                networked = true;
                verbose = false;
                break;

            case -1: 
            case 4: // '\004'
                System.exit(0);
                // fall through

            case 1: // '\001'
                networked = false;
                verbose = false;
                break;

            case 2: // '\002'
                networked = false;
                verbose = true;
                break;

            case 3: // '\003'
                networked = true;
                verbose = true;
                break;

            case 5: // '\005'
                helping = true;
                networked = false;
                verbose = false;
                break;
            }
            if(!networked && !verbose && helping)
            {
                help = new HelpFrame();
                help.helpInfo();
            }
            if(networked && !helping)
                do
                {
                    opponentIPAddress = JOptionPane.showInputDialog(null, "Your IP address is: " + localIPAddress + "\nEnter opponent IP address below", "Stratego IP Addresses", 1);
                    if(opponentIPAddress == null)
                        System.exit(0);
                } while(opponentIPAddress.equals(""));
        } else
        {
            opponentIPAddress = as[0];
        }
        if(verbose && !helping)
            trace = new TraceFrame();
        if(networked && !helping)
        {
            ConnectionManager connectionmanager = new ConnectionManager(opponentIPAddress);
            try
            {
                connectionmanager.join();
            }
            catch(InterruptedException interruptedexception) { }
            writeSocket = connectionmanager.getWriteSocket();
            readSocket = connectionmanager.getReadSocket();
            FlipCoin flipcoin = new FlipCoin(readSocket, writeSocket);
            try
            {
                flipcoin.join();
            }
            catch(InterruptedException interruptedexception1) { }
            byte byte0;
            if(flipcoin.imFirst())
                byte0 = 1;
            else
                byte0 = 2;
            Game game = new Game(byte0, new MoveWriter(writeSocket));
            new MoveReader(game, readSocket);
        }
        if(!networked && !verbose && !helping)
            new Game(1, null);
    }

    public void setGameSize()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        width = dimension.width / 18;
        height = dimension.height / 12;
    }

    private void getLocalIP()
    {
        try
        {
            localIPAddress = InetAddress.getLocalHost().getHostAddress();
        }
        catch(SecurityException securityexception)
        {
            System.err.println("SECURITY ERROR: " + securityexception);
            System.exit(1);
        }
        catch(UnknownHostException unknownhostexception)
        {
            System.err.println("HOST ERROR: " + unknownhostexception);
            System.exit(1);
        }
    }

    public static int getWidth()
    {
        return width;
    }

    public static int getHeight()
    {
        return height;
    }

    public static boolean isNetworked()
    {
        return networked;
    }

    public static boolean isVerbose()
    {
        return verbose;
    }

    public static void trace(String s, String s1)
    {
        trace.append(indent + "Tracing  Class: " + s + ", Method: " + s1);
    }

    public static void trace(String s, String s1, String s2)
    {
        trace.append(indent + "Tracing  Class: " + s + ", Method: " + s1 + "; " + s2);
    }

    public static void traceIn(String s, String s1)
    {
        trace.append(indent + "BEGIN  Class: " + s + ", Method: " + s1);
        indent = indent + "   ";
    }

    public static void traceIn(String s, String s1, String s2)
    {
        trace.append(indent + "BEGIN  Class: " + s + ", Method: " + s1 + "; " + s2);
        indent = indent + "   ";
    }

    public static void traceOut(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer(indent);
        indent = stringbuffer.substring(3);
        trace.append(indent + "EXIT  Class: " + s + ", Method: " + s1);
    }

    public static void traceOut(String s, String s1, String s2)
    {
        StringBuffer stringbuffer = new StringBuffer(indent);
        indent = stringbuffer.substring(3);
        trace.append(indent + "EXIT  Class: " + s + ", Method: " + s1 + "; " + s2);
    }

    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int PLAY = 0;
    public static final int DEMO = 1;
    public static final int TRACE = 2;
    public static final int DEBUG = 3;
    public static final int HELP = 5;
    public static final int QUIT = 4;
    private static boolean networked = true;
    private static boolean verbose = false;
    private static boolean helping = false;
    private static int width;
    private static int height;
    private String localIPAddress;
    private String opponentIPAddress;
    private Socket readSocket;
    private Socket writeSocket;
    private static TraceFrame trace = null;
    private static String indent = "";
    private static HelpFrame help = null;

}
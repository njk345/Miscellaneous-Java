// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:57:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TraceFrame.java

import java.awt.*;
import javax.swing.*;

public class TraceFrame extends JFrame
{

    public TraceFrame()
    {
        window = new JTextArea();
        JScrollPane jscrollpane = new JScrollPane(window);
        getContentPane().add(jscrollpane);
        setTitle("Program Trace");
        setSize(600, 500);
        show();
    }

    public void append(String s)
    {
        window.append(s + "\n");
    }

    private JTextArea window;
}
// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:54:43 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   NotifyDialog.java

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class NotifyDialog extends JDialog
{

    public NotifyDialog(String s, String s1)
    {
        this(s, s1, 300, 150);
    }

    public NotifyDialog(String s, String s1, int i, int j)
    {
        setTitle(s);
        setSize(i, j);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width - getWidth()) / 2, (dimension.height - getHeight()) / 2);
        getContentPane().add(new JLabel(s1, 0));
        getContentPane().setBackground(Color.red);
        show();
    }

    public static void main(String args[])
    {
        NotifyDialog notifydialog = new NotifyDialog("Notify Dialog", "This is a message");
        try
        {
            Thread.sleep(10000L);
        }
        catch(InterruptedException interruptedexception) { }
        notifydialog.dispose();
        System.exit(0);
    }
}
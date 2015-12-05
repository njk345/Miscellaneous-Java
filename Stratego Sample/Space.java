// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:55:25 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Space.java

import java.awt.*;
import java.io.PrintStream;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Space extends JPanel
{

    public Space(int i, int j, int k)
    {
        token = null;
        displayToken = true;
        row = i;
        col = j;
        playerNumber = k;
        setPreferredSize(new Dimension(Stratego.getWidth(), Stratego.getHeight()));
        setBackground(Color.yellow);
    }

    public void setToken(Token token1)
    {
        token = token1;
    }

    public Token rmToken()
    {
        if(Stratego.isVerbose())
            Stratego.trace("Space", "rmToken");
        Token token1 = token;
        token = null;
        return token1;
    }

    public Token getToken()
    {
        if(Stratego.isVerbose())
            Stratego.trace("Space", "getToken");
        return token;
    }

    public void setDisplayToken(boolean flag)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Space", "setDisplayToken");
        displayToken = flag;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawRect(0, 0, Stratego.getWidth(), Stratego.getHeight());
        if(token != null && displayToken)
            token.draw(g, this, playerNumber);
    }

    private Token token;
    private boolean displayToken;
    private int playerNumber;
    public final int row;
    public final int col;

    static 
    {
        System.err.println("Space: demonstration version 1.0");
    }
}
// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:55:05 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SetupMove.java


public class SetupMove extends TokenMove
{

    public SetupMove(int i, int j, int k, int l)
    {
        super(i, j, k, l);
        toTray = false;
    }

    public SetupMove(int i, int j, int k, int l, boolean flag)
    {
        super(i, j, k, l);
        toTray = false;
        toTray = flag;
    }

    public boolean toTray;
}
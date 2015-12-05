// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:53:25 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ModeMove.java


public class ModeMove
    implements Move
{

    public ModeMove(int i)
    {
        mode = i;
    }

    public int mode;
    public static final int QUIT = 0;
    public static final int READY = 1;
    public static final int DEMO = 2;
}
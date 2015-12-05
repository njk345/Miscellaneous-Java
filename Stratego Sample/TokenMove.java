// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:56:46 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TokenMove.java


public class TokenMove
    implements Move
{

    public TokenMove(int i, int j, int k, int l)
    {
        row0 = i;
        col0 = j;
        row1 = k;
        col1 = l;
    }

    public int row0;
    public int col0;
    public int row1;
    public int col1;
}
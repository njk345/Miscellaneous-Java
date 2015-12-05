// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:51:05 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Board.java

import java.awt.*;
import java.io.PrintStream;
import javax.swing.*;

public class Board extends JPanel
{

    public Board(int i, int j, int k, Game game)
    {
        System.err.println("Board: demonstration version 1.0");
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "constructor", "rows = " + i + "  cols = " + j + "  playerNumber = " + k);
        rows = i;
        cols = j;
        playerNumber = k;
        init();
        for(int l = 0; l < i; l++)
        {
            for(int i1 = 0; i1 < j; i1++)
                spaces[l][i1].addMouseListener(game);

        }

        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "constructor");
    }

    public Board(int i, int j, int k)
    {
        System.err.println("Board: demonstration version 1.0");
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "constructor", "rows = " + i + "  cols = " + j + "  playerNumber = " + k);
        rows = i;
        cols = j;
        playerNumber = k;
        init();
        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "constructor");
    }

    public void fillWithTokens()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "fillWithTokens");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                spaces[i][j].setToken(new Token(Token.tokenTypes[i * cols + j], playerNumber));

        }

        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "fillWithTokens");
    }

    public void tokensVisible(boolean flag)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "tokensVisible");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                spaces[i][j].setDisplayToken(flag);

        }

        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "tokensVisible");
    }

    public boolean isEmpty(int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "isEmpty", "row = " + i + "  col = " + j);
        return spaces[i][j].getToken() == null;
    }

    public boolean isEmpty()
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "isEmpty");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                if(!isEmpty(i, j))
                    return false;

        }

        return true;
    }

    public void setToken(Token token, int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "setToken", "row = " + i + "  col = " + j);
        spaces[i][j].setToken(token);
    }

    public Token rmToken(int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "rmToken", "row = " + i + "  col = " + j);
        return spaces[i][j].rmToken();
    }

    public Token getToken(int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "getToken", "row = " + i + "  col = " + j);
        return spaces[i][j].getToken();
    }

    public void moveToken(int i, int j, int k, int l, boolean flag)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "moveToken", "row0 = " + i + "  col0 = " + j + "  row1 = " + k + "  col1 = " + l);
        spaces[k][l].setToken(spaces[i][j].rmToken());
        if(flag)
            spaces[k][l].getToken().expose(spaces[k][l]);
    }

    public void placeToken(Token token)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "placeToken");
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
                if(isEmpty(i, j))
                {
                    spaces[i][j].setToken(token);
                    return;
                }

        }

        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "placeToken");
    }

    public void placeToken(Token token, int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "placeToken", "row = " + i + "  col = " + j);
        spaces[i][j].setToken(token);
    }

    public int getRank(int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Board", "getRank", "row = " + i + "  col = " + j);
        return spaces[i][j].getToken().getRank();
    }

    private void init()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Board", "init");
        spaces = new Space[rows][cols];
        setLayout(new GridLayout(rows, cols, 0, 0));
        setBorder(BorderFactory.createRaisedBevelBorder());
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                spaces[i][j] = new Space(i, j, playerNumber);
                add(spaces[i][j]);
            }

        }

        if(Stratego.isVerbose())
            Stratego.traceOut("Board", "init");
    }

    private int rows;
    private int cols;
    private int playerNumber;
    private Space spaces[][];
}
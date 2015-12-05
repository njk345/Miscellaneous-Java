// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 6/24/2003 12:52:52 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Game.java

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.EventObject;
import javax.swing.*;

public class Game extends JFrame
    implements WindowListener, MouseListener, ActionListener
{

    public Game(int i, MoveWriter movewriter)
    {
        control = new JPanel();
        play = new JButton("Play");
        exit = new JButton("Exit");
        modeLabel = new JLabel("Setup");
        setUp = true;
        readiness = 2;
        myTurn = true;
        sendMove = null;
        System.err.println("Game: demonstration version 1.0");
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "constructor", "playerNumber = " + i + "  sendMove = " + movewriter);
        playerNumber = i;
        sendMove = movewriter;
        opponentNumber = i != 1 ? 1 : 2;
        battleField = new Board(10, 10, i, this);
        tray = new Board(10, 4, i, this);
        tray.fillWithTokens();
        if(Stratego.isNetworked())
        {
            opponentTray = new Board(10, 4, opponentNumber);
            opponentTray.tokensVisible(false);
        } else
        {
            opponentTray = new Board(10, 4, opponentNumber, this);
        }
        opponentTray.fillWithTokens();
        Container container = getContentPane();
        javax.swing.border.Border border = BorderFactory.createRaisedBevelBorder();
        control.setBorder(border);
        container.add(battleField, "Center");
        container.add(tray, "East");
        container.add(opponentTray, "West");
        container.add(control, "South");
        control.add(play);
        control.add(exit);
        if(Stratego.isNetworked())
            control.add(modeLabel);
        placeLake();
        addWindowListener(this);
        play.addActionListener(this);
        exit.addActionListener(this);
        Insets insets = getInsets();
        Dimension dimension = control.getPreferredSize();
        int j = 18 * Stratego.getWidth() + insets.left + insets.right + insets.left;
        int k = 10 * Stratego.getHeight() + insets.bottom + insets.top + dimension.height;
        setTitle("Stratego");
        setSize(j, k);
        setResizable(false);
        show();
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "constructor");
    }

    private void placeLake()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "placeLake");
        battleField.placeToken(new Token(12, 0), 4, 2);
        battleField.placeToken(new Token(12, 0), 4, 3);
        battleField.placeToken(new Token(12, 0), 5, 2);
        battleField.placeToken(new Token(12, 0), 5, 3);
        battleField.placeToken(new Token(12, 0), 4, 6);
        battleField.placeToken(new Token(12, 0), 4, 7);
        battleField.placeToken(new Token(12, 0), 5, 6);
        battleField.placeToken(new Token(12, 0), 5, 7);
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "placeLake");
    }

    private boolean validateMove()
    {
        if(Stratego.isVerbose())
            Stratego.trace("Game", "validateMove");
        if(!myTurn && Stratego.isNetworked())
            return false;
        Token token = startSpace.getToken();
        if(token == null)
            return false;
        if(startSpace.getParent() == tray)
            return false;
        if(token.getPlayerNumber() != playerNumber && Stratego.isNetworked())
            return false;
        int i = token.getRank();
        if(i == 0 || i == 11 || i == 12)
            return false;
        int j = Math.abs(endSpace.col - startSpace.col);
        int k = Math.abs(endSpace.row - startSpace.row);
        if(i == 9)
        {
            if(!validateScoutMove(j, k))
                return false;
        } else
        if((j != 0 || k != 1) && (j != 1 || k != 0))
            return false;
        if(endSpace.getParent() == tray)
            return false;
        Token token1 = endSpace.getToken();
        if(token1 == null)
            return true;
        if(token1.getPlayerNumber() == playerNumber && Stratego.isNetworked())
            return false;
        return token1.getRank() != 12;
    }

    private boolean validateScoutMove(int i, int j)
    {
        if(Stratego.isVerbose())
            Stratego.trace("Game", "validateScoutMove", "  dCol = " + i + "  dRow = " + j);
        if(j != 0 && i != 0)
            return false;
        if((j > 1 || i > 1) && endSpace.getToken() != null)
            return false;
        if(i == 0)
        {
            int k = startSpace.row;
            int i1 = endSpace.row;
            for(int k1 = Math.min(k, i1) + 1; k1 < Math.max(k, i1); k1++)
                if(!battleField.isEmpty(k1, endSpace.col))
                    return false;

        } else
        {
            int l = startSpace.col;
            int j1 = endSpace.col;
            for(int l1 = Math.min(l, j1) + 1; l1 < Math.max(l, j1); l1++)
                if(!battleField.isEmpty(endSpace.row, l1))
                    return false;

        }
        return true;
    }

    private boolean validateSetupMove()
    {
        if(Stratego.isVerbose())
            Stratego.trace("Game", "validateSetupMove");
        if(startSpace.getToken() == null)
            return false;
        if(startSpace.getToken().getRank() == 12)
            return false;
        if(endSpace.getToken() != null)
            return false;
        if(endSpace.getParent() == tray)
            return startSpace.getParent() != tray;
        int i = startSpace.getToken().getPlayerNumber();
        if(i == playerNumber && endSpace.row < 6)
            return false;
        return i == playerNumber || endSpace.row <= 3;
    }

    private void strike()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "strike");
        int i = startSpace.getToken().getRank();
        int j = endSpace.getToken().getRank();
        StrikeMove strikemove = new StrikeMove(startSpace.row, startSpace.col, endSpace.row, endSpace.col);
        if(Stratego.isNetworked())
            sendMove.setMove(strikemove);
        if(i == 10 && j == 1)
        {
            toTray(endSpace.rmToken());
            endSpace.setToken(startSpace.rmToken());
        } else
        if(j == 11)
        {
            if(i == 8)
            {
                toTray(endSpace.rmToken());
                endSpace.setToken(startSpace.rmToken());
            } else
            {
                toTray(startSpace.rmToken());
                endSpace.getToken().expose(endSpace);
            }
        } else
        if(j == 0)
        {
            JOptionPane.showMessageDialog(this, "You Win!!");
            System.exit(0);
        } else
        if(i == j)
        {
            toTray(endSpace.rmToken());
            toTray(startSpace.rmToken());
        } else
        if(i < j)
        {
            toTray(endSpace.rmToken());
            endSpace.setToken(startSpace.rmToken());
        } else
        {
            toTray(startSpace.rmToken());
            startSpace.setToken(endSpace.rmToken());
            startSpace.getToken().expose(startSpace);
        }
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "strike");
    }

    private void opponentStrike(int i, int j, int k, int l)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "validateMove", "row0 = " + i + ", col0 = " + j + ", row1 = " + k + ", col1 = " + l);
        int i1 = battleField.getRank(i, j);
        int j1 = battleField.getRank(k, l);
        if(j1 == 10 && i1 == 1)
        {
            toTray(battleField.rmToken(i, j));
            battleField.moveToken(k, l, i, j, true);
        } else
        if(i1 == 11)
        {
            if(j1 == 8)
            {
                toTray(battleField.rmToken(i, j));
                battleField.moveToken(k, l, i, j, true);
            } else
            {
                toTray(battleField.rmToken(k, l));
            }
        } else
        if(i1 == 0)
        {
            JOptionPane.showMessageDialog(this, "Bummer -- Opponent Wins");
            System.exit(0);
        } else
        if(i1 == j1)
        {
            toTray(battleField.rmToken(k, l));
            toTray(battleField.rmToken(i, j));
        } else
        if(i1 < j1)
        {
            toTray(battleField.rmToken(k, l));
            battleField.moveToken(i, j, k, l, false);
        } else
        {
            toTray(battleField.rmToken(i, j));
            battleField.moveToken(k, l, i, j, true);
        }
        repaint();
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "validateMove");
    }

    private synchronized void checkReadiness()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "checkReadiness");
        if(--readiness == 0 || !Stratego.isNetworked())
        {
            setUp = false;
            opponentTray.tokensVisible(true);
            repaint();
        }
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "checkReadiness");
    }

    public void opponentMove(Move move)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "opponentMove");
        if(move instanceof ModeMove)
        {
            ModeMove modemove = (ModeMove)move;
            if(modemove.mode == 1)
                checkReadiness();
            else
            if(modemove.mode == 0)
                System.exit(1);
        } else
        if(move instanceof SetupMove)
        {
            SetupMove setupmove = (SetupMove)move;
            if(setupmove.toTray)
            {
                Token token = battleField.rmToken(10 - ((TokenMove) (setupmove)).row0 - 1, 10 - ((TokenMove) (setupmove)).col0 - 1);
                opponentTray.setToken(token, ((TokenMove) (setupmove)).row1, ((TokenMove) (setupmove)).col1);
            } else
            {
                Token token1 = opponentTray.rmToken(((TokenMove) (setupmove)).row0, ((TokenMove) (setupmove)).col0);
                battleField.setToken(token1, 10 - ((TokenMove) (setupmove)).row1 - 1, 10 - ((TokenMove) (setupmove)).col1 - 1);
            }
        } else
        if(move instanceof StrikeMove)
        {
            StrikeMove strikemove = (StrikeMove)move;
            opponentStrike(10 - ((TokenMove) (strikemove)).row1 - 1, 10 - ((TokenMove) (strikemove)).col1 - 1, 10 - ((TokenMove) (strikemove)).row0 - 1, 10 - ((TokenMove) (strikemove)).col0 - 1);
            myTurn = true;
            modeLabel.setText("My Turn");
        } else
        {
            TokenMove tokenmove = (TokenMove)move;
            battleField.moveToken(10 - tokenmove.row0 - 1, 10 - tokenmove.col0 - 1, 10 - tokenmove.row1 - 1, 10 - tokenmove.col1 - 1, false);
            myTurn = true;
            modeLabel.setText("My Turn");
        }
        repaint();
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "opponentMove");
    }

    private void toTray(Token token)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "toTray");
        if(token.getPlayerNumber() == playerNumber)
            tray.placeToken(token);
        else
            opponentTray.placeToken(token);
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "toTray");
    }

    private void stopGame()
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "stopGame");
        int i = JOptionPane.showConfirmDialog(this, "End Game?", "Confirm Game End", 0, 3);
        if(i == 0)
        {
            if(Stratego.isNetworked())
                sendMove.setMove(new ModeMove(0));
            System.exit(0);
        }
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "stopGame");
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        startSpace = (Space)mouseevent.getSource();
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "mouseReleased");
        if(setUp)
        {
            if(validateSetupMove())
            {
                endSpace.setToken(startSpace.rmToken());
                Object obj;
                if(startSpace.getParent() == tray)
                    obj = new SetupMove(startSpace.row, startSpace.col, endSpace.row, endSpace.col);
                else
                if(endSpace.getParent() == tray)
                    obj = new SetupMove(startSpace.row, startSpace.col, endSpace.row, endSpace.col, true);
                else
                    obj = new TokenMove(startSpace.row, startSpace.col, endSpace.row, endSpace.col);
                if(Stratego.isNetworked())
                    sendMove.setMove(((Move) (obj)));
            }
        } else
        if(validateMove())
        {
            Token token = endSpace.getToken();
            if(token != null)
            {
                strike();
            } else
            {
                endSpace.setToken(startSpace.rmToken());
                if(Stratego.isNetworked())
                    sendMove.setMove(new TokenMove(startSpace.row, startSpace.col, endSpace.row, endSpace.col));
            }
            myTurn = false;
            modeLabel.setText("Opponent's Turn");
        }
        repaint();
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "mouseReleased");
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
        endSpace = (Space)mouseevent.getSource();
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        if(Stratego.isVerbose())
            Stratego.traceIn("Game", "actionPerformed");
        Object obj = actionevent.getSource();
        if(obj == play)
        {
            if(!tray.isEmpty())
            {
                int i = JOptionPane.showConfirmDialog(this, "The Tray is not empty; start game anyway?", "Configuration Warning", 0);
                if(i == 1)
                    return;
            }
            checkReadiness();
            myTurn = playerNumber == 1;
            if(myTurn)
                modeLabel.setText("My Turn");
            else
                modeLabel.setText("Opponent's Turn");
            if(Stratego.isNetworked())
                sendMove.setMove(new ModeMove(1));
            play.setEnabled(false);
        } else
        if(obj == exit)
            stopGame();
        if(Stratego.isVerbose())
            Stratego.traceOut("Game", "actionPerformed");
    }

    public void windowClosing(WindowEvent windowevent)
    {
        stopGame();
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    private Board battleField;
    private Board tray;
    private Board opponentTray;
    private JPanel control;
    private JButton play;
    private JButton exit;
    private JLabel modeLabel;
    private Space startSpace;
    private Space endSpace;
    private int playerNumber;
    private int opponentNumber;
    private boolean setUp;
    private int readiness;
    private boolean myTurn;
    private MoveWriter sendMove;
    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int TRAY = 4;
}
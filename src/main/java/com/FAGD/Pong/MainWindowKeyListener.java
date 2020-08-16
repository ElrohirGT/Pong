/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FAGD.Pong;

import Objects.PongBar;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author familia
 */
public class MainWindowKeyListener implements KeyListener {

    PongBar LeftBar;
    PongBar RightBar;
    int MarginTop, GameHeight;
    JPanel Panel;
    private final Set<Integer> PressedKeys = new HashSet<>();
    
    public MainWindowKeyListener(PongBar leftBar, PongBar rightBar, int marginTop, int gameHeight, JPanel panel){
        LeftBar = leftBar;
        RightBar = rightBar;
        MarginTop = marginTop;
        GameHeight = gameHeight;
        Panel = panel;
    }
    
    //@Override
    //public void actionPerformed(ActionEvent e) {
//        MovePongBar(Bar, MoveUp, MarginTop, GameHeight, Panel);
//    }
    private void MovePongBar(PongBar bar, boolean moveUp){
        int futureBottomY = bar.GetFutureBottomY();
        int futureTopY = bar.GetFutureTopY();
        
        if (moveUp && futureTopY>MarginTop) {
            bar.MoveUp();
            return;
        }
        if (!moveUp && futureBottomY<GameHeight) {
            bar.MoveDown();
            return;
        }
        //System.out.println("You can't move anymore Down/Up.");
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        PressedKeys.add(e.getKeyCode());
        if (!PressedKeys.isEmpty()) {
            for (Iterator<Integer> it = PressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                        MovePongBar(LeftBar, true);
                        break;
                    case KeyEvent.VK_S:
                        MovePongBar(LeftBar, false);
                        break;
                    case KeyEvent.VK_UP:
                        MovePongBar(RightBar, true);
                        break;
                    case KeyEvent.VK_DOWN:
                        MovePongBar(RightBar, false);
                        break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PressedKeys.remove(e.getKeyCode());
    }
    
}

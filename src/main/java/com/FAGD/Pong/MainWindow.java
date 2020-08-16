/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FAGD.Pong;

import Objects.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author familia
 */
public class MainWindow extends javax.swing.JFrame {


    /**
     * Creates new form MainWindow
     */
    private final int ContainerWidth = 825;
    private final int ContainerHeight = (int)(ContainerWidth/1.7);
    private final int MarginLeft = 5;
    private final int MarginTop = 5;
    
    private final int GameWidth = ContainerWidth-MarginLeft*2;
    private final int GameHeight = ContainerHeight-MarginTop*2;
    
    private final JPanel Panel;
    
    private PongObject[] Objects;
    
    private PongBar LeftBar;
    private PongBar RightBar;
    private PongBall Ball;
    private PongWall UpWall;
    private PongWall DownWall;
    
    private final int PongBarStartTopX = (int)GameWidth/15;
    private final int PongBarStartTopY = (int)GameHeight/3;
    private final int PongBarHeight = (int)GameHeight/3;
    private final int PongBarStep = (int)GameHeight/25;
    private final double PongBarShorten = PongBarHeight/10;
    private final double PongBarMinHeight = PongBarHeight/5;
    
    private final int BallWidth = (int)GameWidth/35;
    private final double BallMinHeight = BallWidth/2;
    private final int BallMaxVelocity = (int)GameWidth/80;
    private final double BallAggregatedSpeed = 1;
    
    private final Color LeftBarColor = Color.RED;
    private final Color LeftBarBorderColor = Color.PINK;
    private final Color RightBarColor = Color.BLUE;
    private final Color RightBarBorderColor = Color.CYAN;
    private final Color BallColor = Color.MAGENTA;
    private final Color BallBorderColor = Color.YELLOW;
    
    private final int FrameRate = 80;
    private int FramesSinceStarted = 0;
    private Timer Timer;
    
    private final int MessageInitialTime = FrameRate*3;
    private int MessageCurrentTime = FrameRate*3;
    private String[] MessageCurrent = new String[] {""};
    private Color MessageCurrentColor = Color.WHITE;
    
    private final String[] MessageSpeed = {    
        "████████████████████████████████████████████████████████████████████████████",
        "█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░███",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀░░░░█",
        "█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░▄▀▄▀░░█",
        "█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█",
        "█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░██░░▄▀░░█",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█",
        "█░░░░░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░██░░▄▀░░█",
        "█████████░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█",
        "█░░░░░░░░░░▄▀░░█░░▄▀░░█████████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░▄▀▄▀░░█",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀░░░░█",
        "█░░░░░░░░░░░░░░█░░░░░░█████████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░███",
        "████████████████████████████████████████████████████████████████████████████"
    };
    private final Color MessageSpeedColor = Color.GREEN;
    
    private final String[] MessageShorter = {
        "████████████████████████████████████████████████████████████████████████████████",
        "█░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█",
        "█░░▄▀░░░░░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░░░░░▄▀░░░░░░█",
        "█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███████░░▄▀░░█████",
        "█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███████░░▄▀░░█████",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███████░░▄▀░░█████",
        "█░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███████░░▄▀░░█████",
        "█████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████████░░▄▀░░█████",
        "█░░░░░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█████░░▄▀░░█████",
        "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█████░░▄▀░░█████",
        "█░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█████░░░░░░█████",
        "████████████████████████████████████████████████████████████████████████████████"
    };
    private final Color MessageShorterColor = new Color(255,69,0);
    
    public MainWindow() {
        initComponents();
        
        SetFirstGameState();
        
        Panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                setBackground(Color.BLACK);
                DrawBackground(g);
                DrawGameState(g);
            }
        };
        Panel.setPreferredSize(new Dimension(ContainerWidth, ContainerHeight));
        Panel.setFocusable(true);
        Panel.addKeyListener(new MainWindowKeyListener(LeftBar, RightBar, MarginTop, GameHeight, Panel));
        
        btnRestart.setFocusable(false);
        btnQuit.setFocusable(false);
        jLabel2.setFocusable(false);
        
        add(Panel);
        setResizable(false);
        pack();
        StartGame();
    }
    
    public void StartGame(){
        Panel.requestFocus();
        Panel.requestFocusInWindow();
        Timer = new Timer(1000/FrameRate, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FramesSinceStarted++;
                DoEachFrame();
                Panel.repaint();
            }
        });
        Timer.setInitialDelay(0);
        Timer.start();
    }
    
    private void DoEachFrame(){
        //Here we move everything that the users are not controlling
        if (Ball.ReachedASide(0, ContainerWidth)) {
            Timer.stop();
            Ball.Move();
            JOptionPane.showMessageDialog(this, GetWinner() + " WON", "PONG", JOptionPane.OK_OPTION);
            return;
        }
        
        for (int i = 0; i < Objects.length; i++) {
            if (Ball.Collides(Objects[i].GetShadow(0), Objects[i].GetShadow(1))) {
                Ball.Bounce();
                break;
            }
        }
        
        Ball.Move();
    }
    
    private String GetWinner(){
        if (Ball.GetTopX() <= 0) {
            return "Player 1";
        }
        return "Player 2";
    }
    
    private void SetFirstGameState(){
        LeftBar = new PongBar(PongBarStartTopX, PongBarStartTopY, PongBarHeight, PongBarStep, PongBarMinHeight, LeftBarColor, LeftBarBorderColor).CreateCorners();
        RightBar = new PongBar(GameWidth-PongBarStartTopX, PongBarStartTopY, PongBarHeight, PongBarStep, PongBarMinHeight, RightBarColor, RightBarBorderColor).CreateCorners();
        
        UpWall = new PongWall(0, 0, ContainerWidth, MarginTop, MarginTop, Color.WHITE, Color.WHITE).CreateCorners();
        DownWall = new PongWall(0, GameHeight, ContainerWidth, MarginTop, MarginTop, Color.WHITE, Color.WHITE).CreateCorners();
        
        Ball = new PongBall((int)(GameWidth/2 - BallWidth/2), (int)(GameHeight/2 - BallWidth/2), BallWidth, BallMinHeight, BallMaxVelocity, BallColor, BallBorderColor).CreateCorners();
        
        Objects = new PongObject[] {LeftBar, RightBar, UpWall, DownWall};
    }
    
    public void DrawMessage(Graphics g){
        g.setColor(MessageCurrentColor);
        g.setFont(new Font("Monaco", Font.BOLD, 12));
        int x = (int)(GameWidth/5);
        int y = (int)(GameHeight/2-g.getFontMetrics().getHeight()*MessageCurrent.length/2);
        for (int i = 0; i < MessageCurrent.length; i++) {
            g.drawString(MessageCurrent[i], x, y+=g.getFontMetrics().getHeight());
        }
        g.setColor(Color.WHITE);
    }
    
    private void DrawBackground(Graphics g){
        g.setColor(Color.WHITE);
        DrawMargin(g);
        DrawCenterLine(g);
        DrawCenterCircle(g);
        if (FramesSinceStarted%(FrameRate*13) == 0) {
            Ball.AggregateSpeed(BallAggregatedSpeed);
            
            MessageCurrentTime = MessageInitialTime;
            MessageCurrent = MessageSpeed;
            MessageCurrentColor = MessageSpeedColor;
        }
        if (FramesSinceStarted % (FrameRate * 25) == 0) {
            LeftBar.Shorten(PongBarShorten);
            RightBar.Shorten(PongBarShorten);
            
            MessageCurrentTime = MessageInitialTime;
            MessageCurrent = MessageShorter;
            MessageCurrentColor = MessageShorterColor;
        }
        if (MessageCurrent.length > 1) {
            DrawMessage(g);
            MessageCurrentTime--;
        }
        if (MessageCurrentTime <= 0) {
            MessageCurrent = new String[] {""};
            MessageCurrentTime = MessageInitialTime;
        }
    }
    
    private void DrawGameState(Graphics g){
        Ball.Draw(g);
        LeftBar.Draw(g);
        RightBar.Draw(g);
    }
    
    private void DrawMargin(Graphics g){
        g.drawLine(0, MarginTop, ContainerWidth, MarginTop);
        g.drawLine(0, GameHeight, ContainerWidth, GameHeight);
    }
    private void DrawCenterLine(Graphics g){
        g.drawLine((int)GameWidth/2, MarginTop, (int)GameWidth/2, GameHeight);
    }
    private void DrawCenterCircle(Graphics g){
        int circleRadius = (int)(GameWidth/2.5);
        g.drawOval((int)(GameWidth/2 - circleRadius/2), (int)(GameHeight/2 - circleRadius/2), circleRadius, circleRadius);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        btnQuit = new javax.swing.JButton();
        btnRestart = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PONG");

        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnQuit.setBackground(new java.awt.Color(255, 0, 0));
        btnQuit.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        btnQuit.setForeground(new java.awt.Color(255, 255, 255));
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        jSplitPane1.setRightComponent(btnQuit);

        btnRestart.setBackground(new java.awt.Color(255, 0, 0));
        btnRestart.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        btnRestart.setForeground(new java.awt.Color(255, 255, 255));
        btnRestart.setText("Restart");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });
        jSplitPane1.setLeftComponent(btnRestart);

        jPanel2.add(jSplitPane1, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLabel2.setFont(new java.awt.Font(".SF NS Text", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Flavio André Galán Donis IV Bach D 12");
        getContentPane().add(jLabel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        Timer.stop();
        new MainWindow().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnRestartActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        dispatchEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));
        dispose();
    }//GEN-LAST:event_btnQuitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnRestart;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author familia
 */
public class PongBall extends PongObject {
    public PongBall(int x, int y, int width, double minHeight, int dx, int dy, int maxVelocity, Color c, Color bc){
        super(x, y, width, width, minHeight, c, bc);
        Dx = dx;
        Dy = dy;
        MaxVelocity = maxVelocity;
    }
    public PongBall(int x, int y, int width, double minHeight, int maxVelocity, Color c, Color bc){
        super(x, y, width, width, minHeight, c, bc);
        Dx = 1*(((int)Math.round(Math.random()) == 0)? 1: -1);
        Dy = 1*(((int)Math.round(Math.random()) == 0)? 1: -1);
        MaxVelocity = maxVelocity;
    }
    @Override
    public PongBall CreateCorners(){
        //The corners of the ball, are a diamond
        int x = GetTopX();
        int y = GetTopY();
        int bx = GetBottomX();
        int by = GetBottomY();
        
        int middleX = (int)(x+Width/2);
        int middleY = (int)(y+Height/2);
        
        Corners.add(new int[]{x, middleY});
        Corners.add(new int[]{middleX, y});
        Corners.add(new int[]{bx, middleY});
        Corners.add(new int[]{middleX, by});
        return this;
    }
    public void AggregateSpeed(double speed){
        double h = Math.sqrt(Math.pow(Dx, 2)+Math.pow(Dy, 2));
        if (Math.sqrt(Math.pow(speed*Dx/h, 2)+Math.pow(speed*Dy/h, 2))<= MaxVelocity) {
            Dx += speed*Dx/h/2;
            Dy += speed*Dy/h/2;
        }
        System.out.println("Current speed is:");
        System.out.println("Dx = "+Dx);
        System.out.println("Dy = "+Dy);
    }
    @Override
    public void Draw(Graphics g){
        g.setColor(Color);
        g.fillOval(GetTopX(), GetTopY(), GetWidth(), GetHeight());
        g.setColor(BorderColor);
        g.drawOval(GetTopX(), GetTopY(), GetWidth(), GetHeight());
        g.setColor(Color.WHITE);
    }
    public boolean ReachedASide(int minX, int maxX){
        int[] futureXCoordinates = GetFutureCoordinates(true);
        return futureXCoordinates[0] <= minX || futureXCoordinates[1] >= maxX;
    }
    public void Bounce(){
        //If it bounces up or down
        if ((Dx < 0 && Dy < 0) || (Dx > 0 && Dy > 0)) {
            Dy = -Dy;
            return;
        }
        Dx = -Dx;
    }
}

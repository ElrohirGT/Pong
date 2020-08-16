/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Color;

/**
 *
 * @author familia
 */
public class PongBar extends PongObject {
    
    public PongBar(int x, int y, int tall, int step, double minHeight, Color c, Color bc){
        super(x, y, 10, tall, minHeight, c, bc);
        Dy = step;
        Dx = 0;
    }
    @Override
    public PongBar CreateCorners(){
        //This creates the corners of a square that encapsulates the shape
        int x = GetTopX();
        int y = GetTopY();
        int bx = GetBottomX();
        int by = GetBottomY();
        
        Corners.add(new int[] {x, y});
        Corners.add(new int[] {bx, y});
        Corners.add(new int[] {x, by});
        Corners.add(new int[] {bx, by});
        return this;
    }
    public void Shorten(double value){
        if (Height > MinHeight) {
            Height -= value;
        }
        System.out.println("The bar lenght is now: "+Height);
    }
    public int GetFutureTopY(){
        return (int)(TopY+Dy);
    }
    public int GetFutureBottomY(){
        return (int)(GetBottomY()+Dy);
    }
    public void MoveUp(){
        Dy = -Math.abs(Dy);
        Move();
    }
    public void MoveDown(){
        Dy = Math.abs(Dy);
        Move();
    }
}

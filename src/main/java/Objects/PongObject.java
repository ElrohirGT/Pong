/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author familia
 */
public class PongObject {
    protected double Width, Height;
    protected double MinHeight;
    protected double TopX, TopY;
    protected double Dx, Dy;
    protected int MaxVelocity = 1;
    protected Color Color;
    protected Color BorderColor;
    
    public ArrayList<int[]> Corners = new ArrayList<>();
    
    public PongObject(double topX, double topY, double width, double height, double minHeight, Color color, Color borderColor){
        Width = width;
        Height = height;
        TopX = topX;
        TopY = topY;
        Color = color;
        BorderColor = borderColor;
    }
    
    public void Draw(Graphics g){
        g.setColor(Color);
        g.fillRect(GetTopX(), GetTopY(), GetWidth(), GetHeight());
        g.setColor(BorderColor);
        g.drawRect(GetTopX(), GetTopY(), GetWidth(), GetHeight());
        g.setColor(Color.WHITE);
    }
    
    public void DrawMessage(Graphics g, String[] message, Color c){
        
    }
    
    public boolean Collides(int[] xShadow, int[] yShadow){
        return InsideOrIntersects(GetShadow(0), xShadow) && InsideOrIntersects(GetShadow(1), yShadow);
    }
    
    protected boolean InsideOrIntersects(int[] ownShadow, int[] otherShadow){
        //If ownShadow is inside otherShadow
        if ((ownShadow[0] >= otherShadow[0] && ownShadow[0] <= otherShadow[1]) &&
          (ownShadow[1] >= otherShadow[0] && ownShadow[1] <= otherShadow[1])){
            return true;
        }
        //If the start of ownShadow intersects otherShadow
        if (ownShadow[0] >= otherShadow[0] && ownShadow[0] <= otherShadow[1]) {
            return true;
        }
        //If the end of ownShadow intersects otherShadow
        if (ownShadow[1] >= otherShadow[0] && ownShadow[1] <= otherShadow[1]) {
            return true;
        }
        return false;
    }
    //0 for x dimension, 1 for y dimension
    public int[] GetShadow(int dimension){
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        
        for (int i = 0; i < Corners.size(); i++) {
            int value = Corners.get(i)[dimension];
            min = Integer.min(min, value);
            max = Integer.max(max, value);
        }
        return new int[] {min, max};
    }
    
    public PongObject CreateCorners(){
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
    public void UpdateCorners(){
        Corners.clear();
        CreateCorners();
    }
    
    protected int[] GetFutureCoordinates(){
        double newTopX = TopX+Dx;
        double newTopY = TopY+Dy;
        
        return new int[] {(int)newTopX, (int)newTopY, GetBottomX(newTopX), GetBottomY(newTopY)};
    }
    protected int[] GetFutureCoordinates(boolean getX){
        if (getX) {
            double newTopX = TopX+Dx;
            return new int[] {(int)newTopX, GetBottomX(newTopX)};
        }
        double newTopY = TopY+Dy;
        return new int[] {(int)newTopY, GetBottomY(newTopY)};
    }
    
    public void Move(){
        TopX += Dx;
        TopY += Dy;
        UpdateCorners();
    }
    
    public int GetWidth(){
        return (int)Width;
    }
    public int GetHeight(){
        return (int)Height;
    }
    public int GetTopX(){
        return (int)TopX;
    }
    public int GetTopY(){
        return (int)TopY;
    }
    public int GetBottomX(){
        return (int)(TopX+Width);
    }
    public int GetBottomX(double x){
        return (int)(x+Width);
    }
    public int GetBottomY(){
        return (int)(TopY+Height);
    }
    public int GetBottomY(double y){
        return (int)(y+Height);
    }
}

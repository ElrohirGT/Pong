/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author familia
 */
public class PongWall extends PongObject {    
    public PongWall(double topX, double topY, double width, double height, double minHeight, java.awt.Color color, java.awt.Color borderColor) {
        super(topX, topY, width, height, minHeight, color, borderColor);
    }
    @Override
    public PongWall CreateCorners(){
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
}


/**
 * Write a description of class Trail here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class Trail extends GameObject
{
    // instance variables - replace the example below with your own
    private float alpha = 1;
    private float life;//life between 0.001 - 0.1
    private Handler handler;
    private Color color;
    private float sizeX, sizeY;
    
    
    public Trail(float x, float y, ID id, Color color, int sizeX, int sizeY,float life, Handler handler)
    {
        super(x,y,id);
        this.handler = handler;
        this.color = color;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.life = life;
    }
    
    public void tick()
    {
        if(alpha > life)
        {
            alpha -= life - .001;
        }
        else
        {
            handler.removeObject(this);
        }
    }
    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
        g2d.setComposite(makeTransparent(1));
    }
    
    private AlphaComposite makeTransparent(float alpha)
    {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }
    public Rectangle getBounds()
    {
        return null;
    }
}

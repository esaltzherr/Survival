
/**
 * Write a description of class BasicEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class Wall extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = Game.width;
    public int sizeY = Game.height;
    private Handler handler;
    public Wall(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;

    }
    public void tick()
    {     
    }
    public void render(Graphics g)
    {
    
        
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }
}

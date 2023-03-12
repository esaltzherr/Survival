
/**
 * Write a description of class BasicEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class BasicEnemy extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = 16;
    public int sizeY = 16;
    private Handler handler;
    public BasicEnemy(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        speed = 3;
        velX = 1;
        velY = 1;
        maxHealth = 25;
        sizeX = 16;
        sizeY = 16;
        score = 25;
        health = maxHealth;
        color = Color.red;
    }
    public void tick()
    {
        checkDeath(handler);
        x+= velX * speed;
        y+= velY * speed;
        
        if(x >= Game.width - 30|| x <= 0)
        {
            velX *= -1;
        }
        if(y >= Game.height - 55|| y <= 0)
        {
            velY *= -1;
        }
        //handler.addObject(new Trail(x, y, id,color, sizeX, sizeY, 0.05f, handler));
    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
        Healthbar.render(g, id, x, y, maxHealth, health, sizeX, color);
        
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }
}


/**
 * Write a description of class BasicEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
public class MenuParticle extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = 16;
    public int sizeY = 16;
    private Handler handler;
    
    private Random r = new Random();
    
    public MenuParticle(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        speed = r.nextInt(3) + 1;
        velX = r.nextInt(5) + 1;
        velY = r.nextInt(5) + 1;
        sizeX = r.nextInt(10) + 5;
        sizeY = r.nextInt(10) + 5;
        color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
    public void tick()
    {
        
        
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
        handler.addObject(new Trail(x, y, ID.Trail, color, sizeX, sizeY, 0.1f, handler));
    }
    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
        //Healthbar.render(g, id, x, y, maxHealth, health, sizeX, color);
        
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }
}

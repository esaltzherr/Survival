
/**
 * Write a description of class BasicEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class WeirdEnemy extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = 16;
    public int sizeY = 16;
    private Handler handler;
    private GameObject player;
    public WeirdEnemy(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            if(handler.object.get(i).getID() == ID.Player)
            {
                player = handler.object.get(i);
            }
        }
        
        
        
        
        speed = 1;
        velX = 1;
        velY = 1;
        maxHealth = 50;
        sizeX = 32;
        sizeY = 32;
        score = 100;
        health = maxHealth;
        color = Color.magenta;
    }
    public void tick()
    {
        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (int)Math.sqrt((diffX*diffX) + (diffY * diffY));
        checkDeath(handler);
        if(diffX != 0)
        {
            velX = -distance / diffX;
            
        }
        if(diffY != 0)
        {
            
            velY = -distance / diffY;
        }
        x += velX * speed;
        y += velY * speed;
        Game.clamp(x, 0,Game.width);
        Game.clamp(y , 0, Game.height);
        
        handler.addObject(new Trail(x, y, ID.Trail, color, sizeX, sizeY, 0.1f, handler));
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

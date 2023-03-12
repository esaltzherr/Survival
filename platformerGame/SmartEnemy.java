
/**
 * Write a description of class BasicEnemy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class SmartEnemy extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = 16;
    public int sizeY = 16;
    private Handler handler;
    private GameObject player;
    public SmartEnemy(float x, float y, ID id, Handler handler)
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
        
        
        
        
        speed = 1.5f;
        velX = 1;
        velY = 1;
        maxHealth = 100;
        score = 100;
        sizeX = 32;
        sizeY = 32;
        health = maxHealth;
        color = Color.green;
    }
    public void tick()
    {
        checkDeath(handler);
        
        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float)Math.sqrt((diffX*diffX) + (diffY * diffY));
        
       
        
        if (distance !=0)
        {
             velX = -(diffX/distance) * speed; 
             velY = -(diffY/distance) * speed; 
        }
       
    
        //System.out.println("velX: " + velX + "  velY: " + velY+ "  diffX: " + diffX+ "  diffY: " + diffY + "  Dis: " + distance );
        x += velX * speed;
        y += velY * speed;
         
        
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

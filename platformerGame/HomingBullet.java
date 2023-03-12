
/**
 * Write a description of class BUllet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class HomingBullet extends GameObject
{
    // instance variables - replace the example below with your own

    private Handler handler;
    private GameObject player;
    private int timer;
    public HomingBullet(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        speed = 2;
        sizeX = 10;
        sizeY = 10;
        health = 1;
        color = Color.magenta;
        timer = 200;
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            if(handler.object.get(i).getID() == ID.Player)
            {
                player = handler.object.get(i);
            }
        }
    }   
    public void tick()
    {
        timer--;
        if(timer <= 0)
        {
            health -=1;
        }
        
        checkDeath(handler);
        x+= velX * speed;
        y+= velY * speed;

        collision();
        float diffX = x - player.getX();
        float diffY = y - player.getY();
        float distance = (float)Math.sqrt((diffX*diffX) + (diffY * diffY));
        if (distance !=0)
        {
             velX = -(diffX/distance) * speed; 
             velY = -(diffY/distance) * speed; 
        }
       
        //handler.addObject(new Trail(x, y, id,color, sizeX, sizeY, 0.05f, handler));
    }

    public void render(Graphics g)
    {
        g.setColor(color);
        g.fillRect((int)x, (int)y, (int)sizeX, (int)sizeY);
        //Healthbar.render(g, id, x, y, maxHealth, health, sizeX, color);

    }

    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getID() == ID.Player)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    //collision code

                    handler.removeObject(this);
                    tempObject.changeHealth(tempObject.getHealth()-5);
                }
            }
            if(tempObject.getID() == ID.Wall)
            {
                if(!getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(this);
                    //System.out.println("removed");
                }
            }
        }

    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,(int)sizeX,(int)sizeY);
    }
}

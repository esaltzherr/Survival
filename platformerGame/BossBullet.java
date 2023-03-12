
/**
 * Write a description of class BUllet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
public class BossBullet extends GameObject
{
    // instance variables - replace the example below with your own
    Random r = new Random();
    private Handler handler;
    public BossBullet(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        speed = 2;
        
        
        velX = -5;
        velY = r.nextInt(10) - 5;
        //maxHealth = 1;
        sizeX = 7;
        sizeY = 7;
        //health = maxHealth;
        color = Color.gray;
    }

    public void tick()
    {
        x+= velX * speed;
        y+= velY * speed;

        collision();

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

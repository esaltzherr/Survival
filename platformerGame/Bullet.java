
/**
 * Write a description of class BUllet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
public class Bullet extends GameObject
{
    // instance variables - replace the example below with your own

    private Handler handler;
    public Bullet(float x, float y, ID id, Handler handler, float velX, float velY)
    {
        super(x, y, id);
        this.handler = handler;
        speed = 3;
        this.velX = velX;
        this.velY = velY;
        //maxHealth = 1;
        sizeX = 5;
        sizeY = 5;
        //health = maxHealth;
        color = Color.magenta;
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

            if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy 
            || tempObject.getID() == ID.BossEnemy || tempObject.getID() == ID.SmartEnemy
            || tempObject.getID() == ID.WeirdEnemy || tempObject.getID() == ID.HomingBullet)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    //collision code

                    handler.removeObject(this);
                    tempObject.changeHealth( tempObject.getHealth()-2);
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

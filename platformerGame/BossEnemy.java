
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
public class BossEnemy extends GameObject
{
    // instance variables - replace the example below with your own
    private int sizeX = 16;
    public int sizeY = 16;
    private Handler handler;
    private Random r = new Random();

    private int[] phaseTimers = {70,100,300,300};
    private int[] phaseHealths = {0 ,150, 100, 0};
    private int phase = 0;
    private Boolean changePhase = false;
    private Boolean timerChangePhase = false;
    public BossEnemy(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
        speed = 4;
        velX = -1;
        velY = 0;
        maxHealth = 200;
        sizeX = 64;
        sizeY = 64;
        score = 500;
        health = maxHealth;
        color = Color.cyan;
    }

    public void tick()
    {
        checkDeath(handler);
        
        if(phase ==0)
        {
            changePhase = phaseIncrementer();
        }
        else
        {
            changePhase = PhaseByHealth();
        }
        //System.out.println(phase);
        x+= velX * speed;
        y+= velY * speed;
        if(phase > 0)
        {
            if(x >= Game.width - 30|| x <= 0)
            {
                velX *= -1;
            }
            if(y >= Game.height - 55|| y <= 0)
            {
                velY *= -1;
            }
        }
        if(changePhase)
        {
            if(phase == 0)
            {
                velX = -1;
            }
            if(phase == 1)
            {
                velY = 1;
                velX = 0;
            }
            if(phase == 2)
            {
                speed = 6;
            }
            
        }
        
        if(phase == 3)
        {
            int shoot = r.nextInt(40);
            if(shoot == 0)
            {
                handler.addObject(new HomingBullet(getX()+(sizeX/2),getY()+(sizeY/2),ID.HomingBullet, handler));
            }
            int shoot2 = r.nextInt(15);
            if(shoot2 == 0)
            {
                handler.addObject(new BossBullet(getX()+(sizeX/2),getY()+(sizeY/2),ID.BossBullet, handler));
            }
        }
        if(phase == 2)
        {
            int shoot = r.nextInt(50);
            if(shoot == 0)
            {
                handler.addObject(new HomingBullet(getX()+(sizeX/2),getY()+(sizeY/2),ID.HomingBullet, handler));
            }
        }

        if(phase == 1)
        {
            int shoot = r.nextInt(10);
            if(shoot == 0)
            {
                handler.addObject(new BossBullet(getX()+(sizeX/2),getY()+(sizeY/2),ID.BossBullet, handler));
            }
        }

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

    public Boolean phaseIncrementer()
    {
        if(phaseTimers[phase] <= 0)
        {
            if(phase + 1 < phaseTimers.length)
            {
                phase++;
                return true;
            }   
        }
        else
        {
            phaseTimers[phase]--;    
        }
        return false;
    }
    
    public Boolean PhaseByHealth()
    {
        if(getHealth() < phaseHealths[phase])
        {
            phase++;
            return true;
        }
        return false;
    }
}

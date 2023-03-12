
/**
 * Write a description of class GameObject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
public abstract class GameObject
{
    // instance variables - replace the example below with your own
    protected float x, y;
    protected int sizeX, sizeY;
    protected ID id;
    protected float velX, velY, speed, lastVelX, lastVelY;
    protected boolean right, left, up, down;
    protected int maxHealth, health;
    protected Color color;
    protected Handler handler;
    protected boolean shooting;
    protected int score;
    public GameObject(float x, float y, ID id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setID(ID id)
    {
        this.id = id;
    }

    public ID getID()
    {
        return id;
    }

    public Handler getHandler()
    {
        return handler;
    }

    public void setVelX(float velX)
    {
        this.velX = velX;
    }

    public void setVelY(float velY)
    {
        this.velY = velY;
    }

    public float getVelX()
    {
        return velX;
    }

    public float getVelY()
    {
        return velY;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void changeSpeed(float newSpeed)
    {
        speed = newSpeed;
    }

    public int getHealth()
    {
        return health;
    }

    public void changeHealth(int newHealth)
    {
        if(newHealth <= 0)
        {
            health = 0;
        }
        if(newHealth >= this.maxHealth)
        {
            health = this.maxHealth;
        }
        else
        {
            health = newHealth;
        }
    }
    
    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    public void changeMaxHealth(int newMaxHealth)
    {
        this.maxHealth = newMaxHealth;
    }
    
    public void movingRight(boolean right)
    {
        this.right = right;
    }

    public void movingLeft(boolean left)
    {
        this.left = left;
    }

    public void movingUp(boolean up)
    {
        this.up = up;
    }

    public void movingDown(boolean left)
    {
        this.down = left;
    }

    public boolean getRight()
    {
        return right;
    }

    public boolean getLeft()
    {
        return left;
    }

    public boolean getUp()
    {
        return up;
    }

    public boolean getDown()
    {
        return down;
    }

    public boolean isShooting()
    {
        return shooting;
    }
    
    public void changeShooting(boolean shooting)
    {
        this.shooting = shooting;
    }
    
    public void setLastVelX(float lastVelX)
    {
        this.lastVelX = lastVelX;
    }
    public void setLastVelY(float lastVelY)
    {
        this.lastVelY = lastVelY;
    }
    public float getLastVelX()
    {
        return lastVelX;
    }
    public float getLastVelY()
    {
        return lastVelY;
    }
    public int getScore()
    {
        return score;
    }
    public void checkDeath(Handler handler)
    {
        if(getHealth() <= 0)
        {
            handler.removeObject(this);
            HUD.changeScore(HUD.getScore() + this.getScore());
        }
    }
}

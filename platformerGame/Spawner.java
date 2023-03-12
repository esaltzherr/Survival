
/**
 * Write a description of class Spawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
public class Spawner
{
    // instance variables - replace the example below with your own
    private Handler handler;
    private HUD hud;

    private Random r = new Random();
    private int scoreKeep = 0;
    private Boolean spawn = false;
    public Spawner(Handler handler, HUD hud)
    {
        this.handler = handler;
        this.hud = hud;

    }

    public void tick()
    {
        scoreKeep++;
        if(!handler.checkEnemys())
        {
            hud.setLevel(hud.getLevel() + 1);
            spawn = true;
        }
        if(spawn)
        {
            if (hud.getLevel() ==2)
            {
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
            }
            if (hud.getLevel() ==3)
            {
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
                //handler.addObject(new WeirdEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.WeirdEnemy, handler));
            }
            if (hud.getLevel() ==4)
            {
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
            }
            if (hud.getLevel() ==5)
            {
                handler.addObject(new BossEnemy(Game.width + 150, Game.height/2, ID.BossEnemy, handler));
            }
            if(hud.getLevel() > 5)
            {
                handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
                handler.addObject(new BossEnemy(Game.width + 150, Game.height/2, ID.BossEnemy, handler));
                handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
                handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));

            }
            if(hud.getLevel() > 6)
            {
                //handler.addObject(new WeirdEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.WeirdEnemy, handler));
            }
            spawn = false;
        }

        /*
        if(scoreKeep >= 300 && hud.getLevel() < 5)
        {
        scoreKeep = 0;
        hud.setLevel(hud.getLevel() + 1);

        if (hud.getLevel() ==2)
        {
        handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
        handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
        }
        if (hud.getLevel() ==3)
        {
        handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
        handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
        //handler.addObject(new WeirdEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.WeirdEnemy, handler));
        }
        if (hud.getLevel() ==4)
        {
        handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
        handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.FastEnemy, handler));
        }
        if (hud.getLevel() ==5)
        {
        handler.addObject(new BossEnemy(Game.width + 150, Game.height/2, ID.BossEnemy, handler));
        }
        //if (hud.getLevel() == 2)
        //{
        //handler.addObject(new FastEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));
        //handler.addObject(new SmartEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.SmartEnemy, handler));
        //handler.addObject(new WeirdEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.WeirdEnemy, handler));
        //    handler.addObject(new BossEnemy(Game.width - 150, Game.height/2, ID.BossEnemy, handler));
        //}

        }
         */
    }
}

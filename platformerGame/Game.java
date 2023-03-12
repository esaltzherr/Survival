
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    // instance variables - replace the example below with your own
    public static final int width = 640, height = width/12*9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Healthbar healthbar;
    private Random r;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    private Leaderboard leaderboard;
    private Shop shop;

    public State gameState = State.Menu;

    public Game()
    {

        handler = new Handler();
        hud = new HUD();
        shop = new Shop(handler, hud);
        leaderboard = new Leaderboard(this, handler, hud);
        menu = new Menu(this, handler, hud, leaderboard, shop);

        healthbar = new Healthbar();

        spawner = new Spawner(handler, hud);
        this.addKeyListener(new KeyInput(handler, this, leaderboard));
        this.addMouseListener(menu);
        this.addMouseListener(shop);

        new Window(width,height, "Game Name", this);
        r = new Random();
        if (gameState == State.Game)
        {
            //handler.addObject(new Player(width/2,height/2,ID.Player, handler));
            //handler.addObject(new Wall(0,0,ID.Wall, handler));
            //handler.addObject(new BasicEnemy(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.BasicEnemy, handler));

        }
        else
        {
            for(int i = 0; i < 10; i++)
            {
                handler.addObject(new MenuParticle(r.nextInt(Game.width - 50), r.nextInt(Game.height - 50), ID.MenuParticle, handler));
            }
        }

    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        this.requestFocus();
        long lastTime  =System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {
                tick();
                delta--;

            }
            if(running)
            {
                render();
            }
            frames++;
            if (System.currentTimeMillis()- timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;

            }
        }
        stop();
    }

    private void tick()
    {

        if(gameState== State.Game)
        {
            healthbar.tick();
            hud.tick();
            spawner.tick();
            handler.tick();
        }
        else if(gameState == State.Menu || gameState == State.End || gameState == State.Help|| gameState == State.Leaderboard)
        {
            menu.tick();
            handler.tick();
        }

    }
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);

        if(gameState== State.Game)
        {
            hud.render(g);
            handler.render(g);
        }
        else if(gameState == State.Menu || gameState == State.Help || gameState == State.End || gameState == State.Leaderboard)
        {
            menu.render(g);
            handler.render(g);
        }
        else if(gameState == State.Shop)
        {
            shop.render(g);
        }
        g.dispose();
        bs.show();

    }

    public static void main(String args[])
    {
        new Game();
    }

    public static float clamp(float var, float min, float max)
    {
        if(var >= max)
        {
            return var = max;
        }
        else if(var <= min)
        {
            return var = min;
        }
        else
        {
            return var;
        }
    }

}


/**
 * Write a description of class leaderboard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
public class Leaderboard
{
    // instance variables - replace the example below with your own
    ArrayList<String> leadName = new ArrayList<String>(Arrays.asList("Elroy", "Elroy","Joyce", "Alex", "Rene", "Karin"));
    ArrayList<Integer> leadScore = new ArrayList<Integer>(Arrays.asList(26870, 2710, 800, 1100, 300, 300));

    private Game game;
    private Handler handler; 
    private HUD hud;
    
    String potName = "";
    public Leaderboard(Game game, Handler handler, HUD hud)
    {
        Sort();
        
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {

    }
    public int getLength()
    {
        return leadScore.size();
    }
    public void addScore(String str, int num)
    {
        this.leadName.add(str);
        this.leadScore.add(num);
        Sort();        
    }

    public int getScore(int index)
    {
        return leadScore.get(index);
    }

    public String getName(int index)
    {
        return leadName.get(index);
    }

    public void Sort()
    {
        for(int i = leadScore.size()- 1; i > 0; i--)
        {
            if(leadScore.get(i) > leadScore.get(i-1))
            {
                Swap(i, i-1);
            }
        }
        if(leadScore.size()>10)
        {
            leadScore.remove(leadScore.size()-1);
            leadScore.remove(leadName.size()-1);
        }
    }

    public void Swap(int second, int first)
    {
        leadName.add(second , leadName.remove(first));
        leadScore.add(second , leadScore.remove(first));
    }
    
    public void potName(String key)
    {
        
        potName += key;
    }
    public void remPotName()
    {
        potName = potName.substring(0,potName.length()-1);
    }
    public String getPotName()
    {
        return potName;
    }
    
}

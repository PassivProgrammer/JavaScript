import java.awt.Color;
import java.awt.Graphics;

public class Hud {
	
	public int width;
	public boolean paused = true;
	
	public Hud(int pWidth)
	{
		width = pWidth;
	}
	
	
	public void tick()
	{
		
	}
	public void render(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRoundRect(width-150, 5, 100, 30, 0, 0);
		
		if(!paused) {
			playButton(g,width-150+40, 10);
		}else
		{
			stopButton(g,width-150+40, 10);
		}
	}
	
	public String checkClicked(int[] maus)
	{
		if(maus[0]>(width-150) && maus[0]<width-50 ) //x position
		{
			if( maus[1]>5 && maus[1]<35) //y position
			{
				return "pause";
				}
			}
		
		return null;
	}
	
	public String clicked(int[] maus)
	{
		if(maus[0]>(width-150) && maus[0]<width-50 ) //x position
		{
			if( maus[1]>5 && maus[1]<35) //y position
			{
					if(paused) {
						paused = false;
					}else {
						paused = true;
					}

				return "pause";
				}
			}
		
		return null;
	}
	
	
	public void playButton(Graphics g,int x,int y)
	{
		int[] xPoints = {x,x+20,x}, yPoints = {y,y+10,y+20};
		g.setColor(Color.yellow);
		g.fillPolygon(xPoints, yPoints, 3);
	}
	
	public void stopButton(Graphics g,int x,int y)
	{
		g.setColor(Color.yellow);
		g.fillRect(x, y, 8, 20);
		g.fillRect(x+12, y, 8, 20);
	}
	

}

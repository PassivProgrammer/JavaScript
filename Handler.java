import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> list0 = new LinkedList<GameObject>();
	LinkedList<Tile> list = new LinkedList<Tile>();
	
	public boolean timeElapses = true;
	
	public int mouseX,mouseY;
	private int heigth,width;

	LinkedList<City> cities = new LinkedList<City>();
	
	public Welt welt = new Welt(100,1.5,this);//default 120,1.5
	
	public Hud hud;
	
	public Handler(Hud pHud,int pHeight,int pWidth) {
		hud = pHud;
		heigth = pHeight;
		width = pWidth;
	}

	
	public void tick()
	{
		mouseX = (int) ((MouseInfo.getPointerInfo().getLocation().getX())-((1920-width)/2));// obere linke Ecke vom Fenster
		mouseY = (int) ((MouseInfo.getPointerInfo().getLocation().getY())-((1080-heigth)/2));
		//System.out.println(mouseX +(" ")+ mouseY);
		
		welt.tile[maus()[1]][maus()[0]].mouse = true;
		
		for(int i = 0; i<cities.size(); i++)
		{
			City tempObject = cities.get(i);
			
			tempObject.tick(timeElapses);
		}
	}
	
	public void render(Graphics g)
	{


			for(int i = 0; i<list.size(); i++)
			{
				Tile tempObject = list.get(i);
				
				tempObject.render(g,timeElapses);
			}


		

	}
	
	public void time()
	{
        long startTime = System.currentTimeMillis();

        long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    
	}
	
	
	public int[] maus()
	{
		int mouseX = (int) ((MouseInfo.getPointerInfo().getLocation().getX())-340);
		int mouseY = (int) ((MouseInfo.getPointerInfo().getLocation().getY())-120);
		int [] i = {(int) clamp(0,welt.sizeX-2,mouseX/7),(int) clamp(0,welt.sizeY-2,mouseY/7)};
		return i;
		
	}
	
	public void pause()
	{
		if(timeElapses)
		{
			timeElapses = false;
		}else 
		{
			timeElapses = true;
		}
		System.out.println("pause");
	}
	
	public double clamp(double min,double max,double val)
	{
		double ret = 0.0;
		if(val>max) {ret = max;}
		else if(val<min){ret = min;}
		else {ret= val;}
		return ret;
	}
	
	
	
	
}
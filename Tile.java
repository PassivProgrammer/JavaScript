import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Tile extends GameObject {
	
	public int x,y;
	public int relX,relY;
	public boolean mouse=false;
	public int population = 0;
	public Welt welt;
	public Faction faction;
	public int hoehe;
	public City city;
	public boolean trees;
	public boolean wet,wood;
	public int wealth;
	public int belive;
	public int power;
	public int food;
	
	public int counter;
	
	
	private boolean once1 = false;
	
	
	//Farben
	Color lightBrown = new Color(222,184,135);
	Color midBrown = new Color(205,170,125 );
	Color darkBrown = new Color(139,69,19);
	Color darkGreen = new Color(0,102,0);
	Color lightGreen = new Color(51,204,0);
	Color midGreen = new Color(51,153,51);
	Color midGray = new Color(128,128,128);
	Color lightGray = new Color(169,169,169);
	//für Häuser
	int offset = (int)(Math.random()*3);
	
	Image image = Toolkit.getDefaultToolkit().getImage("G:\\Adrian\\eclipse");//???
	
	
	public Tile(int pY,int pX,int pHoehe,ID pid,City pCity, Welt pwelt)
	{
		super(pid);
		x= pX;
		y= pY;
		hoehe = pHoehe;
		welt=pwelt;
		food = 300;
		counter = 0;
		relX = (x*7)+50;
		relY = (y*7)+50;
		
	}


	public void render(Graphics g,boolean time) {
        long startTime = System.currentTimeMillis();
		tileColor(g);
		g.fillRect(relX, relY , 7, 7);
		
		mouse = false;
		
		if(population>40){drawBigHut(g,relX,relY);}
		else if(population>1) {drawHut(g,relX,relY);}
		else {drawTree(g,relX,relY);}
		
		if(time)wachstum(1);

	}



	public ID getID() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Tile neighbour(String wo)//gibt tile zurück (oben,unten,rechts,links)
	{

		if(x<178 && wo.equals("rechts"))
		{	
			return(welt.tile[y][x+1]); 
			
		}
		
		else if(x>0 && wo.equals("links"))
		{
			return(welt.tile[y][x-1]);
		}
		else if(y<118 && wo.equals("unten"))
		{	
			return(welt.tile[y+1][x]);
		}
		
		else if(y>0 && wo.equals("oben"))
		{
			return(welt.tile[y-1][x]);
		}
		
		
		return null;
	}
	
	public void tileColor(Graphics g)
	{
		if(mouse)
		{
		g.setColor(Color.red);
		}else
		{
			Color color;
			if(hoehe>-50)
				{	color = new Color((int)clamp(0,255,hoehe+125),255-(int)clamp(0,255,hoehe+125),0);
					//color = new Color(0,139,0);
				
				}else {
					color = new Color((int)clamp(0,255,hoehe+125),255-(int)clamp(0,255,hoehe+125),0);
					//color = new Color(30,144,255);
				}
			g.setColor(color);
		}
	}
	
	public void increasePop(int many)
	{
		
		if(population<(255-many))population+= many;
	}
	
	public void drawHut(Graphics g,int x,int y) //obere rechte Ecke
	{

		
		//body
		g.setColor(lightBrown);
		g.fillRect(x+offset, y+offset, 4, 4);
		//door
		g.setColor(darkBrown);
		g.fillRect(x+offset+1, y+offset+2, 1, 2);
		//roof
		g.setColor(darkBrown);
		g.fillRect(x+offset, y+offset-1, 4, 1);
		g.fillRect(x+offset+1, y+offset-2, 2, 1);
	}
	
	public void drawTree(Graphics g,int x,int y) //obere rechte Ecke
	{
		
		//crown
		g.setColor(midGreen);
		g.fillRect(x+offset+1, y+offset, 3, 5);
		g.fillRect(x+offset, y+offset+1, 5, 3);

		//body
		g.setColor(darkBrown);
		g.fillRect(x+offset+2, y+offset+5, 1, 3);
	}
	
	public void drawBigHut(Graphics g,int x,int y) //obere rechte Ecke
	{
		
		//body
		g.setColor(lightGray);
		g.fillRect(x+offset, y+offset, 5, 5);

		//roof
		g.setColor(darkBrown);
		g.fillRect(x+offset, y+offset-1, 5, 1);
		g.fillRect(x+offset+1, y+offset-2, 3, 1);
		g.fillRect(x+offset+2, y+offset-3, 1, 1);
		
		//door
		g.setColor(darkBrown);
		g.fillRect(x+offset+2, y+offset+3, 2, 2);
		
		//widows
		g.fillRect(x+offset+1, y+offset+1, 1, 1);
		g.fillRect(x+offset+3, y+offset+1, 1, 1);
		
	}
	
	public double clamp(double min,double max,double val)
	{
		double ret = 0.0;
		if(val>max) {ret = max;}
		else if(val<min){ret = min;}
		else {ret= val;}
		return ret;
	}
	
	public void wachstum(int amount)
	{
		if(population>0)
		{
			if(city.food>500 && once1)//Ab weniger als 500 food wird auf einem zufälligen anliegenden Feld die Pop. auf 2 gesetzt //wird erst beim 2. Aufruf ausgeführt
			{
				increasePop(amount);
				food -= population;
			}
			else if(counter < 4)
			{
				int rand = (int)(Math.random()*4);
				if(neighbour("rechts") != null && neighbour("links") != null && neighbour("oben") != null && neighbour("unten") != null)
				{
				if(rand==0)neighbour("rechts").anschluss(this.city,2); else	if(rand==1)neighbour("links").anschluss(this.city,2); else	if(rand==2)neighbour("oben").anschluss(this.city,2); else	if(rand==3)neighbour("unten").anschluss(this.city,2);
				
				}
				counter++;
			}
			food += 10 - clamp(0,10,population); // food regeneriert nur mit Pop. unter 10
			once1 = true;
		}
	}
	
	public void founding() //Pop. wird auf 2 gesetzt und es wird eine neue Stadt erschaffen
	{
		city = new City("Eins");
		welt.newCity(city);
		increasePop(2);
		city.tiles.add(this);
	}
	
	public void anschluss(City pCity,int pPopulation) //gerufen wenn ein anderes Tile dieses an die eigene Stadt anschließt
	{
		if(this.city == null)
		{
		this.city = pCity;
		this.city.tiles.add(this);
		this.population += pPopulation;
		once1=false;
		}
	}


	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}

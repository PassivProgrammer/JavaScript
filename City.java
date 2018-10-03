import java.util.LinkedList;

public class City {
	
	public LinkedList<Tile> tiles = new LinkedList<Tile>();
	public String name;
	
	public int food = 10,population,wealth,power;
	
	public City(String pName)
	{
		name = pName;
	}
	
	public void tick(boolean timeElapses)
	{
		if(timeElapses) {
			food = 0;
			population = 0;
			for(int i = 0; i<tiles.size(); i++)
			{
				Tile tempObject = tiles.get(i);
				
				food += tempObject.food;
				population += tempObject.population;
			}
			//System.out.println(population +(" ")+ food);
		}
	}
}

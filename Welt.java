
public class Welt {
	
	public Tile[][] tile;
	public int[][] hoehen;
	private NoiseGenerator noiseGen = new NoiseGenerator();
	public int cities = 0,factions = 0;
	public Handler handler;
	public int sizeX,sizeY;
	
	public Welt(int pSize,double pRatio,Handler pHandler) {
		handler = pHandler;
		sizeY = pSize;
		sizeX = (int)(pSize*pRatio);
		tile = new Tile[pSize][(int)(pSize*pRatio)];
		hoehen = noiseGen.noise((int) (pSize*pRatio), pSize, 20);
		for(int i=0; i<(pSize-1);i++)
		{
			for(int j=0;j<( (int)(pSize*pRatio) -1 );j++)
			{
				tile[i][j]=new Tile(i,j,0,ID.Thing,null,this);//für 0 "heohen[i][j]" für hoehen
					handler.list.add(tile[i][j]);


			}
		}
	}
	
	public void newCity(City city)
	{
		cities+=1;
		handler.cities.add(city);
	}
}

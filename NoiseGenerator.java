


	public class NoiseGenerator {


		
		public int[][] noise(int width, int height,int tolerance)
		{
			int[][] noise = new int[height][width];
			int[][] antiNoise = new int[height][width];
			
			for(int i = 0;i < height; i++)
			{
				for(int j = 0;j < width;j++)
				{
					if(i == 0 && j == 0) noise[i][j] = (int)((Math.random()*tolerance))-((int)(tolerance/2+1)-1);
					
					else if(j==0) noise[i][j] = noise[i-1][j]+((int)((Math.random()*tolerance))-((int)(tolerance/2+1)-1));
					
					else if(i==0) noise[i][j] = noise[i][j-1]+((int)((Math.random()*tolerance))-((int)(tolerance/2+1)-1));//fuellt die erste Reihe mit höhen die max (toleranz/2)+1 abweichen
					
					else if(!(i == 0 && j == 0)) {
						if(j>0) noise[i][j] = ((noise[i-1][j]+noise[i][j-1])/2)+((int)((Math.random()*tolerance))-((int)(tolerance/2+1)-1));
					}
				}
			}
			
			antiNoise = fillAntiArray(noise,width,height);

			noise = addArray(antiNoise,noise,width,height);
			
			return noise;
		}
		
		public int[][] fillAntiArray(int[][] array,int width,int height) //spiegelt einen Array an "x=y"
		{
			int[][] antiNoise = new int[height][width];
			for(int i = 0;i < height; i++)
			{
				for(int j = 0;j < width;j++)
				{
					antiNoise[i][j] = array[(height-i)-1][(width-j)-1];
				}
			}
			return antiNoise;
		}
		
		public int[][] addArray(int[][] array1,int[][] array2,int width,int height)//addiert jede Stelle der Arrays miteinander
		{
			int[][] addedArray = new int[height][width];
			for(int i = 0;i < height; i++)
			{
				for(int j = 0;j < width;j++)
				{
					addedArray[i][j] = array1[i][j]+array2[i][j];
				}
			}
			return addedArray;
		}
		
		
	}


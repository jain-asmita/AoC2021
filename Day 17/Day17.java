public class Day17
{
    public static void main(String[] args)
	{
	    int xmin = 241;
		int xmax = 275;
		int ymin = -75;
		int ymax = -49;
		
		int maxDistance = 0;
		
		for(int xVelocity = -1000; xVelocity < 1000; xVelocity++)
		{
		    for(int yVelocity = -1000; yVelocity < 1000; yVelocity++)
			{
			    maxDistance += findMaxDistance(xmin, xmax, ymin, ymax, xVelocity, yVelocity, maxDistance);
				StdOut.println(xVelocity + " " + yVelocity);
			}
		}
		
		StdOut.println(maxDistance);
	}
	
	public static int findMaxDistance(int xmin, int xmax, int ymin, int ymax, int xVelocity, int yVelocity, int maxDistance)
	{
	    int xpos = 0;
		int ypos = 0;
		
		int maxYPos = 0;
		
		boolean isTargetAreaVisited = false;
		
		while(true)
		{
			xpos += xVelocity;
			ypos += yVelocity;
			
			if(maxYPos < ypos)
			{
				maxYPos = ypos;
			}
			
			if(xVelocity > 0)
			{
				xVelocity--;
			}
			else if(xVelocity < 0)
			{
			    xVelocity++;	
			}
			
			yVelocity--;
			
			// StdOut.println("pos " + xpos + " " + ypos);
			// StdOut.println("vel " + xVelocity + " " + yVelocity);
			
			if(xpos <= xmax && xpos >= xmin && ypos <= ymax && ypos >= ymin)
			{
				isTargetAreaVisited = true;
				break;
			}
			else if(xVelocity == 0 && (xpos < xmin || xpos > xmax))
			{
				break;
			}
			else if(xVelocity == 0 && ypos < ymax)
			{
				break;
			}
		}
		
		
		
		return isTargetAreaVisited ? 1 : 0;
	}
	
}
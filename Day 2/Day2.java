public class Day2
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byline = allInput.split("\r\n");
		int nSteps = input_byline.length;
		String[] direction = new String[nSteps];
        int[] magnitude = new int[nSteps];

        for(int i = 0; i < nSteps; ++i)
		{
			direction[i] = input_byline[i].split(" ")[0];
			magnitude[i] = Integer.parseInt(input_byline[i].split(" ")[1]);
		}
		
		long xPosition = 0;
		long depth = 0;
		long aim = 0;
		
		for(int i = 0; i < nSteps; ++i)
		{
			if (direction[i].equals("forward"))
			{
				xPosition += magnitude[i];
			}
			else if (direction[i].equals("up"))
			{
				depth -= magnitude[i];
			}
			else
			{
				depth += magnitude[i];
			}
		}
		
		StdOut.println(depth * xPosition);
		
		xPosition = 0;
		depth = 0;
		aim = 0;
		
		for(int i = 0; i < nSteps; ++i)
		{
			if (direction[i].equals("forward"))
			{
				xPosition += magnitude[i];
				depth += aim * magnitude[i];
			}
			else if (direction[i].equals("up"))
			{
				aim -= magnitude[i];
			}
			else
			{
				aim += magnitude[i];
			}
		}
		
		StdOut.println(depth * xPosition);	
	}
}
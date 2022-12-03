public class Day3
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byline = allInput.split("\r\n");
		int xLength = input_byline[0].length();
		int yLength = input_byline.length;
		int[][] input = new int[xLength][yLength];
		
		for (int x = 0; x < xLength; ++x)
		{
		    for (int y = 0; y < yLength; ++y)
			{
			    input[x][y] = Integer.parseInt(input_byline[y].substring(x, x + 1)); 
			}
		}
		
		int[] gammaRateBinary = new int[xLength];
		int[] epsilonRateBinary = new int[xLength];
		
		for (int x = 0; x < xLength; ++x)
		{
		    int sum = 0;
			for (int y = 0; y < yLength; ++y)
			{
			    sum += input[x][y];
			}
			
		    if (sum > yLength / 2)
			{
			    gammaRateBinary[x] = 1;
			}
			else
			{
			    epsilonRateBinary[x] = 1;
			}
		}
		
		long gammaRate = binaryIntArrayToDecimal(gammaRateBinary);
		long epsilonRate = binaryIntArrayToDecimal(epsilonRateBinary);
		
		StdOut.println(gammaRate * epsilonRate);
		
	}
	
	public static long binaryIntArrayToDecimal(int[] inputArray)
	{
	    int length = inputArray.length;
		long decimal = 0;
		for (int i = 0; i < length; ++i)
		{
		    decimal += Math.pow(2, i) * inputArray[length - i - 1];
		}
		return decimal;
	}
}
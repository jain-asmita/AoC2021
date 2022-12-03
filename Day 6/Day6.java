public class Day6
{
	public static void main(String[] args)
	{
		String allInput = StdIn.readAll();
		String[] input_byFish = allInput.split(",");
		long[] input = new long[9];
		int nDays = 256;
		
		for(int i = 0; i < input_byFish.length; ++i)
		{
			int days = 	Integer.parseInt(input_byFish[i]);
			input[days] += (long) 1;
		}
	
		for(int day = 0; day < nDays; ++day)
		{
			long[] copyInput = new long[9];
			for(int daysLeft = 8; daysLeft > 0; --daysLeft)
			{
				if(daysLeft == 1)
				{
					copyInput[8] = input[daysLeft - 1];
					copyInput[6] += input[daysLeft - 1];
					copyInput[daysLeft - 1] = input[daysLeft];
				}
				else
				{
					copyInput[daysLeft - 1] = input[daysLeft];
				}
			}
			input = copyInput;
		}
		
		long sum = (long) 0;
		for(int i = 0; i < 9; ++i)
		{
			sum += input[i];
		}
		
		StdOut.println(sum);
	}
}
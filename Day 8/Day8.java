import java.util.HashSet;

public class Day8
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		HashSet<HashSet<String>> output = new HashSet<HashSet<String>>();
		int silverAnswer = 0;
		
		for(int line = 0; line < nLines; ++line)
		{
			StdOut.println(input_byLine[line]);
			String outputPart = input_byLine[line].split("\s\\|\s")[1];
			StdOut.println(outputPart);
			String[] outputPartArray = outputPart.split(" ");
			
			for (int digit = 0; digit < 4; ++digit)
			{
				int length = outputPartArray[digit].length();
				if(length == 2 || length == 3 || length == 4 || length == 7)
				{
					++silverAnswer;
				}
			}
		}
		
		StdOut.println(silverAnswer);
		
		
	}
}
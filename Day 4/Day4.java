public class Day4
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byPart = allInput.split("\r\n\r\n");
		int nDraws = input_byPart[0].split(",").length;
		int sideLength = 5;
		int nBoards = input_byPart.length - 1;
		int[] randomDraws = new int[nDraws];
		int[][][] boards = new int[nBoards][sideLength][sideLength];
		
		for (int i = 0; i < nDraws; ++i)
		{
			randomDraws[i] = Integer.parseInt(input_byPart[0].split(",")[i]);
		}
		
		for (int boardN = 0; boardN < nBoards; ++boardN)
		{
			String[] board_byLine = input_byPart[boardN + 1].split("\r\n");
			for (int rowN = 0; rowN < sideLength; ++rowN)
			{
				for (int columnN = 0; columnN < sideLength; ++columnN)
				{
					boards[boardN][rowN][columnN] = Integer.parseInt(board_byLine[columnN].substring(rowN * 3, rowN * 3 + 2).trim());
				}					
			}
		}
		
		int[][][] score = new int[nBoards][sideLength][sideLength];
		long winningScore = 0;
		boolean[] hasWon = new boolean[nBoards];
		
		loop:
		for(int drawN = 0; drawN < nDraws; ++drawN)
		{
			int draw = randomDraws[drawN];
			for(int boardN = 0; boardN < nBoards; ++boardN)
			{
				for(int rowN = 0; rowN < sideLength; ++rowN)
				{
					for(int columnN = 0; columnN < sideLength; ++columnN)
					{
						if(boards[boardN][rowN][columnN] == draw)
						{
							score[boardN][rowN][columnN] = 1;
						}
					}						
				}
			    hasWon[boardN] = hasBoardWon(score[boardN]);
				if(sumBooleanArray(hasWon) == nBoards)
			    {
			    	winningScore = findScore(boards[boardN], score[boardN], draw);
			    	break loop;
			    }
			}
		}
		
		StdOut.println(winningScore);
		
	}
	
	public static int sumBooleanArray(boolean[] array)
	{
		int sum = 0;
		for(int i = 0; i < array.length; ++i)
		{
			sum = array[i] ? sum + 1 : sum;
		}
		return sum;
	}
	
	public static boolean hasBoardWon(int[][] score)
	{
		int sideLength = 5;
		for(int rowN = 0; rowN < sideLength; ++rowN)
		{
			int rowSum = 0;
			for(int columnN = 0; columnN < sideLength; ++columnN)
			{
				if(score[rowN][columnN] == 1)
				{
					++rowSum;
				}
			}
			if(rowSum == sideLength)
			{
				return true;
			}
		}
		
		for(int columnN = 0; columnN < sideLength; ++columnN)
		{
			int columnSum = 0;
			for(int rowN = 0; rowN < sideLength; ++rowN)
			{
				if(score[rowN][columnN] == 1)
				{
					++columnSum;
				}
			}
			if(columnSum == sideLength)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static long findScore(int[][] board, int[][] score, int lastDraw)
	{
		long finalScore = 0;
		int sideLength = 5;
		for(int rowN = 0; rowN < sideLength; ++rowN)
		{
			for(int columnN = 0; columnN < sideLength; ++columnN)
			{
				if(score[rowN][columnN] == 0)
				{
					finalScore += board[rowN][columnN];
				}
			}
		}
		return finalScore * lastDraw;
	}
	
    public static void print2DIntArray(int[][] array, int length)
	{
		for(int i = 0; i < length; ++i)
		{
			for(int j = 0; j < length; ++j)
			{
				StdOut.print(array[i][j]);
			}
			StdOut.println("");
		}
	}
}
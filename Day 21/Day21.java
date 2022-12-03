public class Day21
{
    public static void main(String[] args)
	{
		long startTime = System.nanoTime();
	    int player1Score = 0;
		int player2Score = 0;
		
		int player1Pos = 3;
        int player2Pos = 1;
		int i = 0;
		
		int nRolls = 0;

        while(player1Score < 1000 && player2Score < 1000)
        {
		    i = (i == 100) ? 1 : i + 1;
			if ((nRolls / 3) % 2 == 0 && (nRolls + 1) % 3 == 0)
			{
			    player1Pos = (player1Pos + i) % 10;
				player1Score += player1Pos + 1;
			}
			else if ((nRolls / 3) % 2 == 0 )
			{
			    player1Pos = (player1Pos + i) % 10;
			}
			else if ((nRolls + 1) % 3 == 0)
			{
			    player2Pos = (player2Pos + i) % 10;
				player2Score += player2Pos + 1;
			}
			else
			{
			    player2Pos = (player2Pos + i) % 10;
			}
			nRolls += 1;
        }
		System.out.println(nRolls);
        System.out.println(Math.min(player1Score, player2Score) * nRolls);	

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime * Math.pow(10, -9));		
	}
}
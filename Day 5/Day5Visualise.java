public class Day5Visualise
{
    public static void main(String[] args)
	{
	    String allInput = StdIn.readAll();
		String[] input_byLine = allInput.split("\r\n");
		int nLines = input_byLine.length;
		Draw lines = new Draw();
		lines.setXscale(0, 1000);
		lines.setYscale(0, 1000);
		
		for(int line = 0; line < nLines; ++line)
		{
			int x1 = Integer.parseInt(input_byLine[line].split(" -> ")[0].split(",")[0]);
			int y1 = Integer.parseInt(input_byLine[line].split(" -> ")[0].split(",")[1]);
			int x2 = Integer.parseInt(input_byLine[line].split(" -> ")[1].split(",")[0]);
			int y2 = Integer.parseInt(input_byLine[line].split(" -> ")[1].split(",")[1]);
			lines.line(x1, y1, x2, y2);
		}
	}
}
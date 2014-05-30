package parser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class parser
{
	public static void main(String[] args)
	{
		BufferedReader br = null;
		int totalSuccess[] = new int[4];
		int totalFails[] =  new int[4];
		int success = 3;
		int failure = 0;
		
		int flag = 0;
		try
		{
			String sCurrentLine;
			br = new BufferedReader(new FileReader("C:\\Users\\chandrad\\Desktop\\filesForParsing\\jmeter.log"));
			File file = new File("C:\\Users\\chandrad\\Desktop\\filesForParsing\\parsed.txt");
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while ((sCurrentLine = br.readLine()) != null)
			{
				if (sCurrentLine.contains("2014/05/23"))
					flag = 0;
				if (sCurrentLine.contains("2014/05/24"))
					flag = 1;
				if (sCurrentLine.contains("2014/05/25"))
					flag = 2;
				if (sCurrentLine.contains("2014/05/26"))
					flag = 3;
				
				if (sCurrentLine.contains("Thread started:"))
				{
					success = 3;
					failure = 0;
				}
				if (sCurrentLine.contains("ERROR") && sCurrentLine.contains("readResponse"))
				{
					failure++;
					bw.write("" + sCurrentLine);
				}
				if (sCurrentLine.contains("Thread finished:"))
				{
					totalFails[flag] += failure;
					totalSuccess[flag] += success-failure;
				}
			}
			bw.close();
			System.out.println("Total Success on 2014/05/23: " + totalSuccess[0]);
			System.out.println("Total Fails   on 2014/05/23: " + totalFails[0]);
			System.out.println("Total Success on 2014/05/24: " + totalSuccess[1]);
			System.out.println("Total Fails   on 2014/05/24: " + totalFails[1]);
			System.out.println("Total Success on 2014/05/25: " + totalSuccess[2]);
			System.out.println("Total Fails   on 2014/05/25: " + totalFails[2]);
			System.out.println("Total Success on 2014/05/26: " + totalSuccess[3]);
			System.out.println("Total Fails   on 2014/05/26: " + totalFails[3]);
			int TotalSuccess = totalSuccess[0] + totalSuccess[1] + totalSuccess[2] + totalSuccess[3]; 
			int TotalFails = totalFails[0] + totalFails[1] + totalFails[2] + totalFails[3]; 
			System.out.println("Total Success  : " + TotalSuccess);
			System.out.println("Total Fails    : " + TotalFails);
			System.out.println("Total Requests : " + (TotalSuccess+TotalFails));
			System.out.println("Done");
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (br != null)
					br.close();
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public static void wite(String line)
	{
		try
		{
			File file = new File("C:\\Users\\chandrad\\Desktop\\filesForParsing\\parsed.txt");
			// if file doesnt exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(line);
			bw.close();
			System.out.println("Done");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

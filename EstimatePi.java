import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class EstimatePi 
{

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the numer of game you would like played: ");
		int games = sc.nextInt();
		games--;
		System.out.println("Please enter number of darts per game: ");
		int darts = sc.nextInt();
		double rad = 0.5;
		ArrayList<Double> res = allGames(games, darts, rad);
		prints(res, games, darts, rad);
		sc.close();
	}
	
	public static double throwDart(double x, double y, double rad)
	{
		double ret = 0;
		double dist = Math.sqrt((x*x)+(y*y));
		if(dist < rad)
		{
			ret = 1;
		}
		return ret;
	}
	
	public static double oneGame(int darts, double rad) 
	{
		int count = 0;
		double dartsIn = 0;
		while (count <= darts)
		{
			Random rand  = new Random();
			double x = rand.nextDouble() - 0.5;
			double y = rand.nextDouble() - 0.5;
			double in = throwDart(x,y,rad);
			if(in==1)
			{
				dartsIn++;
			}
			count++;
		}
		double estPi = (4 *(dartsIn/darts));
		return estPi;
	}

	private static double getStandDev(ArrayList<Double> xyList) 
	{
		
		double mean = mean(xyList);
		double newSum = 0;
		double size = (double) xyList.size();
		int count = 0;
		while (count < size)
		{
			double num = xyList.get(count);
			double num3 = Math.pow((num - mean), 2);
			newSum = newSum + num3;
			count++;
		}
		double temp = 1.0/size;
		double temp2 = temp * newSum;
		double SD = Math.sqrt(temp2);
		return SD;
	}

	public static ArrayList<Double> allGames(int numOfGames, int darts, double rad)
	{
		int count = 0;
		ArrayList<Double> results = new ArrayList<Double>(0);
		while (numOfGames >= count)
		{
			double game = oneGame(darts, rad);
			results.add(game);
			count++;
		}
		return results;
	}

	public static double mean(ArrayList<Double> list) 
	{
		int count = 0;
		double sum = 0;
		while (count < list.size())
		{
			double num = list.get(count);
			sum = sum + num;
			count++;
		}
		double mean = sum/list.size();
		return mean;	
	}
	
	public static void prints(ArrayList<Double> res, int numOfGames, int darts, double rad)
	{
	
		int count = 0;
		System.out.println();
		System.out.println();
		System.out.println("The number of games is " + (numOfGames+1)  + " and the number of darts per game is " + darts);
		System.out.println("The radius of the circle is " + rad + ".");
		System.out.println();
		System.out.println();
		while(count < res.size())
		{
			count++;
			int count2 = count -1;
			System.out.println(count + ". Pi Estimate: " + res.get(count2) +  ".");
		}
		System.out.println();
		double myMean = mean(res);
		double SD = getStandDev(res);
		System.out.println("Pi Estimate Mean of All: " + myMean);
		System.out.println("Standard Deviation of All: " + SD);
	}
}

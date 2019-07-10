package edx.battleship;

import java.util.Scanner;

public class BattleShip {
	
	private String [][] grid = new String [10][10];
	private Scanner scan = new Scanner(System.in);

	public BattleShip()
	{
		printGrid();
		userSetsLocation();
		printGrid();
		computerSetsLocation();
	}
	
	public void play() 
	{
		int countUserShip = 5;
		int countComputerShip = 5;
		while(countComputerShip !=0 && countUserShip != 0) 
		{
			playUser();
			countUserShip = shipCounter()[0];
			printGrid();
			System.out.format("\n\n     ------- Your ships: %d | Computer ships: %d ---------", countUserShip, countComputerShip);

			playComputer();
			countComputerShip = shipCounter()[1];
			printGrid();
			System.out.format("\n\n     ------- Your ships: %d | Computer ships: %d ----------", countUserShip, countComputerShip);
		}
		if(countComputerShip == 0) 
		{
			System.out.println("\n\n     ------- Hooray! You win the battle :) ");
		}
		else 
		{
			System.out.println("\n\n     ------- Opps! You lost the battle :( ");
		}
		scan.close();
	}
	
	/**
	 * Checks how many user ships and computer ships are left.
	 * @param grid 
	 * @return number of user and computer ships
	 */
	private int [] shipCounter() 
	{
		int countUserShip = 0;
		int countComputerShip = 0;
		int [] counters = new int[2];
		for(int i = 0; i < 10; i++) 
		{
			for (int j = 0; j < 10; j++) 
			{
				if(grid[i][j] == "@") 
				{
					countUserShip++;
				}
				else if(grid[i][j] == "2") 
				{
					countComputerShip++;
				}
			}
		}
		counters[0] = countUserShip;
		counters[1] = countComputerShip;
		return counters;
	}
	
	/**
	 * User puts the location of the ships
	 * @param grid
	 */
	private void userSetsLocation()
	{
		int counter = 0;
		while(counter < 5) 
		{
			while(true) 
			{
				System.out.format("Please Enter X coordinate for ship %d \n", counter + 1);
				int xCord = scan.nextInt();
				System.out.format("Please Enter Y coordinate for ship %d \n", counter + 1);
				int yCord = scan.nextInt();
				if(yCord < 10 && yCord >= 0 && xCord < 10 && xCord >= 0 && grid[xCord][yCord] != "@")
				{
					grid[xCord][yCord] = "@";
					break;
				}
			}
			counter++; 
		}
	}
	
	/**
	 * Computer puts the locations of the ships
	 * @param grid
	 */
	private void computerSetsLocation()
	{
		System.out.println("\n\n     ------- Computer is deploying ships ---------- \n");
		int counter = 0;
		while(counter < 5) 
		{
			while(true) 
			{
				int xCord = (int)(Math.random()*10);
				int yCord = (int)(Math.random()*10);
				if(grid[xCord][yCord] != "@" && grid[xCord][yCord] != "2")
				{
					grid[xCord][yCord] = "2";
					System.out.format("%d. ship DEPLOYED \n", counter + 1);
					break;
				}
			}
			counter++; 
		}
		System.out.println("\n      --------------------------\n");
	}
	
	/**
	 * Prints the current grid output
	 * @param grid
	 */
	private void printGrid() 
	{
		for(int i = 0; i < 10; i++) 
		{
			System.out.print(i + "  |");
			for (int j = 0; j < 10; j++) 
			{
				String placeHolder = grid[i][j];
				if(placeHolder == null || placeHolder == "2" || placeHolder == "CM")
				{
					placeHolder = " ";
				}
				System.out.format("__%s__|", placeHolder);
			}
			System.out.println(i);
		}
	}

	/**
	 * User starts playing
	 * @param grid
	 */
	private void playUser() 
	{
		System.out.println("\n\n     YOUR TURN       \n");
		while(true) 
		{
			System.out.println("Please Enter X coordinate");
			int xCord = scan.nextInt();
			System.out.println("Please Enter Y coordinate");
			int yCord = scan.nextInt();
			if(yCord < 10 && yCord >= 0 && xCord < 10 && xCord >= 0 && grid[xCord][yCord] != "!")
			{
				if(grid[xCord][yCord] == "@")
				{
					grid[xCord][yCord] = "x";
					System.out.println("\n\n     ------- Oh no, you sunk your own ship :( ---------- \n");
				}
				else if(grid[xCord][yCord] == null)
				{
					grid[xCord][yCord] = "-";
					System.out.println("\n\n     ------- Sorry, you missed ---------- \n");
				}
				else if(grid[xCord][yCord] == "2") {
					grid[xCord][yCord] = "!";
					System.out.println("\n\n     ------- Boom! You sunk the ship! ---------- \n");
				}
				break;
			}
		}
	}

	/**
	 * Computer starts playing
	 * @param grid
	 */
	private void playComputer() 
	{
		System.out.println("\n\n     COMPUTERS TURN       \n");
		while(true) 
		{
			int xCord = (int)(Math.random()*10);
			int yCord = (int)(Math.random()*10);
			if(grid[xCord][yCord] != "x" && grid[xCord][yCord] != "CM")
			{
				if(grid[xCord][yCord] == "2")
				{
					grid[xCord][yCord] = "!";
					System.out.println("\n\n     ------- The Computer sunk one of its own ships ---------- \n");
				}
				else if(grid[xCord][yCord] == null)
				{
					grid[xCord][yCord] = "CM";
					System.out.println("\n\n     ------- Computer missed ---------- \n");
				}
				else if(grid[xCord][yCord] == "2") {
					grid[xCord][yCord] = "!";
					System.out.println("\n\n     ------- Boom! You sunk the ship! ---------- \n");
				}
				break;
			}
		}
	}
}

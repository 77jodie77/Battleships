import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
/**
 * 
 */

/**
 * @author jodielaurenson
 *
 */
public class BattleshipsGame {

	/**
	 * 
	 */
	public BattleshipsGame() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args main arguments array
	 */
	public static void main(String[] args) {
		BattleshipsGame myGame = new BattleshipsGame();
		System.out.println("Welcome to Battleships");
		
		myGame.mainMenu();
	}

	
	
	/**
	 * Asks user if they want to start a new game or load previous game from file
	 */
	public void mainMenu()
	{
		System.out.println();
		System.out.println("Please select one of the options below");
		System.out.println("1. New Game (single player)");
		System.out.println("2. Load Game(single player)");
		System.out.println("3. New Game(multiplayer)");
		System.out.println("0. Exit");
		System.out.println();
		
		boolean exit = false;
		
		
		do
		{
			Scanner s1 = new Scanner(System.in);
			System.out.println("Please enter a number: ");
			int choice = s1.nextInt();
			System.out.println();
			if (choice == 1)
			{
				Grid myGrid = new Grid();
				Ship myShip = new Ship();
				
				Ship[] ships=myGrid.placeShips();
				
				gameMenu(ships,myGrid);
			}
			else if (choice==2)
			{
				Grid myGrid = new Grid();
				
				
				Ship[] ships=myGrid.placeShips();
				
				myGrid.loadGame();
				gameMenu(ships,myGrid);
			}
			else if (choice == 3)
			{
				Grid myGrid = new Grid();
				Grid comGrid = new Grid();
				
				Ship[] ships=myGrid.placeShips();
				Ship[] comShips = comGrid.placeShips();
				
				Computer com = new Computer();
				
				String[][] cShipGrid = new String[10][10];
				
				
				comGrid.placeShips();

				gameMenuMP(ships,myGrid,com,comGrid,comShips,cShipGrid);
			}
			else if (choice == 0)
			{
				System.out.println("Closing");
				exit = true;
			}
			else 
			{
				System.out.println("Invalid - please try again");
			}
			
		}
		while (exit == false); //part of the do while loop
	}
	
	
	
	/**
	 * Main menu for single player
	 * @param ships array of ship objects
	 * @param myGrid object used to call methods in grid class
	 */
	public void gameMenu(Ship[] ships, Grid myGrid)
	{
		
		

		
		boolean exit = false;
		
		Player mySelf = new Player();
		
		
		//A do while loop is used as the code inside the condition has to be run at least once
		do
		{
			System.out.println();
			System.out.println("Please select one of the options below");
			System.out.println("1. Fire shot");
			System.out.println("2. Save progress");
			System.out.println("0. Exit");
			System.out.println();
			
			System.out.println();
			
			Scanner s1 = new Scanner(System.in);
			System.out.println("Enter a number: ");
			int choice = s1.nextInt();
			System.out.println();
			if (choice == 1)
			{
			
				myGrid.displayGrid();
				
				int x = -1;
				boolean valid = false;
				
				// Loop until we get a valid int input from the user
				while(x>=10 ||x<0) { //checks that x pos is on the board
					
					do {
					
						try {
						
							System.out.println("Enter valid x position in range");
							System.out.println("X position: ");
							x = s1.nextInt()-1;
					
						valid = true;
					}
					catch (InputMismatchException e) //runs if value is not an int
					{
						// Read and discard the invalid input
						s1.next();
						System.out.println("Please enter a valid integer:");
						}
					}while (!valid);
				} 
				
				int y = -1;
				valid = false;
				
				// Loop until we get a valid int input from the user
				while(y>=10 ||y<0) { //checks that y pos is on the board
					
					do {
					
						try {
						
							System.out.println("Enter valid y position in range");
							System.out.println("Y position: ");
							y = s1.nextInt()-1;
					
						valid = true;
					}
					catch (InputMismatchException e) //runs if value is not an int
					{
						// Read and discard the invalid input
						s1.next();
						System.out.println("Please enter a valid integer:");
						}
					}while (!valid);
				}
				
				myGrid.attackCell(y,x,ships,mySelf,myGrid);
				mySelf.incrementShotsFired();
				
			}
			else if (choice == 2)
			{
				myGrid.saveGame(ships,mySelf);
			}
			else if (choice==0)
			{
				System.out.println("Closing");
				System.exit(0);
				exit = true;
			}
			else
			{
				System.out.println("invalid - please enter again");
			}
			
			
		}
		while (exit == false); 
		
	}
	
	/**
	 * Main menu for multiplayer
	 * 
	 * @param ships array of players ship objects
	 * @param myGrid Grid object used to call methods in grid class
	 * @param com computer object used to call methods in computer class
	 * @param comGrid computers grid object to call methods in grid class
	 * @param comShips array of computers ships
	 * @param cShipGrid 2D array of computers grid
	 */
	public void gameMenuMP(Ship[] ships, Grid myGrid,Computer com,Grid comGrid, Ship[] comShips,String[][] cShipGrid)
	{
		boolean exit = false;
		int turn = 0; //turn is set to user by default
		Player mySelf = new Player();
		
		
		//A do while loop is used as the code inside the condition has to be run at least once
		do
		{
			if(turn==0)
			{
				displayMenu();
				System.out.println();
				
		
				Scanner s1 = new Scanner(System.in);
				System.out.println("Please enter a number: ");
				int choice = s1.nextInt();
				System.out.println();
				if (choice == 1)
				{
				
					myGrid.displayGrid();
					
					int x = -1;
					boolean valid = false;
					
					// Loop until we get a valid int input from the user
					while(x>=10 ||x<0) { //checks that x pos is on the board
						
						do {
						
							try {
							
								System.out.println("Enter valid x position in range");
								System.out.println("X position: ");
								x = s1.nextInt()-1;
						
							valid = true;
						}
						catch (InputMismatchException e) //runs if value is not an int
						{
							// Read and discard the invalid input
							s1.next();
							System.out.println("Please enter a valid integer:");
							}
						}while (!valid);
					} 
					
					int y = -1;
					valid = false;
					
					// Loop until we get a valid int input from the user
					while(y>=10 ||y<0) { //checks that y pos is on the board
						
						do {
						
							try {
							
								System.out.println("Enter valid y position in range");
								System.out.println("Y position: ");
								y = s1.nextInt()-1;
						
							valid = true;
						}
						catch (InputMismatchException e) //runs if value is not an int
						{
							// Read and discard the invalid input
							s1.next();
							System.out.println("Please enter a valid integer:");
							}
						}while (!valid);
					}
					
					myGrid.attackCell(y,x,ships,mySelf,myGrid);
					mySelf.incrementShotsFired();
					
					turn = 1; //computers turn
				}
				else if (choice == 2)
				{
					myGrid.saveGame(ships,mySelf);
				}
				else if (choice==0)
				{
					System.out.println("Closing");
					exit = true;
				}
				
			}
			else if(turn==1)
			{
				
				com.fireRandomShot(cShipGrid,comShips,comGrid,mySelf);
				turn=0;
			}
		}
		while (exit == false); 
		
	}
}




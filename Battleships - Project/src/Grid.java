import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author jodielaurenson
 *
 */
public class Grid {

	//boolean[][] grid;
	String[][] shipGrid;
	int column;
	int row;
	/**
	 * constructor that creates an empty grid
	 */
	public Grid() {
		column = 10;
		row = 10;
		//grid = new boolean[row][column];
		shipGrid = new String[row][column];
		for (int y = 0; y < row; y++)
		{
			for (int x = 0; x < column; x++)
			{
				//grid[y][x] = false; //set every cell to false (not attacked)
				shipGrid[y][x] = "-";
			}
		}
	}

	/**
	 * Displays the grid which the user sees
	 */
	public void displayGrid()
	{
		
		for (int col = 0; col < column; col++)
		{
			System.out.print("\t"+(col+1));
		}
		System.out.println();
		for (int y = 0; y < row; y++)
		{
			System.out.print((y+1)+"\t");
			for (int x = 0; x < column; x++)
			{
			
				
				if (shipGrid[y][x].equals("X"))
				{
					System.out.print("X\t");
				}
				else if (shipGrid[y][x].equals("O"))
				{
					System.out.print("O\t");
				}
				else 
				{
					System.out.print("-\t");
				}
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("-----------------------------------------------------");
	}
	
	
	

	/**
	 * Used to create an array of ship objects by calling constructor from Ship class
	 * 
	 * @param ships array of ship objects
	 * @param i index which determines current ship being checked
	 * @return ships[i] indexed object in ships class
	 */
	public Ship createShips(Ship[] ships,int i)
	{
		
		if (i==0) {
			ships[i] = new Ship("Battleship");
			
		}
		else if (i==1) {
			ships[i] = new Ship("Cruiser1");
		}
		else if (i==2) {
			ships[i] = new Ship("Cruiser2");
		}
		else if (i==3) {
			ships[i] = new Ship("Destroyer1");
		}
		else if (i==4) {
			ships[i] = new Ship("Destroyer2");
		}
		else if (i==5) {
			ships[i] = new Ship("Destroyer3");
		}
		else if (i==6) {
			ships[i] = new Ship("Submarine1");
		}
		else if (i==7) {
			ships[i] = new Ship("Submarine2");
		}
		else if (i==8) {
			ships[i] = new Ship("Submarine3");
		}
		else if (i==9) {
			ships[i] = new Ship("Submarine4");
		}
		
		return ships[i];
	}

	/**
	 * Creates ships, displays information about the ships and checks if it is a valid position
	 * @return ships array of all ship objects
	 */
	public Ship[] placeShips()
	{
		Ship[] ships;
		ships = new Ship[10];
		for(int i=0; i<ships.length;i++) {
			
			ships[i]=createShips(ships,i);

			
			updateShipGrid(ships,i);
		}
		clearOverlappedShip(ships, ships.length-1);
		return ships;
	}
	
	/**
	 * This method goes through every position on the ship and checks if it is out of bounds and calls a method to 
	 * check if it is overlapping other ships
	 * @param ships array of all ship objects
	 * @param i current ship being checked
	 */
	public void updateShipGrid(Ship[] ships, int i)
	{

		
	
		if (ships[i].getShipDirection()==0) {  //if ship is horizontal
			for (int pos = 0; pos<ships[i].getShipSize(); pos++) { // go through every position of the ship
				
				
				int x = ships[i].getShipX()+pos;
				int y = ships[i].getShipY();
				

				if (!(shipGrid[y][x]).equals("-")) { //While the position being updated is not an empty string
					
					
					clearOverlappedShip(ships,i-1); //clears board from overlapped ships
					ships[i]=createShips(ships,i); //Finds a position that does not have a ship on it
					
				}
				else
				{
					shipGrid[ships[i].getShipY()][ships[i].getShipX()+pos]=ships[i].getShipCode();
				}
			}
		} 
		else if (ships[i].getShipDirection()==1) {  //0=horizontal,1=vertical
			for (int pos = 0; pos<ships[i].getShipSize(); pos++) {
				
				
				int x = ships[i].getShipX();
				int y = ships[i].getShipY()+pos;
				if (!(shipGrid[y][x]).equals("-")) { //While the position being updated is not an empty string
					
					
					clearOverlappedShip(ships,i-1); //clears board from overlapped ships
					ships[i]=createShips(ships,i); //Finds a position that does not have a ship on it
					Grid myGrid = new Grid();
					
					ships=myGrid.placeShips();
					
				}
				else
				{
					shipGrid[ships[i].getShipY()+pos][ships[i].getShipX()]=ships[i].getShipCode();
				}
				
			}
		}
	}
	
	
	
	/**
	 * Resets overlapped ships grid to previous ship code
	 * 
	 * @param ships array of ship objects
	 * @param lastShipNo number of ships already placed
	 */
	public void clearOverlappedShip(Ship[] ships, int lastShipNo)
	{
		for (int y = 0; y < row; y++)
		{
			for (int x = 0; x < column; x++)	//go through every grid position
			{
				shipGrid[y][x]="-";
			}
		}
		for(int ship = 0; ship<lastShipNo;ship++)	//go through every ship
		{
			for (int pos = 0; pos<ships[ship].getShipSize(); pos++) {	//go through every position
				if (ships[ship].getShipDirection()==0) {  //if ship is horizontal
					shipGrid[ships[ship].getShipY()][ships[ship].getShipX()+pos]=ships[ship].getShipCode();		//reset to ship code
				}
				else
				{
					shipGrid[ships[ship].getShipY()+pos][ships[ship].getShipX()]=ships[ship].getShipCode();		//reset to ship code
				}
			}
		}
		
	}
	
	

	
	/**
	 * Checks whether target position is valid or not and calls method which
	 * determines if a ship was hit or not
	 * 
	 * @param x target position, x coordinate
	 * @param y target position, y coordinate
	 * @param ships array of ship objects
	 * @return boolean value whether ship is hit or not
	 * @param mySelf object for user
	 * @param myGrid object used to call methods in grid class
	 */
	public boolean attackCell(int x, int y, Ship[]ships, Player mySelf,Grid myGrid) 
	{
		boolean attacked = getCellStatus(x,y);

		if(attacked == true||(shipGrid[x][y]).equals("O")||(shipGrid[x][y]).equals("X"))
		{
			System.out.println("invalid - this position has already been attacked");
			System.out.println();
			//Grid myGrid = new Grid();
			BattleshipsGame myGame = new BattleshipsGame();
			mySelf.decreaseShotsFired();
			myGame.gameMenu( ships,myGrid);
			
		}
		else
		{
			System.out.println("Shot fired");
			System.out.println();
			
			if (!(shipGrid[x][y]).equals("-")||!(shipGrid[y][x]).equals("-")) {
				
				System.out.println("A SHIP HAS BEEN HIT!");
				updateCellStatus(x, y, ships, mySelf);
				shipGrid[x][y] = "O";
				
				
			} else {
				System.out.println("MISS");
				shipGrid[x][y] = "X";
				
			}
			
		}
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println();
		displayGrid();
		return true;
	}
	
	/**
	 * Checks whether target position is valid or not and calls method which
	 * determines if a ship was hit or not
	 * 
	 * @param x target position, x coordinate
	 * @param y target position, y coordinate
	 * @param ships array of ship objects
	 * @return boolean value whether ship is hit or not
	 */
	public static boolean attackGUI(int x, int y, Ship[]ships) 
	{
		//----------DISPLAYING SHIP POSITION
		

		boolean attacked = getCellStatus(x,y);

		if(attacked == true)
		{
			System.out.println("invalid - this position has already been attacked");
			
		}
		else
		{
			System.out.println("Shot fired");

			
			
			
			if (!(shipGrid[y][x]).equals("-")) {
				
				System.out.println("A SHIP HAS BEEN HIT!");
				shipGrid[y][x] = "O";
			} 
			else {
				System.out.println("MISS");
				shipGrid[y][x] = "X";	
			}
		}	
		return true;
	}
	
	/**
	 * increases number of cells hit and prints message if a ship has been sunk
	 * 
	 * @param y position of target, y coordinate
	 * @param x position of target, x coordinate
	 * @param ships array of ship objects
	 * @param mySelf object for user
	 * @return returns null if no ships have been fully sunk
	 */
	
	
	public String updateCellStatus(int y,int x,Ship[] ships, Player mySelf) {
		for(int i=0; i<9;i++) {
			
			if (ships[i].getShipDirection()==0) {  //if ship is horizontal
				for (int pos = 0; pos<ships[i].getShipSize(); pos++) { // go through every position of the ship
					
					if (ships[i].getShipY()==y && ships[i].getShipX()+pos==x) {
					
						ships[i].increaseCellStatuses();
						if(ships[i].getCellStatuses()==ships[i].getShipSize())
						{
							System.out.println("The ship " + ships[i].getShipName() + " has been sunk!");
							checkIfEndGame(ships,mySelf);
						}
						
					}
					
				}
			}
			else if (ships[i].getShipDirection()==1) {  //0=horizontal,1=vertical
				for (int pos = 0; pos<ships[i].getShipSize(); pos++) {
					
					if (ships[i].getShipX()==x && ships[i].getShipY()+pos==y) {
						
						ships[i].increaseCellStatuses();
						if(ships[i].getCellStatuses()==ships[i].getShipSize())
						{
							System.out.println("The ship " + ships[i].getShipName() + " has been sunk!");
							checkIfEndGame(ships,mySelf);
						}
					}
					
				}
			}
			
			
		}
		return "null";
	}
	
	
		/**
		 * checks if ship is where target coordinate is
		 * 
		 * @param shotX x position that user is firing at
		 * @param shotY y position that user is firing at
		 * @param shipX x position on grid
		 * @param shipY y position on grid
		 * @param shipSize size of ship
		 * @param shipDirection direction of ship
		 * @return true or false depending on whether a ship is present at users coordinates
		 */
		public boolean checkIfShipPresent(int shotX, int shotY, int shipX, int shipY, int shipSize, int shipDirection){
			
			if (shipDirection==0 && shipY==shotY) { //if horizontal and on the same row
				for (int i = shipX; i<shipSize;i++){
					if (shotX==i) {
						return true;
					}
				}
			} else if (shipDirection==1 && shipX==shotX) { //if vertical and on same column
				for (int i = shipY; i<shipSize;i++){
					if (shotY==i) {
						return true;
					}
				}
			} 
			return false;
		}
		
		/**
		 * Writes the values of the variables in every object to an external text file 
		 * and then writes the coordinates of positions shot
		 *
		 * @param ships array of ship objects
		 * @param mySelf object for user
		 */
		public void saveGame(Ship[] ships,Player mySelf)
		{
			FileOutputStream outputStream = null;
			PrintWriter printWriter = null;
			
			try
			{
				outputStream = new FileOutputStream("savedGame.txt");
				printWriter = new PrintWriter(outputStream);
				printWriter.println(mySelf.getShotsFired());
				for(int i = 0;i<9;i++)
				{
					printWriter.print(ships[i].getShipName()+" ");
					printWriter.print(ships[i].getShipCode()+" ");
					printWriter.print(ships[i].getShipSize()+" ");
					printWriter.print(ships[i].getShipX()+" ");
					printWriter.print(ships[i].getShipY()+" ");
					printWriter.print(ships[i].getShipDirection()+" ");
					printWriter.print(ships[i].getCellStatuses()+" ");
					printWriter.println();
				}
				
				
				
				for (int y = 0; y < row; y++)
				{
					for (int x = 0; x < column; x++)	//go through every grid position
					{
						//printWriter.print(shipGrid[y][x]+"\t");
						if(shipGrid[y][x]=="X")
						{
							printWriter.println("X "+x+" "+y);
						}
						else if(shipGrid[y][x]=="O")
						{
							printWriter.println("O "+x+" "+y);
						}
					}
					
				}
				
				//printWriter.println(lineMessage);
				System.out.println("Progress Saved");
				printWriter.close();
			}
			catch (IOException error)
			{
				System.out.println("Error in file write: " + error);
			}
		}
		
		/**
		 * Reads a file and parses it into arrays which is assigned to the objects
		 * then the coordinates below are read in to figure out what positions have been hit
		 */
		public void loadGame()
		{
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;

			shipGrid = new String[row][column];
			
			Ship[] ships;
			ships = new Ship[9];
			Player mySelf = new Player();
			
			try
			{
				fileReader = new FileReader("savedGame.txt");
				bufferedReader = new BufferedReader(fileReader);
				
				String nextLine = bufferedReader.readLine();
				
				int shots = Integer.parseInt(nextLine);
				mySelf.setShotsFired(shots);
				nextLine = bufferedReader.readLine();
				
				for (int ypos = 0; ypos < row; ypos++)
					{
						for (int xpos = 0; xpos < column; xpos++)	//go through every grid position
						{
							shipGrid[ypos][xpos]="-"; //set all to default
						
						}
					}
				
				for(int i = 0; i<9;i++)
				{
					ships[i]=new Ship();
					
					String[] parts = nextLine.split(" "); //first lines are split into an array that contains the objects fields
					
					//changes string to integer where needed
					int size = Integer.parseInt(parts[2]);
					int x = Integer.parseInt(parts[3]);
					int y = Integer.parseInt(parts[4]);
					int dir = Integer.parseInt(parts[5]);
					int status = Integer.parseInt(parts[6]);
					
					//sets all values in array to a variable in each object
					ships[i].setShipName(parts[0]); 
					ships[i].setShipCode(parts[1]);
					ships[i].setShipSize(size);
					ships[i].setShipX(x);
					ships[i].setShipY(y);
					ships[i].setShipDirection(dir);
					ships[i].setCellStatuses(status);
					
					nextLine = bufferedReader.readLine();
					
					if (ships[i].getShipDirection()==0) //if ship is horizontal
					{  
						for (int pos = 0; pos<ships[i].getShipSize(); pos++)// go through every position of the ship
						{
							shipGrid[ships[i].getShipY()][ships[i].getShipX()+pos]=ships[i].getShipCode();	//set the coordinate to the ship code
						}
					}
					
					if (ships[i].getShipDirection()==1) //if ship is vertical
					{  
						for (int pos = 0; pos<ships[i].getShipSize(); pos++) // go through every position of the ship
						{ 
							shipGrid[ships[i].getShipY()+pos][ships[i].getShipX()]=ships[i].getShipCode(); 	//set the coordinate to the ship code
						}
					}
					
				}
				
				while (nextLine != null)
				{
					
					String[] parts = nextLine.split(" "); //first lines are split into an array that contains the objects fields
					
					int x = Integer.parseInt(parts[1]);
					int y = Integer.parseInt(parts[2]);
					shipGrid[y][x]=parts[0];	//set position on grid to X or O
					
					nextLine = bufferedReader.readLine();
				}
			}
			catch (IOException error)
			{
				System.out.println("Error reading from file: " + error);
			}
			
		}
		
		/**
		 * increments the number of ships sunk variable if a new ship is sunk and if shipsSunk = 8 then
		 * game is over and asks user what they want to do
		 *
		 * @param ships array of ship objects
		 * @param mySelf object for user
		 */
		public void checkIfEndGame(Ship[] ships, Player mySelf)
		{
			int shipsSunk=0;
			for(int ship = 0;ship<9;ship++)
			{
				if(ships[ship].getCellStatuses()==ships[ship].getShipSize());
				{
					shipsSunk++;
				}
			}
			if(shipsSunk==8)
			{
				System.out.println("All ships have been sunk! Well done!");
				System.out.println("Moves taken: " + mySelf.getShotsFired());
				Scanner s1 = new Scanner(System.in);
				System.out.println("Play again?(Y/N) ");
				String choice = s1.nextLine();
				if (choice=="Y")
				{
					BattleshipsGame myGame = new BattleshipsGame();
					myGame.mainMenu();
				}
				else if (choice == "N")
				{
					System.exit(0);
				}
				else
				{
					System.out.println("Invalid");
					checkIfEndGame(ships, mySelf);
				}
			}
		}
	
	//-----------------------ACCESSOR AND MUTATOR METHODS------------------------
	/**
	 * 
	 * @param x position of target
	 * @param y position of target
	 * @return true if target is already hit and false if not
	 */
	public boolean getCellStatus(int x,int y)
	{
		if(shipGrid[y][x]=="X")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * accessor method for 2d array shipGrid
	 * 
	 * @return the shipGrid
	 */
	public String[][] getShipGrid() {
		return shipGrid;
	}
	
	/**
	 * returns the value in a specific location on grid
	 * 
	 * @param x position of target
	 * @param y position of target
	 * @return the value in that position
	 */
	public String getShipPos(int x,int y) {
		return shipGrid[y][x];
		
	}


	/**
	 * @param shipGrid the shipGrid to set
	 */
	public void setShipGrid(String[][] shipGrid) {
		this.shipGrid = shipGrid;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}


	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}


	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}


	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	
	
	
	
}

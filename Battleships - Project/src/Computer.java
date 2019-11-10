import java.util.Random;

/**
 * 
 */


/**
 * @author Jodie
 *
 */
public class Computer {

	/**
	 * 
	 */
	public Computer() {
		Grid comGrid = new Grid();
		String[][] cShipGrid = new String[10][10];
		Ship[] comShips = comGrid.placeShips();
	}

	
	/**
	 * Randomly chooses a position on the grid and checks if it is valid or not
	 * 
	 * @param cShipGrid The computers grid
	 * @param comShips Computers array of ship objects
	 * @param comGrid Computers grid used to call grid methods
	 * @param mySelf object for user
	 */
	public void fireRandomShot(String[][] cShipGrid, Ship[] comShips,Grid comGrid,Player mySelf)
	{
		Random rand = new Random();
		int x = rand.nextInt(10);
		int y = rand.nextInt(10);
		System.out.println(x);;
		System.out.println("Computer is firing at: "+(x+1)+", "+(y+1));
		
		
		boolean attacked = comGrid.getCellStatus(x,y);

		if(attacked==true)
		{
			fireRandomShot(cShipGrid,comShips,comGrid,mySelf);	//runs if position has already been shot
		}
		else
		{
			System.out.println("COM Shot fired");
			

				
				if (!(comGrid.getShipPos(x, y)).equals("-")) {
					
					System.out.println("OPPONANT HAS HIT A SHIP!");
					
					comGrid.updateCellStatus(y, x,comShips,mySelf);
					
				} else {
					System.out.println("OPPONANT MISSED");		
				}
			}
		
	}

	

	


	
	
}

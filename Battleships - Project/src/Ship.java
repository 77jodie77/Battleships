import java.util.Random;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author jodielaurenson
 *
 */
public class Ship {

	String shipName;
	String shipCode;
	int shipSize;
	int shipX;
	int shipY;
	int shipDirection; //0=horizontal,1=vertical
	//boolean[] cellStatuses;
	int cellStatuses;
	
	/**
	 * Constructor to create an initial ship
	 */
	public Ship() {
		shipName = "";
		shipCode = "";
		shipSize = 0;
		shipX = 0;
		shipY = 0;
		shipDirection = 1;
		
		cellStatuses = 0;
		
	}
	
	
	/**
	 * This is another constructor but I used overloading and added a parameter. This method
	 * creates random position and directions of the ships
	 * 
	 * @param Name name of battleship to be created
	 */
	public Ship(String Name) {
		shipName = Name;
		Random rand = new Random();
		
		if(shipName == "Battleship")
		{
			shipSize = 4;
			shipCode = "B";
		}
		else if(shipName == "Cruiser1")
		{
			shipSize = 3;
			shipCode = "C1";
		}
		else if(shipName == "Cruiser2")
		{
			shipSize = 3;
			shipCode = "C2";
		}
		else if(shipName == "Destroyer1")
		{
			shipSize = 2;
			shipCode = "D1";
		}
		else if(shipName == "Destroyer2")
		{
			shipSize = 2;
			shipCode = "D2";
		}
		else if(shipName == "Destroyer3")
		{
			shipSize = 2;
			shipCode = "D3";
		}
		else if(shipName == "Submarine1")
		{
			shipSize = 1;
			shipCode = "S1";
		}
		else if(shipName == "Submarine2")
		{
			shipSize = 1;
			shipCode = "S2";
		}
		else if(shipName == "Submarine3")
		{
			shipSize = 1;
			shipCode = "S3";
		}
		else if(shipName == "Submarine4")
		{
			shipSize = 1;
			shipCode = "S4";
		}
		shipDirection = rand.nextInt(1);
		
		if(shipDirection==0) //If ship is horizontal
		{
			shipX = rand.nextInt(10-shipSize); //makes sure ship is not out of bounds
			shipY = rand.nextInt(10);
			
		}
		else //If ship is vertical
		{
			shipY = rand.nextInt(10-shipSize);  //makes sure ship is not out of bounds
			shipX = rand.nextInt(10);
			
		}
			cellStatuses = 0;
	}
	
	/**
	 * Increases the number of parts of a ship sunk by 1
	 */
	public void increaseCellStatuses() {
		cellStatuses+=1;
		
	}
	
//-----------------------ACCESSOR AND MUTATOR METHODS------------------------	

	/**
	 * @return the shipCode
	 */
	public String getShipCode() {
		return shipCode;
	}
	/**
	 * @param shipCode the shipCode to set
	 */
	public void setShipCode(String shipCode) {
		this.shipCode = shipCode;
	}
	
	
	/**
	 * @return the shipName
	 */
	public String getShipName() {
		return shipName;
	}


	/**
	 * @param shipName the shipName to set
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}


	/**
	 * @return the shipSize
	 */
	public int getShipSize() {
		return shipSize;
	}


	/**
	 * @param shipSize the shipSize to set
	 */
	public void setShipSize(int shipSize) {
		this.shipSize = shipSize;
	}


	/**
	 * @return the shipX
	 */
	public int getShipX() {
		return shipX;
	}


	/**
	 * @param shipX the shipX to set
	 */
	public void setShipX(int shipX) {
		this.shipX = shipX;
		
	}
	/**
	 * @return the shipY
	 */
	public int getShipY() {
		return shipY;
	}


	/**
	 * @param shipY the shipY to set
	 */
	public void setShipY(int shipY) {
		this.shipY = shipY;
		
	}


	/**
	 * @return the shipDirection
	 */
	public int getShipDirection() {
		return shipDirection;
	}


	/**
	 * @param shipDirection the shipDirection to set
	 */
	public void setShipDirection(int shipDirection) {
		this.shipDirection = shipDirection;
	}


	/**
	 * @return the cellStatuses
	 */
	public int getCellStatuses() {
		return cellStatuses;
	}


	public void setCellStatuses(int status) {
		// TODO Auto-generated method stub
		
	}


	

}

/**
 * 
 */

/**
 * @author jodielaurenson
 *
 */
public class Player {

	boolean hasWon;
	int shotsFired;
	
	/**
	 * 
	 */
	public Player() {
		shotsFired = 0;
		hasWon=false;
	}
	
	
	/**
	 * increases the shots fired by 1 after every shot
	 */
	public void incrementShotsFired()
	{
		shotsFired++;
		System.out.println("Shots fired: " + shotsFired);
	}
	
	/**
	 * decreases shots fired by 1 if invalid position entered
	 */
	public void decreaseShotsFired()
	{
		shotsFired--;
		System.out.println("Shots fired: " + shotsFired);
	}
	
	//-----------------------ACCESSOR AND MUTATOR METHODS------------------------
	
	
	/**
	 * @return the shotsFired
	 */
	public int getShotsFired() {
		return shotsFired;
	}
	/**
	 * @param shotsFired the shotsFired to set
	 */
	public void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}
	/**
	 * @return the hasWon
	 */
	public boolean isHasWon() {
		return hasWon;
	}
	/**
	 * @param hasWon the hasWon to set
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

}

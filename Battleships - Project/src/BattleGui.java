
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * I created the class and attempted the optional extra but I did not know how to 
 * run it without making most of my methods static which involved me changing a 
 * lot of my code. I got it to almost work but I decided to try the make the game 
 * multi player instead
 * 
 * @author Jodie
 * 
 */
public class BattleGui implements ActionListener 
{
	Grid myGrid = new Grid();
    Player mySelf = new Player();
    Ship[] ships = new Ship[10];
	
    // Default filename to use for saving and loading files
    // Possible improvement: replace with a FileChooser
    private final static String DEFAULT_FILENAME = "battlegui.txt";
    private int GRID_SIZE = 10;
    private JButton [] buttonArray; 
  
    /**
     * main menu
     * @return menu object
     */
    public JMenuBar createMenu() 
    {
        JMenuBar menuBar  = new JMenuBar();;
        JMenu menu = new JMenu("Battle Menu");
        JMenuItem menuItem;
       
        menuBar.add(menu);

        // A group of JMenuItems. You can create other menu items here if desired
        menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Save Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        return menuBar;
    }
 
    /**
     * Works out which button was clicked on
     * @return the grid
     */
    public Container createContentPane() 
    {
        int numButtons = GRID_SIZE * GRID_SIZE;
        JPanel grid = new JPanel(new GridLayout(GRID_SIZE,GRID_SIZE));
        buttonArray = new JButton[numButtons];
        
        for (int i=0; i<numButtons; i++)
        {
            buttonArray[i] = new JButton(" ");

			// This label is used to identify which button was clicked in the action listener
            buttonArray[i].setActionCommand("" + i); // String "0", "1" etc.
            buttonArray[i].addActionListener(this);
            grid.add(buttonArray[i]);
        }
        return grid;
    }
    
    /**
     * This method handles events from the Menu and the board.
     */
    public void actionPerformed(ActionEvent e) 
    {
        String classname = getClassName(e.getSource());
        JComponent component = (JComponent)(e.getSource());
        

		if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();
            
            // Determine which menu option was chosen
            if (menutext.equals("Load Game"))
            {
                
            	
				LoadGame();
            }
            else if (menutext.equals("Save Game"))
            {
                
            	SaveGame(ships);
            	
            	 
            }
            else if (menutext.equals("New Game"))
            {
                
            	NewGame();
            	
                
            	
            }
        }
        // Handle the event from the user clicking on a command button
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            int row = bnum / GRID_SIZE;
            int col = bnum % GRID_SIZE;
                   
            /* BATTLEGUI    Add your code here to handle user clicking on the grid ***********/
          
            Grid.displayShips();
            Grid.attackGUI(col, row, ships);
			Grid.incrementShotsFired();
        }  
    }
    
    /**
     *  Returns the class name
     */
    protected String getClassName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /**
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() 
    {
        // Create and set up the window.
        JFrame frame = new JFrame("Battleships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        BattleGui battlegui = new BattleGui();
        frame.setJMenuBar(battlegui.createMenu());
        frame.setContentPane(battlegui.createContentPane());

        // Display the window, setting the size
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    /**
     * Sets a Gui grid square at row, col to display a character
     * @param row x pos clicked on
     * @param col y pos clicked on
     * @param c character to display on square
     * @return returns true if valid
     */
    public boolean setGuiSquare(int row, int col, char c)
    {
        int bnum = row * GRID_SIZE + col;
        if (bnum >= (GRID_SIZE*GRID_SIZE))
        {
            return false;
        }
        else
        {
            buttonArray[bnum].setText(Character.toString(c));
        }
        return true;
    }
    
    /**
     * This is a standard main function for a Java GUI
     */
    /*public static void main(String[] args) 
    {
    	
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
        });
    }*/
    
    
    //************************************************************************
    //*** BATTLEGUI: Modify the methods below to respond to Menu and Mouse click events
     
    /**
     * This method is called from the Menu event: New Game.
     * 
     */
    public void NewGame()
    {
    	Grid myGrid = new Grid();
         System.out.println("New game selected");
         myGrid.placeShips();
     	 myGrid.displayShips();
    }
    
    /**
     * This method is called from the Menu event: Load Game.
     * 
     */
    public void LoadGame()
    {
          System.out.println("Load game selected");
          Grid myGrid = new Grid();
          
		  myGrid.loadGame();
    }
    
    
    /**
     * This method is called from the Menu event: Save Game.
     * @param ships array of ship objects
     */
    public void SaveGame(Ship[] ships)
    {
          System.out.println("Save game selected");
          
      	  Grid.saveGame(ships);
    }
    
    /**
     * This method is called from the Mouse Click event.
     * @param row position clicked on
     * @param col position clicked on
     */
    public void fireShot(int row, int col)
    {
          System.out.println("Fire shot selected: at (" + row + ", " + col + ")");
    }
}


    

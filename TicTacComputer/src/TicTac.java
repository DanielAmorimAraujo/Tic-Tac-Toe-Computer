/*
Name: Daniel Araujo
Date: 10 Aug 18
Title: Tic-Tac-Toe
Purpose: to create a GUI that simulates a Tic Tac Toe game using 2-d arrays to store the values (X, O, or blank) of the board
* keeps track of score and outputs to the users messages guiding them along the user of the application
*/

import java.awt.*; //imports classes needed for FlowLayout
import javax.swing.*; //imports classes needed for the GUI elements

public class TicTac extends JFrame {
    
    TicTacEvent tictac = new TicTacEvent(this); //joins the TicTac and TicTacEvent program so they work together
    
    JPanel pnlRow = new JPanel(); //declares outline box pnlRow
    JButton[ ] [ ] btnBoxes = new JButton [3][3]; //declares a 2-d button array that is 3 by 3
    JButton btnPlay = new JButton ("Play"); //declares JButton btnPlay and sets its text as "Play"
    JButton btnReset = new JButton ("Reset"); //declares JButton btnReset and sets its text as "Reset"
    JButton btnComputer = new JButton ("Computer");
    JButton btnComputerFirst = new JButton ("Computer Moves First");
    JTextField txtBlank1 = new JTextField(); //declares JTextField txtBlank1 - used to output the number of wins for X and O
    JTextField txtBlank2 = new JTextField(); //declares JTextField txtBlank2 - used to output the number of ties
    ImageIcon back = new javax.swing.ImageIcon(getClass().getResource("/imageresources/Background Icon.jpg")); //declares ImageIcon variable back and stores the Background Icon.jpg icon - image address: https://d1bw6y4vdxj982.cloudfront.net/general-images/Materials/_materialThumbnail/Angelim-Amargoso-Wood-Icon-400x400.jpg

    /**
     * game board components must be declares (btnPlay, btnReset, txtBlank1, etc.)
     * creates the game board
     */
    public TicTac() {
        super ("Tic Tac Toe"); //creates the outer frame and sets its title as "Tic Tac Toe"
        setSize (600,800); //sets the size of the outer frame as 500 pixels horizontally and 600 pixels vertically
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the program to quit running when the window is closed
        FlowLayout layout = new FlowLayout(); //declares FlowLayout layout - arranges components left to right and horizontally cenetered
        setLayout(layout); //sets the layout of the application as the FlowLayout layout
        int intName = 0; //declares integer variable intName and sets it equal to 0 - will be used to number to buttons in the btnBoxes array

        GridLayout layout1 = new GridLayout(5, 3, 10, 10); //declares GridLayout layout1 as 5 rows by 3 columns with 10 pixels between the components horizontally and vertically
        pnlRow.setLayout(layout1); //sets the layout of pnlRow as the GridLayout layout1 - components will be equally sized and arranged in a rectange grid
        //for loop to add buttons to the btnBoxes array - will end with a 3 by 3 grid of 9 JButtons numbered 1-9 horizontally
        for (int x=0; x<=2; x++) { //runs through the rows of the btnBoxes array
            for (int y=0; y<=2; y++) { //runs through the columns of the btnBoxes array
                intName++; //increases the value of intName by 1 - used to number to buttons in order
                btnBoxes[x][y] = new JButton(Integer.toString(intName)); //declares a JButton at the specific indexes and sets its text to the intName variable
                btnBoxes[x][y].setIcon(back); //sets the icon of the buttons at the specific index as the icon stored as the back variable
                pnlRow.add(btnBoxes[x][y]); //adds the button to the game board (pnlRow)
            }
        }
        
        txtBlank1.setEditable(false); //sets the txtBlank1 text field as uneditable to the user - ensure user does not change score
        txtBlank2.setEditable(false); //sets the txtBlank2 text field as uneditable to the user - ensure user does not change score
        
        pnlRow.add(txtBlank1); //adds the txtBlank1 text field to the game board (pnlRow)
        pnlRow.add(btnPlay); //adds the Play (btnPlay) button to the game board (pnlRow)
        pnlRow.add(txtBlank2); //adds the txtBlank 2 text field to the game board (pnlRow)
        pnlRow.add(btnComputer);
        pnlRow.add(btnReset); //adds the Reset (btnReset) to the game board (pnlRow)
        pnlRow.add(btnComputerFirst);
        add (pnlRow); //adds the game board (pnlRow) to the FlowLayout

        btnPlay.addActionListener(tictac); //makes the TicTacEvent file ready to handle the Play (btnPlay) button being clicked
        btnReset.addActionListener(tictac); //makes the TicTacEvent file ready to handle the Reset (btnReset) button being clicked
        btnComputer.addActionListener(tictac);
        btnComputerFirst.addActionListener(tictac);
        //for loop that runs through the buttons in the btnBoxes array and makes the TicTacEvent file ready to handle if the buttons are clicked
        for (int x=0; x<=2; x++) //runs through the rows of the btnBoxes array
            for (int y=0; y<=2; y++) //runs through the columns of the btnBoxes array
                btnBoxes[x][y].addActionListener(tictac); //makes the TicTacEvent file ready to handle the button at [x][y] being clicked
        
        setVisible(true); //sets the FlowLayout as visibile
    }
    
    public static void main(String[] arguments){
        TicTac frame = new TicTac(); //runs the screen layout class
    }
}
import javax.swing.*; //imports classes needed for the GUI elements
import java.awt.event.*; //imports classes needed for ItemListener, ActionListener, and ActionEvent

/**
 * creates class that responds to mouse inputs by using ItemListener and ActionListener
 */
public class TicTacEvent implements ItemListener, ActionListener, Runnable {
    TicTac gui; //associates the gameboard with the event so that the gameboard components (e.g. JButtons) can be accessed
    ImageIcon a = new ImageIcon("ICS4UE_U2A5A1_DanielAraujo - Tic-Tac-Toe (X Icon).png"); //declares ImageIcon variable a and stores the ICS4UE_U2A5A1_DanielAraujo - Tic-Tac-Toe (X Icon).png icon - image address: https://image.flaticon.com/icons/png/128/75/75519.png
    ImageIcon b = new ImageIcon("ICS4UE_U2A5A1_DanielAraujo - Tic-Tac-Toe (O Icon).png"); //declares ImageIcon variable b and stores the ICS4UE_U2A5A1_DanielAraujo - Tic-Tac-Toe (O Icon).png icon - image address: http://www.i2clipart.com/cliparts/a/d/a/e/128045adaec2334c9b935218efbe25ea2cd32d.png
    int intClicks = 1; //declares integer variable intClicks and sets it equal to 0 - counts how many JButtons have been selected in a single game
    boolean blnWin = false; //declares boolean variable blnWin and sets it as false - used to determine whether a player one or not
    boolean blnComp = false;
    boolean blnCompFirst = false;
    int intCompFirst = 0;
    int[][] intCheck = new int[3][3]; //declares 2-d integer array that's 3 by 3 to store values corresponding to Xs, Os, or blanks from the game board
    int[] intRowSum = new int[3]; //declares integer array that stores the sum of the rows of the intCheck array
    int[] intColSum = new int[3]; //declares integer array that stores the sum of the columns of the intCheck array
    
    int intX = 0, intO = 0, intTie = 0; //declares integer variables intX, intO, and intTie and sets them all equal to 0 - stores the number of times X wins, times O wins, and ties, respectively
    boolean blnStarted = false; //declares boolean variable blnStarted and sets it as false - stores whether the game has started or not
    boolean blnFinished = false; //declares boolean variable blnFinished and sets it as false - stores whether the game has finished or not (before it has been reseted)

    /**
     * associates the TicTacEvent and TicTac files together
     */
    public TicTacEvent (TicTac in) {
        gui = in; //sets gui equal to in
        //sets all the values in the intCheck array as 0
        for (int row=0; row<=2; row++) //runs through all the rows (indexes 0-2)
           for (int col=0; col<=2; col++) //runs through all the columns (index 0-2)
               intCheck[row][col]=0; //sets the value of the intCheck array at the specific indexes as 0
    }

    /**
     * a button on the game board is pressed
     * executes the code for the button
     * @param event - condition in which a button is pressed
     */
    public void actionPerformed (ActionEvent event) {
        String strCommand = event.getActionCommand(); //declares string variable strCommand and sets it equal to the button name of the button that was clicked
        
        //if statements run the code for the specific button - based on the button's name
        if (strCommand.equals("Play")) //code that executes if the Play button is pressed
            startPlaying(); //calls on the startPlaying() method
        if (strCommand.equals("Reset")) //code that executes if the Reset button is pressed
            reset(); //calls on the reset() method
        if (strCommand.equals("Computer")) {
            blnComp = true;
        }
        if (strCommand.equals("Computer Moves First")) {
            intClicks = 0;
            blnCompFirst = true;
            computerFirst();
        }
        if (strCommand.equals("1")) //code that executes if the 1 button on the Tic Tac Toe board is pressed
            button(0,0); //calls on the button() method and passes 0,0 for the parameter
        if (strCommand.equals("2")) //code that executes if the 2 button on the Tic Tac Toe board is pressed
            button(0,1); //calls on the button() method and passes 0,1 for the parameter
        if (strCommand.equals("3")) //code that executes if the 3 button on the Tic Tac Toe board is pressed
            button(0,2); //calls on the button() method and passes 0,2 for the parameter
        if (strCommand.equals("4")) //code that executes if the 4 button on the Tic Tac Toe board is pressed
            button(1,0); //calls on the button() method and passes 1,0 for the parameter
        if (strCommand.equals("5")) //code that executes if the 5 button on the Tic Tac Toe board is pressed
            button(1,1); //calls on the button() method and passes 1,1 for the parameter
        if (strCommand.equals("6")) //code that executes if the 6 button on the Tic Tac Toe board is pressed
            button(1,2); //calls on the button() method and passes 1,2 for the parameter
        if (strCommand.equals("7")) //code that executes if the 7 button on the Tic Tac Toe board is pressed
            button(2,0); //calls on the button() method and passes 2,0 for the parameter
        if (strCommand.equals("8")) //code that executes if the 8 button on the Tic Tac Toe board is pressed
            button(2,1); //calls on the button() method and passes 2,1 for the parameter
        if (strCommand.equals("9")) //code that executes if the 9 button on the Tic Tac Toe board is pressed
            button(2,2); //calls on the button() method and passes 2,2 for the parameter
    }

    /**
     * Play button must be pressed
     * executes the code required for the game to be able to be played
     */
    void startPlaying() {
        //playing = new Thread(this);
        gui.btnPlay.setEnabled(false); //disables the Play (btnPlay) button - user cannot start the game after it has started
        blnStarted = true; //sets the boolean variable blnStarted as true - program knows the game started
    }    

    /**
     * Reset button must be pressed
     * executes the code required to reset the game
     */
    void reset() {
        gui.btnPlay.setEnabled(true); //enables the Play (btnPlay) button - user can play the game again
        blnStarted = false; //sets the boolean variable blnStarted as false - program knows the game has not been started
        blnFinished = false; //sets the boolean variable blnFinished as false - program knows the game has not finished
        //for loop that runs through the buttons in the btnBoxes array and sets their icon
        for (int x=0; x<=2; x++) //runs through the rows of the btnBoxes array
            for (int y=0; y<=2; y++) //runs through the columns of the btnBoxes array
                gui.btnBoxes[x][y].setIcon(gui.back); //sets the icon of the buttons at the specific index as the icon stored as the back variable
        intClicks = 1; //sets the value of intClicks as 0 - ensures clicks of the previous game does not carry forward
        blnWin = false; //sets the value of blnWin as false - ensures the win condition of the previous game does not carry forward
        intCompFirst = 0;
        blnComp = false;
        blnCompFirst = false;
        intCompFirst = 0;
        //for loop that runs through the values of the intCheck array and sets them all to 0 - ensures that values from the previous game does not carry forward
        for (int i = 0; i < 3; i++) //runs through the rows of the intCheck array
            for (int j = 0; j < 3; j++) //runs through the columns of the intCheck array
                intCheck[i][j] = 0; //sets the value of the intCheck array at the specific index as 0
    }

    /**
     * a button from the Tic Tac Toe board must be pressed
     * sets the buttons icon as the respective player's icon (X or O)
     * informs the user if their move is not allowed (game hasn't started or been reseted or the button has been selected already) using JOptionPanes
     * @param intRow - integer variable that represents the button's row index in the btnBoxes array
     * @param intColumn  - integer variable that represents the button's column index in the btnBoxes array
     */
    void button(int intRow, int intColumn) {
        if (blnStarted) { //if statement that executes if the game has started (boolean variable blnStarted = true)
            if (intCheck[intRow][intColumn] == 0) { //if statent that executes if the button selected has not been selected already (if it has, it's value would not be 0)
                if (intClicks % 2 == 1) { //if statement that executes if the intClicks button is odd - used to switch between Xs and Os
                    gui.btnBoxes[intRow][intColumn].setIcon(a); //sets the selected buttons icon as the icon stored as a (an X)
                    intCheck[intRow][intColumn] = 1; //sets the value in the intCheck array that corresponds to the button (same index) as 1 - represents an X
                } else { //else statement that executes if the intClicks button is even (not odd) - used to switch between Xs and Os
                    gui.btnBoxes[intRow][intColumn].setIcon(b); //sets the selected buttons icon as the icon stored as b (an O)
                    intCheck[intRow][intColumn] = 2; //sets the value in the intCheck array that corresponds to the button (same index) as 2 - represents an O
                }
                intClicks++; //increases the intClicks value by 1 - keeps track of the number of clicks
                winner(); //calls the winner() method to see if there is a winner
            }
            else //else statement that exectues if the button has been selected already (its corresponding index in the intCheck array != 0)
                JOptionPane.showMessageDialog(null, "This square has already been selected. Please select a different square."); //outputs a pop-up box informing the user that they need to select a different button
        }
        else if (blnFinished) //else if statement executes if the game has not been started but it has also no been reseted (blnFinished = true)
            JOptionPane.showMessageDialog(null, "The game has already finished. Press Reset to play again."); //outputs a pop-up box informing the user that they need to reset the game to play again
        else //else statement executes if the game has not been started but has been reseted
            JOptionPane.showMessageDialog(null, "Press Play to start the game."); //outputs a pop-up box informing the user that they need to press the Play button to start the game
    }

    /**
     * a button from the btnBoxes array must be selected
     * checks to see if there is 3 in a row
     * outputs the result (X wins, O wins, or a tie)
     * if their is no result, does nothing
     */
    void winner() {
        //for loop that runs through each row and checks to see if all the entries in the row are either X or if all the entries are O
        for (int x = 0; x <= 2; x++) //runs through te rows of the intCheck array
            if ((intCheck[x][0]==intCheck[x][1])&&(intCheck[x][0]==intCheck[x][2])) { //if statement that executes if all values of a single row (index x) of the intCheck array are equal
                if (intCheck[x][0]==1) { //if statement that executes if the first value of the row is an X (equal to 1)
                    JOptionPane.showMessageDialog(null, "X is the winner"); //outputs a pop-up box informing the user that X is the winner
                    blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                    intX++; //increasing the value of intX by 1 - keeps track of how many wins X has
                    output(); //calls on the output() method
                } else if (intCheck[x][0]==2){ //if statement that executes if the first value of the row is an O (equal to 2)
                    JOptionPane.showMessageDialog(null, "O is the winner"); //outputs a pop-up box informing the user that O is the winner
                    blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                    intO++; //increasing the value of intO by 1 - keeps track of how many wins O has
                    output(); //calls on the output() method
                }
            }
        //for loop that runs through each column and checks to see if all the entries in the column are either X or if all the entries are O
        for (int x = 0; x <= 2; x++) //runs through te columns of the intCheck array
            if ((intCheck[0][x]==intCheck[1][x])&&(intCheck[0][x]==intCheck[2][x])) { //if statement that executes if all values of a single column (index x) of the intCheck array are equal
                if (intCheck[0][x]==1) { //if statement that executes if the first value of the column is an X (equal to 1)
                    JOptionPane.showMessageDialog(null, "X is the winner"); //outputs a pop-up box informing the user that X is the winner
                    blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                    intX++; //increasing the value of intX by 1 - keeps track of how many wins X has
                    output(); //calls on the output() method
                } else if (intCheck[0][x]==2) { //if statement that executes if the first value of the column is an O (equal to 2)
                    JOptionPane.showMessageDialog(null, "O is the winner"); //outputs a pop-up box informing the user that O is the winner
                    blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                    intO++; //increasing the value of intO by 1 - keeps track of how many wins O has
                    output(); //calls on the output() method
                }
            }
        //if satement that executes if the values of either of the diagonals of the intCheck array are equal
        if (((intCheck[0][0]==intCheck[1][1])&&(intCheck[0][0]==intCheck[2][2]))||
                ((intCheck[2][0]==intCheck[1][1])&&(intCheck[1][1]==intCheck[0][2])))
            if (intCheck[1][1]==1) { //if statement that executes if the middle value of the diagonal is an X (equal to 1)
                JOptionPane.showMessageDialog(null, "X is the winner"); //outputs a pop-up box informing the user that X is the winner
                blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                intX++; //increasing the value of intX by 1 - keeps track of how many wins X has
                output(); //calls on the output() method
            } else if (intCheck[1][1]==2) { //if statement that executes if the first value of the column is an O (equal to 2)
                JOptionPane.showMessageDialog(null, "O is the winner"); //outputs a pop-up box informing the user that O is the winner
                blnWin = true; //setting the value of blnWin as true - used later to distinguish between a win or a tie
                intO++; //increasing the value of intO by 1 - keeps track of how many wins O has
                output(); //calls on the output() method
            }
        //if statement that executes if all the buttons have been clicked (intClicks == 9) and there is still no winner (blnWin = true) - represents a tie
        if ((intClicks == 10 && !blnWin && !blnCompFirst) || (intClicks == 9 && !blnWin && blnCompFirst)) {
            JOptionPane.showMessageDialog(null, "The game is a tie"); //outputs a pop-up box informing the user that it is a tie
            intTie++; //increasing the value  of intTie by 1 - keeps track of how many ties there has been
            output(); //calls on the output() method
        }
        System.out.println(intClicks);
        
        if (intClicks % 2 == 0 && blnComp)
            computer();
        if (intClicks % 2 == 0 && blnCompFirst) {
            computerFirst();
        }
    }

    /**
     * either a win or a tie must occur for the output method to be called
     * updates Xs and Os score along with the number of ties
     * ends the game
     */
    void output() {
        blnStarted = false; //sets the blnStarted variable as false so the program knows the game has not been started again - ensures user selects the Play (btnPlay) button before playing
        blnFinished = true; //sets the blnFinished variable as true so the program knows the game has ended - ensures the user selects the Reset (btnReset) button to reset the game
        gui.txtBlank1.setText(" X wins: " + intX + "         O wins: " + intO); //outputs the text to the txtBlank1 text field with Xs and Os score (intX and intO, respectively)
        gui.txtBlank2.setText(" Ties: " + intTie); //outputs the text to the txtBlank2 text field with the number of ties (intTie)
    }
    
    void computer() {
        int intRun = computerWin();
        if (intRun !=1) {
            if (intCheck[0][0] == 1 || intCheck[0][2] == 1 || intCheck[2][2] == 1 || intCheck[2][0] == 1) {
                if (intCheck[1][1] == 0)
                    button(1,1);
                    
                else if ((intCompFirst == 0 || intCompFirst == 4) && (intCheck[0][1] == 0 || intCheck[1][0] == 0 || intCheck [1][2] == 0 || intCheck [2][1] == 0 )) {
                    intCompFirst = 4;
                    int intC = 0;
                    for (int i = 0; i < 3; i += 2)
                        for (int j = 0; j < 3; j += 2)
                            if (intCheck[i][j] == 1)
                                intC = j;
                    for (int i = 0; i < 3; i++) {
                        if (intCheck[i][1] == 1)
                            button(i,intC);
                        if (intCheck[1][i] == 1)
                            button(intC,i);
                    }
                }
                else if ((intCompFirst == 0 || intCompFirst == 5) && (intCheck[0][0] == 1 || intCheck[0][2] == 1 || intCheck[2][2] == 1 || intCheck[2][0] == 1))
                    intCompFirst = 5;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (intCheck[i][j] == 0) {
                                button(i,j);
                                System.out.println("Now");
                                intRun = 1;
                            }
                            if (intRun == 1)
                                break;
                        }
                        if (intRun == 1)
                            break;
                    }
            } 
        }
        
        
    }
        
    void computerFirst() {
        int intRun = computerWin();
        if (intRun != 1) {
        if (intCheck[0][0] == 0) {
            button(0,0);
        }
        else if (intCheck[1][1] == 1) {
            if (intCheck[2][2] == 0) {
                button(2,2);
            }
        }
        
        choose(intRun);
        
        if ((intCompFirst == 0 || intCompFirst == 2) && (intCheck[0][2] == 1 || intCheck[2][2] == 1 || intCheck[2][0] == 1)) {
            corner();
            intCompFirst = 2; }
        else if ((intCompFirst == 0 || intCompFirst == 3) && (intCheck[0][1] == 1 || intCheck[1][0] == 1 || intCheck[1][2] == 1 || intCheck[2][1] == 1)) {
                edge();
                intCompFirst = 3;
        }
    }
    }
    
    void choose(int intRun) {
        if (intRun == 0) {}
        if (intRun == 2)
            corner();
        if (intRun == 3)
            edge();
    
    }
    
    void corner() {
            {
            intCompFirst = 2;
            if (intCheck[2][2] == 0)
                button(2,2);
            else if (intCheck[0][2] == 0)
                button (0,2);
            else if (intCheck[2][0] == 0)
                button(2,0);
            else
                lastSquares();
        }
    
    }
    
    void edge() {
    if (intCheck[1][1] == 0)
                button(1,1);
            else {
                if (intCheck[2][0] == 0 && intCheck[1][0] == 0)
                    button(2,0);
                else if (intCheck[0][2] == 0)
                    button (0,2);
                else
                    lastSquares();
            }
        }
    
    int computerWin() {
        int intRun = 0;
        int intCounter = 0;
        //O horizontal
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (intCheck[i][j] == 2) {
                    intCounter++;
                }
            }
            if (intCounter == 2) {
                for (int j = 0; j < 3; j++)
                    if (intCheck[i][j] == 0) {
                        button(i,j);
                        intRun = 1;
                    }
            }
            intCounter = 0;
        }
        //O vertical
        if (intRun != 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (intCheck[j][i] == 2) {
                        intCounter++;
                    }
                }
                if (intCounter == 2) {
                    for (int j = 0; j < 3; j++)
                        if (intCheck[j][i] == 0) {
                            button(j,i);
                            intRun = 1;
                        }
                }
                intCounter = 0;
            }
        }
        //O diagonal down
        if (intRun != 1) {
            for (int i = 0; i < 3; i++) {
                if (intCheck[i][i] == 2) {
                    intCounter++;
                }
                if (intCounter == 2) {
                    for (int j = 0; j < 3; j++) {
                        if (intCheck[j][j] == 0) {
                            button(j,j);
                            intRun = 1;
                        }
                    }
                }
            }
            intCounter = 0;
        }
        //O diagonal up
        if (intRun != 1) {
            if (intCheck[0][2] == 2) intCounter++;
            if (intCheck[1][1] == 2) intCounter++;
            if (intCheck[2][0] == 2) intCounter++;
            if (intCounter == 2) {
                if (intCheck[0][2] == 0) {
                    button(0,2);
                    intRun = 1;
                } else if (intCheck[1][1] == 0) {
                    button(1,1);
                    intRun = 1;
                } else if (intCheck[2][0] == 0) {
                    button(2,0);
                    intRun = 1;
                }
            }
            intCounter = 0;
        }
        //X horizontal
        if (intRun != 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (intCheck[i][j] == 1) {
                        intCounter++;
                    }
                }
                if (intCounter == 2) {
                    for (int j = 0; j < 3; j++)
                        if (intCheck[i][j] == 0) {
                            button(i,j);
                            intRun = 1;
                        }
                }
                intCounter = 0;
            }
        }
        //X vertical
        if (intRun != 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (intCheck[j][i] == 1) {
                    intCounter++;
                    }
                }
                if (intCounter == 2) {
                    for (int j = 0; j < 3; j++)
                        if (intCheck[j][i] == 0) {
                            button(j,i);
                            intRun = 1;
                        }
                }
            intCounter = 0;
            }
        }
        //X diagonal down
        if (intRun != 1) {
            for (int i = 0; i < 3; i++) {
                if (intCheck[i][i] == 1) {
                    intCounter++;
                }
            if (intCounter == 2) {
                for (int j = 0; j < 3; j ++) {
                if (intCheck[j][j] == 0) {
                    button(i,i);
                    intRun = 1;
                }
                }
            }
            }
            intCounter = 0;
        }
        //X diagonal up
        if (intRun != 1) {
            if (intCheck[0][2] == 1) intCounter++;
            if (intCheck[1][1] == 1) intCounter++;
            if (intCheck[2][0] == 1) intCounter++;
            if (intCounter == 2) {
                if (intCheck[0][2] == 0) {
                    button(0,2);
                    intRun = 1;
                } else if (intCheck[1][1] == 0) {
                    button(1,1);
                    intRun = 1;
                } else if (intCheck[2][0] == 0) {
                    button(2,0);
                    intRun = 1;
                }
            }
            intCounter = 0;
        }    
    return intRun;
    }
    
    void lastSquares() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (intCheck[i][j] == 0)
                    button(i,j);
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

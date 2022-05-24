/*
 * Alert.java
 * @Author: Mike Wu, Student ID. 100342872
 * @Date: Mar 15, 2022
 * an object class inherited from the Window class,
 * used to create an Alert obj where corresponding alert info is printed
 */

package DocHandler;

public class Alert extends Window
{
    //instance variables
    public static int numberAlert = 0; // static alert number added by one each time a new Alert is created
    private String name;
    private char[][] window;
    private int openRow;
    private int openCol;
    private int row;
    private int col;

    //default constructor
    public Alert()
    {
        super();
        numberAlert++;
        this.openRow = Window.currentOpenRow;
        this.openCol = Window.currentOpenCol;
        this.row = ROW;
        this.col = Window.WIDTH + COL;
        this.name = "Alert" + numberAlert;
        this.getNewWindow();
        this.getErrorMessage();
    }

    //constructor with a parameter of a String to get input
    public Alert(String input)
    {
        super();
        numberAlert++;
        this.openRow = Window.currentOpenRow;
        this.openCol = Window.currentOpenCol;
        this.row = ROW;
        this.col = Window.WIDTH + COL;
        this.name = "Alert" + numberAlert;
        this.getNewWindow();
        this.getMessage(input);
    }

    //some get set methods
    public String getName()
    {
        return this.name;
    }

    public char[][] getWindow()
    {
        return this.window;
    }

    public int getOpenRow()
    {
        return this.openRow;
    }

    public int getOpenCol()
    {
        return this.openCol;
    }

    public int getRow()
    {
        return this.row;
    }
    
    public int getCol()
    {
        return this.col;
    }

    /**
     * get a new Alert window
     * @param: none
     * @return: none
     */
    public void getNewWindow()
    {
        this.window = new char[this.row][this.col];
        for (int i = 0; i < this.col; i++)
        {
            for (int j = 0; j < this.row; j++)
            {
                this.window[j][i] = ' ';
            }
        }
        for (int i = 0; i < this.col; i++)
        {
            this.window[0][i] = '*';
            this.window[this.row-1][i] = '+';
        }
        for (int i = 1; i < this.row-1; i++)
        {
            this.window[i][0] = '+';
            this.window[i][this.col-1] = '+';
        }

        //print the tile of Alert to the window top
        char[] nameChar = this.name.toCharArray();
        for (int i = 0; i < nameChar.length; i++)
        {
            this.window[0][this.col/2 - nameChar.length / 2 + i] = nameChar[i];
        }
        this.window[0][this.col/2 - nameChar.length / 2 - 1] = ' ';
        this.window[0][this.col/2 - nameChar.length / 2 + nameChar.length] = ' ';
    }

    /**
     * takes in different input as parameter and print different alert messages based on the input
     * @param input: a String with different inputs from user which may cause alerts
     * @return: none
     */
    public void getMessage(String input)
    {
        //print "FILENAMES MUST INCLUDE .txt" alert
        if (input.contains("open") && !input.contains(".txt"))
        {
            char[] message = " FILENAMES MUST INCLUDE .txt".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "CLOSE DOCUMENTS BY NAME ONLY" alert
        else if (input.contains("closebyname"))
        {
            char[] message = " CLOSE DOCUMENTS BY NAME ONLY".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "DOCUMENT NOT FOUND" alert
        else if (input.contains("invalidfile"))
        {
            char[] message = " DOCUMENT NOT FOUND".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "ALERT CANNOT BE MOVED" alert
        else if (input.contains("movealert"))
        {
            char[] message = " ALERT CANNOT BE MOVED".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "ALERT CANNOT BE RESIZED" alert
        else if (input.contains("resizealert"))
        {
            char[] message = " ALERT CANNOT BE RESIZED".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "ALERT CANNOT BE OVERWRITTEN" alert
        else if (input.contains("writealert"))
        {
            char[] message = " ALERT CANNOT BE OVERWRITTEN".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
        //print "ALERT CANNOT BE SAVED" alert
        else if (input.contains("saveAlert"))
        {
            char[] message = " ALERT CANNOT BE SAVED".toCharArray();
            for (int i = 1; i < message.length + 1; i++)
            {
                this.window[2][i] = message[i - 1];
            }
        }
    }

    /**
     * print "INVALID COMMAND" alert
     * @param: none
     * @return: none
     */
    public void getErrorMessage()
    {
        char[] message = " INVALID COMMAND".toCharArray();
        for (int i = 1; i < message.length + 1; i++)
        {
            this.window[2][i] = message[i - 1];
        }
    }
}

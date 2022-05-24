/*
 * TextBox.java
 * @Author: Mike Wu, Student ID. 100342872
 * @Date: Mar 15, 2022
 * an object class inherited from the Window class,
 * used to create a textbox obj where it can store information and can be opened up from a .txt file
 */

package DocHandler;

import java.io.*;

public class TextBox extends Window
{
    //instance variables
    public static int numberWin = 0; // static window number added by one each time a new Untitled window is created
    private String name;
    private char[][] window;
    private int openRow;
    private int openCol;
    private int row;
    private int col;
    private String contents; // contents as a String to be stored in the window

    //default contructor
    public TextBox()
    {
        super();
        numberWin++;
        this.openRow = Window.currentOpenRow;
        this.openCol = Window.currentOpenCol;
        this.row = Window.HEIGHT;
        this.col = Window.WIDTH;
        this.name = "Untitled" + numberWin;
        this.contents = "";
        this.getNewWindow();
    }

    //contructor with String input as a name of the textbox
    public TextBox(String n) throws IOException
    {
        super();
        this.openRow = Window.currentOpenRow;
        this.openCol = Window.currentOpenCol;
        this.row = Window.HEIGHT;
        this.col = Window.WIDTH;
        this.name = n;
        this.contents = "";
        this.getNewWindow();
    }

    //some get set methods
    public String getName()
    {
        return this.name;
    }

    public int getOpenRow()
    {
        return this.openRow;
    }

    public void setOpenRow(int oR)
    {
        this.openRow = oR;
    }

    public int getOpenCol()
    {
        return this.openCol;
    }

    public void setOpenCol(int oC)
    {
        this.openCol = oC;
    }

    public int getRow()
    {
        return this.row;
    }

    public void setRow(int r)
    {
        this.row = r;
    }
    
    public int getCol()
    {
        return this.col;
    }

    public void setCol(int c)
    {
        this.col = c;
    }

    public char[][] getWindow()
    {
        return this.window;
    }

    public void setWindow(char[][] w)
    {
        this.window = w;
    }

    public String getContents()
    {
        return this.contents;
    }
    
    public void setContents(String contents)
    {
        this.contents = contents;
    }

    /**
     * get a new textbox window
     * @param: none
     * @return: void
     */
    public void getNewWindow()
    {
        this.window = new char[this.row][this.col];
        for (int i = 0; i < this.col; i++)
        {
            for (int j = 0; j < this.row; j++)
            {
                this.window[j][i] = '\0';
            }
        }
        for (int i = 0; i < this.col; i++)
        {
            this.window[0][i] = '=';
            this.window[this.row-1][i] = '-';
        }
        for (int i = 1; i < this.row-1; i++)
        {
            this.window[i][0] = '[';
            this.window[i][this.col-1] = ']';
        }

        //print the tile of TextBox to the window top
        char[] nameChar = this.name.toCharArray();
        for (int i = 0; i < nameChar.length; i++)
        {
            this.window[0][this.col/2 - nameChar.length / 2 + i] = nameChar[i];
        }
        this.window[0][this.col/2 - nameChar.length / 2 - 1] = ' ';
        this.window[0][this.col/2 - nameChar.length / 2 + nameChar.length] = ' ';
    }

    /**
     * copy the content from a previous window to a new window
     * @param content: a char[] array with contents to be copied to the new window
     * @return: void
     */
    public void copyWindow(char[] content)
    {
        getNewWindow();
        int count = 0; // count the number of chars from content
        for (int i = 1; i < this.window.length - 1; i++)
        {
            for (int j = 1; j < this.window[0].length - 1; j++)
            {
                if (content.length - count > 0)
                {
                    if (content[count] == '|')
                    {
                        this.window[i][j] = '|';
                        i++; j = 0; // advance a new line
                        if (i == this.window.length - 1)
                            break; // make sure the content is not copied out of range of the screen
                        else
                            count++;
                    }
                    else
                    {
                        this.window[i][j] = content[count];
                        count++;
                    }
                }
            }
        }
    }
}

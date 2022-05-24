/*
 * Screen.java
 * @Author: Mike Wu, Student ID. 100342872
 * @Date: Mar 15, 2022
 * an object class to create a Screen object where all information stored in here from Window got printed
 */

package DocHandler;

public class Screen 
{
    public static final int SCREENWIDTH = 142; // width of screen
    public static final int SCREENHEIGHT = 82; // height of screen
    public static char[][] screen;

    //a default constructor to create a screen
    public Screen()
    {
        screen = new char[SCREENHEIGHT][SCREENWIDTH];
        getScreen();
    }

    /**
     * the method used to get an empty screen
     * @param: none
     * @return void
     */
    public void getScreen()
    {
        char[] title = "§ Octopus Document Handler".toCharArray();
        char[] bottom = ("§ >> ").toCharArray();
        for (int i = 0; i < SCREENWIDTH; i++)
        {
            for (int j = 0; j < SCREENHEIGHT; j++)
            {
                screen[j][i] = ' ';
            }
        }
        for (int i = 0; i < SCREENWIDTH - 1; i++)
        {
            if (i<title.length)
                screen[0][i] = title[i];
            else
                screen[0][i] = '-';

            if (i < bottom.length)
                screen[SCREENHEIGHT-1][i] = bottom[i];
            else
                screen[SCREENHEIGHT-1][i] = '_';

            screen[0][SCREENWIDTH - 1] = '+';
            screen[SCREENHEIGHT-1][SCREENWIDTH - 1] = '+';
        }
        for (int i = 1; i < SCREENHEIGHT-1; i++)
        {
            screen[i][0] = '^';
            screen[i][SCREENWIDTH-1] = '^';
        }
    }

    /**
     * the method used to convert Screen from 2D array to string which get printed out
     * @param: none
     * @return: a String to be printed out later
     */
    public String toString()
    {
        String rt = "";
        for (int i = 0; i < SCREENHEIGHT; i++)
        {
            for (int j = 0; j < SCREENWIDTH; j++)
            {
                rt += screen[i][j];
            }
            rt += '\n';
        }
        return rt;
    }
}

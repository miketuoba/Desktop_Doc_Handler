/*
 * Window.java
 * @Author: Mike Wu, Student ID. 100342872
 * @Date: Mar 15, 2022
 * an abstract object class of Window which has properties of a Window popping up on the screen
 * got inherited to subclasses: TextBox and Alert
 */

package DocHandler;

import java.io.*;
import java.util.*;

//abstract class to be inherited by subclasses
public abstract class Window
{
    //static constants, instance variables, and static variables
    public final static int WIDTH = 35; // width of window
    public final static int HEIGHT = 25; // height of window
    public final static int ROW = 5; // distance of open position to the previous one
    public final static int COL = 5; // distance of open position to the previous one
    public static int currentOpenRow; // current row open position
    public static int currentOpenCol; // current column open position
    private int openRow;
    private int openCol;
    private int row;
    private int col;
    private char[][] window;
    private boolean onTop; // whether window is active
    private String contents; // contents stored in the window
    public static ArrayList<Window> windowList = new ArrayList<Window>(); // arraylist to store different windows
    Screen screen = new Screen();

    //default constructor
    public Window()
    {
        this.window = new char[HEIGHT][WIDTH];
        this.onTop = true;
        currentOpenRow += ROW;
        currentOpenCol += COL;
        checkOpenPosition(); // reset window to upper left if out of screen
    }

    //some get set methods
    public boolean getOnTop()
    {
        return this.onTop;
    }

    public void setOpenRow(int oR)
    {
        this.openRow = oR;
    }

    public void setOpenCol(int oC)
    {
        this.openCol = oC;
    }

    public void setRow(int r)
    {
        this.row = r;
    }

    public void setCol(int c)
    {
        this.col = c;
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

    public void copyWindow(char[] content)
    {
        this.window = new char[HEIGHT][WIDTH];
    }

    /**
     * get contents from a .txt file and store them to a string to be printed later
     * @param fileName: the name of .txt file to be opened and read
     * @return: none
     */
    public void getContent(String fileName) throws IOException
    {
        File inFile = new File (fileName);
        Scanner sc = new Scanner (inFile);
        String content = "";
        while (sc.hasNextLine())
        {
            content += sc.nextLine(); // read each line and assign to String content
        }
        windowList.get(windowList.size()-1).write(content); // write the content to the window
        sc.close();
    }

    /**
     * make any selected window active
     * @param str: input get from user containing the name of the window (textbox or alert) to be set to active
     * @return: none
     */
    public void select(String str)
    {
        //make untitled textbox to be active
        if (str.contains("Untitled"))
        {
            int number = Integer.parseInt(str.substring(str.length()-1));
            for (Window w: windowList)
            {
                //put the active window to the last position in the arraylist
                if (w.getName().equals("Untitled" + number))
                {
                    Window win = w;
                    windowList.remove(w);
                    windowList.add(windowList.size(), win);
                    break;
                }
            }
        }
        //make .txt textbox to be active
        else if (str.contains(".txt"))
        {
            for (Window w: windowList)
            {
                //put the active window to the last position in the arraylist
                if (w.getName().equals(str.substring(7)))
                {
                    Window win = w;
                    windowList.remove(w);
                    windowList.add(windowList.size(), win);
                    break;
                }
            }
        }
        //make alert to be active
        else if (str.contains("Alert"))
        {
            int number = Integer.parseInt(str.substring(str.length()-1));
            for (Window w: windowList)
            {
                //put the active window to the last position in the arraylist
                if (w.getName().equals("Alert" + number))
                {
                    Window win = w;
                    windowList.remove(w);
                    windowList.add(windowList.size(), win);
                    break;
                }
            }
        }
        print();
    }

    /**
     * move the active window to a position given
     * @param str: input get from user containing the name of the window (only textbox) to be moved
     * @return: none
     */
    public void move(String str)
    {
        if (windowList.get(windowList.size()-1).getName().contains("Alert"))
        {
            windowList.add(new Alert("movealert"));
            windowList.get(windowList.size()-1).print();
        }
        else
        {
            String[] split = str.split("\\ ");
            int newOpenRow = Integer.parseInt(split[1]); // get new row position
            int newOpenCol = Integer.parseInt(split[2]); // get new col position

            for (Window w: windowList)
            {
                //select active window and set new positions
                if (w.getOnTop())
                {
                    w.setOpenRow(newOpenRow);
                    w.setOpenCol(newOpenCol);
                    break;
                }
            }
        }
            print();
    }

    /**
     * resize the active window to a new dimension
     * @param str: input get from user containing the name of the window (only textbox) to be resized
     * @return: none
     */
    public void resize(String str)
    {
        if (windowList.get(windowList.size()-1).getName().contains("Alert"))
        {
            windowList.add(new Alert("resizealert"));
            windowList.get(windowList.size()-1).print();
        }
        else
        {
            char[] content;
            String[] split = str.split("\\ ");
            int newRow = Integer.parseInt(split[1]); // get new row dimension
            int newCol = Integer.parseInt(split[2]); // get new col dimension
            for (Window w: windowList)
            {
                //resize the active window
                if (w.getOnTop())
                {
                    content = (w.getContents()).toCharArray(); // get the content from the window before resizing
                    w.setRow(newRow);
                    w.setCol(newCol);
                    w.copyWindow(content); // copy the content to the new resized window
                    break;
                }
            }
        }
        print();
    }

    /**
     * write the contents given from input (or from resizing or appending) to the active window
     * @param str: input get from user (or contents from resizing or appending) containing the contents to be written in the window
     * @return: none
     */
    public void write (String str)
    {
        if (windowList.get(windowList.size()-1).getName().contains("Alert"))
        {
            windowList.add(new Alert("writealert"));
            windowList.get(windowList.size()-1).print();
        }
        else
        {
            //put a new line char after the old contents
            if (!windowList.get(windowList.size()-1).getContents().equals(""))
                str = windowList.get(windowList.size()-1).getContents() + '|' + str;

            windowList.get(windowList.size()-1).setContents(str);
            char[] newContent = str.toCharArray();
            int newCount = 0; // count the number of chars from content
            char[][] newWindow = windowList.get(windowList.size()-1).getWindow();
                for (int i = 1; i < newWindow.length - 1; i++)
                {
                    for (int j = 1; j < newWindow[0].length - 1; j++)
                    {
                        if (newContent.length - newCount > 0)
                        {
                            if (newContent[newCount] == '|')
                            {
                                newWindow[i][j] = '|';
                                i++; j = 0; // advance a new line
                                if (i == this.window.length - 1)
                                    break; // make sure the content is not copied out of range of the screen
                                else
                                    newCount++;
                            }
                            else
                            {
                                newWindow[i][j] = newContent[newCount];
                                newCount++;
                            }
                        }
                        else if (newContent.length - newCount == 0)
                        {
                            newWindow[i][j] = '|';
                            i = newWindow.length; j = newWindow[0].length; // exit the loop
                        }
                    }
                }
            windowList.get(windowList.size()-1).setWindow(newWindow);
        }
        print();
    }

    /**
     * print the contents stored in the textbox to the screen
     * @param: none
     * @return: none
     */
    public void print()
    {
        Screen screen = new Screen();
        for (Window w: windowList)
        {
            //throws expection if window goes beyond the screen dimension
            try{
                for (int i = w.getOpenRow(); i < w.getRow() + w.getOpenRow(); i++)
                {
                    for (int j = w.getOpenCol(); j < w.getCol() + w.getOpenCol(); j++)
                    {
                        if (i < Screen.screen.length && j < Screen.screen[0].length)
                        {
                            //special chars should be defaulted to ' '
                            if (w.getWindow()[i-w.getOpenRow()][j-w.getOpenCol()] == '\0' || w.getWindow()[i-w.getOpenRow()][j-w.getOpenCol()] == '|')
                                Screen.screen[i][j] = ' ';
                            else
                                Screen.screen[i][j] = w.getWindow()[i-w.getOpenRow()][j-w.getOpenCol()];
                        }

                    }
                }
            }
            catch(Exception e){}
            w.onTop = false; // other windows are not active expect the last window in the arraylist
        }
        //the last window in the arraylist is defaulted to be active
        windowList.get(windowList.size()-1).onTop = true;
        System.out.println(screen.toString());
    }

    /**
     * close the active window with a given name, or close the active alert
     * @param str: a String containing the name of window to be closed
     * @return: none
     */
    public void close(String str)
    {
        boolean isFound = false;
        //close Untitled textbox
        if (str.contains("Untitled"))
        {
            int number = Integer.parseInt(str.substring(str.length()-1));
            for (Window w: windowList)
            {
                if (w.getName().equals("Untitled" + number))
                {
                    windowList.remove(w); // closed window is removed from arraylist
                    break;
                }
            }
            if (windowList.size() != 0)
                print();
            else
            {
                screen.getScreen();
                System.out.println(screen.toString());
            }
        }
        //close Alert with a name
        else if (str.contains("Alert"))
        {
            int number = Integer.parseInt(str.substring(str.length()-1));
            for (Window w: windowList)
            {
                if (w.getName().equals("Alert" + number))
                {
                    windowList.remove(w); // closed window is removed from arraylist
                    break;
                }
            }
            if (windowList.size() != 0)
                print();
            else
            {
                screen.getScreen();
                System.out.println(screen.toString());
            }
        }
        //close an active alert
        else if (str.length() == 5)
        {
            if (windowList.get(windowList.size()-1).getName().contains("Alert"))
            {
                windowList.remove(windowList.get(windowList.size()-1));
                if (windowList.size() != 0)
                    print();
                else
                {
                    screen.getScreen();
                    System.out.println(screen.toString());
                }
            }
            else
            {
                windowList.add(new Alert("closebyname")); // if the active window is not alert, a new alert will be generated
                windowList.get(windowList.size()-1).print();
            }
        }
        //close a .txt file with a correct name
        else if (str.contains(".txt"))
        {
            for (Window w: windowList)
            {
                if (w.getName().equals(str.substring(6)))
                {
                    windowList.remove(w);
                    isFound = true;
                    break;
                }
            }
            //generate an alert if the file name is not read correctly
            if (!isFound)
            {
                windowList.add(new Alert("invalidfile"));
            }
            if (windowList.size() != 0)
                print();
            else
            {
                screen.getScreen();
                System.out.println(screen.toString());
            }
        }
    }

    /**
     * append the contents from a window to the active window
     * @param windowName: input get from user with the name of the window (only textbox) to be appended to the new window
     * @return: none
     */
    public void append(String windowName)
    {
        String content = "";
        //find the window used to append
        for (Window w: windowList)
        {
            if (w.getName().equals(windowName))
            {
                content = w.getContents(); // get the contents from the window
            }
        }
        windowList.get(windowList.size()-1).write(content); // append the contents to the active window
    }

    /**
     * save the contents of the active window to a .txt file
     * @param fileName: input get from user with the .txt file name to save the contents of the active window
     * @return: none
     */
    public void save(String fileName) throws IOException
    {
        String content = "";
        content = windowList.get(windowList.size()-1).getContents();// get contents from the active window
        if (fileName.length() == 4)
        {
            PrintWriter pw = new PrintWriter(windowList.get(windowList.size()-1).getName());
            pw.println(content);
            pw.close();
        }
        // wrong file name pops up an alert
        else if (!fileName.contains(".txt"))
        {
            windowList.add(new Alert("open"));
            windowList.get(windowList.size()-1).print();
        }
        // correctly given file name
        else
        {
            PrintWriter pw = new PrintWriter(fileName.substring(5));
            pw.println(content);
            pw.close();
        }
        print();
    }

    /**
     * check the open position of a window and reset it to the upper left if it goes out of the screen range
     * @param: none
     * @return: none
     */
    public void checkOpenPosition()
    {
        if (currentOpenRow > Screen.SCREENHEIGHT - 1 || currentOpenCol > Screen.SCREENWIDTH - 1)
        {
            currentOpenRow = ROW;
            currentOpenCol = COL;
        }
    }

    //abstract methods to be implemented by subclasses
    public abstract String getName();
    public abstract char[][] getWindow();
    public abstract int getOpenRow();
    public abstract int getOpenCol();
    public abstract int getRow();
    public abstract int getCol();
}

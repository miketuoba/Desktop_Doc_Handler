/*
 * DocHandlerDriver.java
 * @Author: Mike Wu, Student ID. 100342872
 * @Date: Mar 15, 2022
 * a driver class to operate the Document Handler
 */

package DocHandler;

import java.io.*;
import java.util.*;

public class DocHandlerDriver {
    public static void main(String[] args) throws IOException
    {
        Screen screen = new Screen();
        System.out.println(screen.toString());

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("quit")) //keep operating until quit
        {
            operateTextBox(input, screen);
            input = sc.nextLine();
        }
        sc.close();
    }

    /**
     * the method used to operate the document handler by dealing with different commands
     * @param input: the command inputted as a String
     * @param scr: the Screen object to be printed
     * @return void
     */
    public static void operateTextBox(String input, Screen scr) throws IOException
    {
        if (input.equals("new"))
        {
            Window.windowList.add(new TextBox());
            Window.windowList.get(Window.windowList.size()-1).print();
        }
        else if (input.contains("close"))
        {
            Window.windowList.get(0).close(input);
        }
        else if (input.contains("select"))
        {
            Window.windowList.get(0).select(input);
        }
        else if (input.contains("move"))
        {
            Window.windowList.get(0).move(input);
        }
        else if (input.contains("resize"))
        {
            Window.windowList.get(0).resize(input);
        }
        else if (input.contains("open"))
        {
            if (!input.contains(".txt"))
            {
                Window.windowList.add(new Alert(input)); // alert for not containing .txt
                Window.windowList.get(Window.windowList.size()-1).print();
            }
            else
            {
                Window.windowList.add(new TextBox(input.substring(5)));
                File inFile = new File (input.substring(5));
                if (!inFile.exists()) // still open a window with the name, but error message pops up
                {
                    System.out.println("Error reading file");
                    Window.windowList.get(Window.windowList.size()-1).print();
                }
                else
                    Window.windowList.get(Window.windowList.size()-1).getContent(input.substring(5));
            }
        }
        else if (input.contains("write"))
        {
            Window.windowList.get(Window.windowList.size()-1).write(input.substring(6));
        }
        else if (input.contains("append"))
        {
            Window.windowList.get(Window.windowList.size()-1).append(input.substring(7));
        }
        else if (input.contains("save"))
        {
            if (Window.windowList.get(Window.windowList.size()-1).getName().contains("Alert") || input.contains("Alert"))
            {
                Window.windowList.add(new Alert("saveAlert")); // alert for saving alert
                Window.windowList.get(Window.windowList.size()-1).print();
            }
            else
                Window.windowList.get(Window.windowList.size()-1).save(input);
        }
        else
        {
            Window.windowList.add(new Alert()); // other commands are considered invalid and an alert is generated
            Window.windowList.get(Window.windowList.size()-1).print();
        }
    }
}
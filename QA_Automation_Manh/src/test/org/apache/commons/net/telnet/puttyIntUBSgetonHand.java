package org.apache.commons.net.telnet;

import TestingTools.Generic.autoScreenShot;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by kwestberg on 3/18/2015.
 */
public class puttyIntUBSgetonHand {

/**
 * Created by kwestberg on 3/10/2015.
 */

    private InputStream in;
    private static PrintStream out;

    public static void puttyIntUBSgetonHand(String Warehouse, String Item) {

        try {


            String[] command =
                    {
                            "cmd",
                    };
            Process p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            // Get input and output stream references

            Thread.sleep(1000);
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("putty -load ubsqa1");//this open putty.exe for a specific profile set up as VT100
            System.out.flush();
            stdin.close();
            System.out.print(Warehouse);//This is the Parameter passed from the WM testing
            Thread.sleep(3000);//wait for it to load
            try {
                Robot robot = new Robot();

                // Simulate a mouse click
                //robot.mousePress(InputEvent.BUTTON1_MASK);
                //robot.mouseRelease(InputEvent.BUTTON1_MASK);
                // Simulate a key press
                robot.keyPress(KeyEvent.VK_K);
                robot.keyRelease(KeyEvent.VK_K);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_W);
                robot.keyPress(KeyEvent.VK_E);
                robot.keyRelease(KeyEvent.VK_E);
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_S);
                robot.keyPress(KeyEvent.VK_T);
                robot.keyRelease(KeyEvent.VK_T);
                robot.keyPress(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_B);
                robot.keyPress(KeyEvent.VK_E);
                robot.keyRelease(KeyEvent.VK_E);
                robot.keyPress(KeyEvent.VK_R);
                robot.keyRelease(KeyEvent.VK_R);
                robot.keyPress(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_G);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                robot.keyPress(KeyEvent.VK_3);
                robot.keyRelease(KeyEvent.VK_3);
                robot.keyPress(KeyEvent.VK_4);
                robot.keyRelease(KeyEvent.VK_4);
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(10000);//10 seconds
                /*
                    now we are logged in , now lets got to the menus for on hand
                 */
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);// .5 seconds
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_0);
                robot.keyRelease(KeyEvent.VK_0);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);// .5 seconds
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);// .5 seconds      --------------------------------we are now at the PROD INQ SCREEN
                robot.keyPress(KeyEvent.VK_0);
                robot.keyRelease(KeyEvent.VK_0);
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);                                                        //---------------------------------Warehouse
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);
                new autoScreenShot(Warehouse);
                                                                 //---------------------------------
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                //HER WE SHOULD WAIT
                Thread.sleep(1000);//10 seconds
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(2000);
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                Thread.sleep(2000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);
                robot.keyPress(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_L);
                robot.keyPress(KeyEvent.VK_O);
                robot.keyRelease(KeyEvent.VK_O);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            stdin.flush();//write the buffer
            stdin.close();//close the buffer
            // write any other commands you want here
            Thread.sleep(2000);
            Thread.sleep(2000);
            stdin.close();
            int returnCode = p.waitFor();
            System.out.println("Return code = YAY!!! " + returnCode);


        }//end try
        catch (Exception e) {
            e.printStackTrace();
        }//end catch
    }//end puttyInt method
}//end puttyInterface class
/*
QA1_
                robot.keyPress(KeyEvent.VK_Q);
                robot.keyRelease(KeyEvent.VK_Q);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_MINUS);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_MINUS);


 */

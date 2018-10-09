package TestingTools.UBS;

import TestingTools.Generic.autoScreenShot;
import org.apache.commons.net.telnet.SyncPipe;
import org.apache.commons.net.telnet.richRobot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class puttyIntUBSsearchItem {

    private InputStream in;
    private static PrintStream out;

    public static void puttyIntUBSsearchItem(String Warehouse, String Item) {

        try {
            String[] command =
                    {
                            "cmd",
                    };
            Process p = Runtime.getRuntime().exec(command);   // This is necessary for the overall in and out streams
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            // Get input and output stream references

            Thread.sleep(1000);
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println("putty -load ubsqa1");//this open putty.exe for a specific profile set up as VT100
            System.out.flush();
            stdin.close();
            System.out.print(Warehouse);//This is the Parameter passed from the WM testing
            System.out.print(Item);
            Thread.sleep(4000);//wait for it to load
            try {
                richRobot robots = new richRobot();
                Robot robot = new Robot();
                robots.richRobot("KWESTBERG");// we have to send uppercase letters
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(100);
                robots.richRobot("123456");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(5000);
                robots.richRobot("99");//get out to the command interface
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(5000);
                String search1 = "SEA PIX";
                String search2 =   "COMPL19/SIN";
                robots.richRobot(search1);
                robot.keyPress(KeyEvent.VK_SHIFT);//UNDERSCORE
                robot.keyPress(KeyEvent.VK_MINUS);//UNDERSCORE
                robot.keyRelease(KeyEvent.VK_SHIFT);//UNDERSCORE
                robot.keyRelease(KeyEvent.VK_MINUS);//UNDERSCORE
                robots.richRobot(search2 + " " + Item );// we cannot send underscore through
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                        //sea pix_compl19/sin 059951
               //HERE WE SHOULD WAIT
                Thread.sleep(10000);//10 seconds
                new autoScreenShot(Warehouse);
                String logout = "LO";
                robots.richRobot(logout);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);
           } catch (AWTException e) {
                e.printStackTrace();
            }
            stdin.flush();//write the buffer
            stdin.close();//close the buffer
            // any other commands you want
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
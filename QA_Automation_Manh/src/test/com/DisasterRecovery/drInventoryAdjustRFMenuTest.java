package com.DisasterRecovery;

import TestingTools.Oracle.getLocnBrcdjdbcConnectGIL;
import com.WM_2012.tests.locationData;
import org.apache.commons.net.telnet.SyncPipe;
import org.apache.commons.net.telnet.richRobot;
import org.testng.annotations.Test;

import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;


/**
 * Created by kwestberg on 3/20/2015.
 */

public class drInventoryAdjustRFMenuTest {
    static String Warehouse = "TWC";
    static String Item = "123456";

    static final String lpn = "AUTOLPN32000";
    @Test
    public static void Go() {

        List<locationData> locations = getLocnBrcdjdbcConnectGIL.getLocnBRCD(Item, Warehouse);

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
            PrintWriter stdout = new PrintWriter(String.valueOf(p.getInputStream()));
            //stdin.println("putty -load QA2TWC");//this open putty.exe for a specific profile set up as VT100
            //stdin.println("C:\\Selenium_test_cases\\selQAjavaAutomation\\src\\test\\java\\TestingTools\\java -jar jta26.jar QAAS2");
            stdin.println("java -jar C:\\Selenium_test_cases\\selQAjavaAutomation\\jta26.jar QAAS2");
            System.out.flush();
            stdin.close();
            //System.out.print(Warehouse);//This i097783      // s the Parameter passed from the WM testing
            //System.out.print(Item);
            Thread.sleep(3000);//wait for it to load
            stdin.println("KWESTBERG");
            stdin.println();
            stdin.println("123456");

            //stdin.println("KWESTBERG");
            try {
                stdin.println("KWESTBERG");
                stdin.println();
                stdin.println("123456");

                richRobot robots = new richRobot();
                Robot robot = new Robot();
                robots.richRobot("KWESTBER");// we have to send uppercase letters
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(100);
                robots.richRobot("LMNOPKOW3");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(5000);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease((KeyEvent.VK_UP));
                //robot.keyRelease(KeyEvent.VK_SHIFT);
                Thread.sleep(100);
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease((KeyEvent.VK_UP));
                Thread.sleep(100);
                robots.richRobot("KWESTBERG");// we have to send uppercase letters
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(100);
                robots.richRobot("123123");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(3000);//---------------------------- we are logged in now
                robots.richRobot("5");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1000);//---------------------------- we are IN Inventory Control
                robots.richRobot("2");
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1000);//---------------------------- we are IN Case Pick Cycle Count
                //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
                Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                Thread.sleep(400);
                //logged into Linux  and the RF client     now lets loop through the locations and add inventory
                int lpnnew = 1;

                //int count = 1;
                //System.out.print(String.format("\033[%dA",count)); // Move up
                //System.out.print("\033[2K"); // Erase line content


                for (locationData location : locations) {
                    Thread.sleep(2500);
                    //System.out.print('\f');
                    //System.out.print("\033[2K");
                    Thread.sleep(100);
                    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
                    robots.richRobot(location.LocationBarcode);
                    Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    System.out.print('\f');
                    Thread.sleep(600);
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    Thread.sleep(500);
                    //System.out.print(location.LocationBarcode);
                    //System.out.print(location.ItemName);
                    //Thread.sleep(300);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_A);
                    Thread.sleep(500);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_A);
                    Thread.sleep(750);
                    robots.richRobot(location.ItemName);
                    Thread.sleep(1000);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(1000);
                    robots.richRobot("23");
                    Thread.sleep(1000);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);//recount required ere
                    Thread.sleep(900);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);//recount required ACKNOWLEDGE
                    Thread.sleep(900);
                    robots.richRobot("23");
                    Thread.sleep(600);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);//this is the recount
                    Thread.sleep(1000);
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    System.out.print('\f');
                    Thread.sleep(500);
                    lpnnew  ++;
                    //lpnnew = Integer20.parseInt(String.valueOf(lpn));
                    String strLPN = lpn + lpnnew;
                    //System.out.println(strLPN);
                    //System.out.println(String.valueOf(strLPN));
                    robots.richRobot(strLPN);
                    Thread.sleep(600);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(500);
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    System.out.print('\f');
                    Thread.sleep(1000);
                    robots.richRobot("12");
                    Thread.sleep(500);
                    robots.richRobot("12");
                    Thread.sleep(500);
                    robots.richRobot("20");
                    Thread.sleep(500);
                    robots.richRobot("16");
                    Thread.sleep(500);
                    robot.keyPress(
                            KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_N);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_N);
                    Thread.sleep(500);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    //robot.keyPress(KeyEvent.VK_CONTROL);
                    //robot.keyPress(KeyEvent.VK_N);
                    //robot.keyRelease(KeyEvent.VK_CONTROL);
                    //robot.keyRelease(KeyEvent.VK_N);
                    Thread.sleep(500);
                    System.out.print('\f');
                    System.out.flush();
                    //System.in.read();
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(500);
                    //Thread.sleep(200);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(500);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_A);
                    Thread.sleep(500);//                now we repeat
                }
                //begin logout here
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_W);
                Thread.sleep(1000);
                //robot.keyPress(KeyEvent.VK_CONTROL);
                //robot.keyPress(KeyEvent.VK_X);
                //robot.keyRelease(KeyEvent.VK_CONTROL);
                //robot.keyRelease(KeyEvent.VK_X);
                Thread.sleep(1000);
                //robots.richRobot("EXIT");
                //robot.keyPress(KeyEvent.VK_ENTER);
                //robot.keyRelease(KeyEvent.VK_ENTER);
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
    }
}
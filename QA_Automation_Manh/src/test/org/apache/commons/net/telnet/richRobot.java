package org.apache.commons.net.telnet;

import com.google.common.base.Strings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.ParsePosition;

/**
 * Created by kwestberg on 3/19/2015.
 */
public class richRobot extends Robot {


    public richRobot() throws AWTException {
    }

    public void richRobot(Robot robots, String keys) {
        try{
            Robot robot = new Robot();
            for (int t = 0; t < keys.length(); t++) {
                if (keys.contains("_")){
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                }
                else{

                    //robots.keyPress((keys).charAt(t));
                    //robots.keyRelease((keys).charAt(t));
                    try {
                        robots.keyPress((keys).indexOf(t));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }//end catch

    }
    public void richRobot(String keys) {
        try{
            Robot robot = new Robot();
            Robot robots = new Robot();
            for (int t = 0; t < keys.length(); t++) {
                if(keys.matches("_") ) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                    robot.keyPress(KeyEvent.VK_MINUS);
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                    robot.keyRelease(KeyEvent.VK_MINUS);
                }
                else{
                    try {
                        robots.keyPress(String.valueOf(keys).charAt(t));
                        robots.keyRelease(String.valueOf(keys).charAt(t));
                                //robots.keyRelease((keys).charAt(t));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }//end catch

    }
}


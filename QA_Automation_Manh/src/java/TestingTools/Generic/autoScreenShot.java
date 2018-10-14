package TestingTools.Generic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kwestberg on 3/15/2015.
 */

public class autoScreenShot {

        public autoScreenShot(String testnumber){

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");//all the way down to milliseconds
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            Robot robot = new Robot();
            // Capture the screen shot of the area of the screen defined by the rectangle
            BufferedImage bi = robot.createScreenCapture(new Rectangle(1024, 768));
            ImageIO.write(bi, "jpg", new File("C:\\Selenium_test_cases\\photo" + (dateFormat.format(date)) + testnumber + ".jpg"));//modify this to add time stamps to screenshot files
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
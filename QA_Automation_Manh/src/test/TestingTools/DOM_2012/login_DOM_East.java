/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.DOM_2012;

import TestingTools.Generic.autoScreenShot;
import com.WM_2012.tests.WM_2012_TWC_Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by kwestberg on 4/8/2015.
 */
public class login_DOM_East {

    private WebElement element;
    String Warehouse = "TWCDOM_East";
    //String Warehouse;


    public login_DOM_East login_DOM_East() throws Exception {
        //WM_2012_TWC_Test.class.getMethods();
        //WM_2012_TWC_Test.driverIE.switchTo().window(WM_2012_TWC_Test.parentHandle);
        //String childHandle  = WM_2012_TWC_Test.driverIE.getWindowHandle().toString();
        //System.out.println(childHandle + "loginhandlechild");//not needed, just FYI
        //Thread.sleep(3000);
        //new autoScreenShot(Warehouse);
        long end = System.currentTimeMillis() + 7000;
        while (System.currentTimeMillis() < end) {
            element = WM_2012_TWC_Test.driverIE.findElement(By.id("j_username"));
            if (element.isDisplayed()) {
                break;
            }
        }
        element.sendKeys("kwestberg2");
        element = WM_2012_TWC_Test.driverIE.findElement(By.id("j_password"));
        element.sendKeys("123456");//sshhhhh this is my password
        element.submit();
        new autoScreenShot(Warehouse);
        return null;
    }
}
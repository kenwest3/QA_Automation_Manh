/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.IO_2012;

import TestingTools.Generic.autoScreenShot;
import com.DisasterRecovery.drPurchaseOrderCreateIOTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by kwestberg on 4/17/2015.
 */
public class login_IO_QA2 {

    private WebElement element;

    public login_IO_QA2(String Warehouse) throws Exception {
        drPurchaseOrderCreateIOTest.class.getMethods();
        //drPurchaseOrderCreateIOTest.driverIE.switchTo().window(drPurchaseOrderCreateIOTest.parentHandle);
        String childHandle  = drPurchaseOrderCreateIOTest.driverIE.getWindowHandle().toString();
        System.out.println(childHandle + "loginhandlechild");//dont really need this here, just FYI
        new autoScreenShot(Warehouse);
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            element = drPurchaseOrderCreateIOTest.driverIE.findElement(By.id("j_username"));
            if (element.isDisplayed()) {
                break;
            }
        }
        element.sendKeys("kwestberg");
        element = drPurchaseOrderCreateIOTest.driverIE.findElement(By.id("j_password"));
        element.sendKeys("123456");//sshhhhh this is my password
        element.submit();
        new autoScreenShot(Warehouse);
        //return childHandle;
    }
}
/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.WM_2012;

import com.kwestberg.tests.WM_2012_HVA_cancel_DO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.beust.testng.*;

/**
 * Created by kwestberg on 3/13/2015.
 * This will be a reusable login for DOM East
 */


public class Chrome_login_WM_2012 {

    private WebElement element;
    @Test_Chrome_login_WM_2012
    public Chrome_login_WM_2012(String Warehouse) throws Exception {
        WM_2012_HVA_cancel_DO.class.getMethods();
        //WM_2012_HVA_cancel_DO.driverIE.switchTo().window(WM_2012_HVA_cancel_DO.parentHandle);
        String childHandle  = WM_2012_HVA_cancel_DO.driverIE.getWindowHandle().toString();
        System.out.println(childHandle + "loginhandlechild");//dont really need this here, just FYI
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            element = WM_2012_HVA_cancel_DO.driverIE.findElement(By.id("j_username"));
            if (element.isDisplayed()) {
                break;
            }
        }
        element.sendKeys("kwestberg");
        element = WM_2012_HVA_cancel_DO.driverIE.findElement(By.id("j_password"));
        element.sendKeys("123123");//sshhhhh this is my password
        element.submit();
        //return childHandle;
    }
}
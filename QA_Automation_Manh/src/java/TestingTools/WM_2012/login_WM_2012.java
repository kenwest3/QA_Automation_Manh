package test.TestingTools.WM_2012;

import test.TestingTools.Generic.autoScreenShot;
import test.com.WM_2012.tests.WM_2012_TWC_Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by kwestberg on 3/13/2015.
 * This will be a reusable login for DOM East
 */


public class login_WM_2012 {

    private WebElement element;

    public login_WM_2012(String Warehouse) throws Exception {
        WM_2012_TWC_Test.class.getMethods();
        WM_2012_TWC_Test.driverIE.switchTo().window(WM_2012_TWC_Test.parentHandle);
        String childHandle  = WM_2012_TWC_Test.driverIE.getWindowHandle().toString();
        System.out.println(childHandle + "loginhandlechild");//dont really need this here, just FYI
        new autoScreenShot(Warehouse);
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            element = WM_2012_TWC_Test.driverIE.findElement(By.id("j_username"));
            if (element.isDisplayed()) {
                break;
            }
        }
        element.sendKeys("kwestberg");
        element = WM_2012_TWC_Test.driverIE.findElement(By.id("j_password"));
        element.sendKeys("123123");//sshhhhh this is my password
        element.submit();
        new autoScreenShot(Warehouse);
        //return childHandle;
    }
}
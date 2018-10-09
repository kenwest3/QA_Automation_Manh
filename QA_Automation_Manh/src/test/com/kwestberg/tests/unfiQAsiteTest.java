package com.kwestberg.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * Created by kwestberg on 3/11/2015.
 */

public class unfiQAsiteTest {
    //https://unfiqa.unfi.com/Pages/default.aspx
    //https://customers.unfi.com/_login/LoginPage/Login.aspx?ReturnUrl=%2f_layouts%2f15%2fAuthenticate.aspx%3fSource%3d%252F&Source=%2F
    private WebElement element;
    /*
        @Before
        public void setUp() throws Exception {

        }
    */
    @Test
    public void testTWC_test_TEST_Java() throws Exception {
        WebDriver driverIE = new InternetExplorerDriver();

        //Actions builder = new Actions(driverIE);
        driverIE.navigate().to("http://Customersqa.unfi.com");Thread.sleep(3000);
        //element.submit();
        element = driverIE.findElement(By.id("userName"));
        element.sendKeys("KENWEST3");
        element = driverIE.findElement(By.id("Password"));
        element.sendKeys("notmypassword");
        element.submit();
        /*
        Trying to double click in the next section
         */
        Thread.sleep(3000);
    }
}

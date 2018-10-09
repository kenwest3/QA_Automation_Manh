package com.kwestberg.tests;

import TestingTools.WM_2012.Chrome_login_WM_2012;
import TestingTools.WM_2012.login_WM_2012;
import com.DisasterRecovery.drInventoryAdjustRFMenuTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by kwestberg on 5/9/2015.
 */
public class WM_2012_HVA_cancel_DO {
    WebElement element;
    String Warehouse = "HVA";
    Integer x = 1;
    public static String parentHandle;
    public static WebDriver driverIE = new ChromeDriver();
    @BeforeSuite
    public void setUp() throws Exception {
    }
    @Test
    public void mainTest() throws Exception {
        driverIE.manage().deleteAllCookies();
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com/ucl/login/jsp/universal_login.jsp");
        driverIE.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//implicit wait time
        Chrome_login_WM_2012 WML = new Chrome_login_WM_2012(Warehouse);
        Thread.sleep(3000);
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com:20000/cbo/transactional/distributionorder/view/DistributionOrderList.jsflps?fotype=ORDER");
        Thread.sleep(3000);
        do{
        element = driverIE.findElement(By.id("checkAll_c0_dataForm:DOList_entityListView:DOList_MainListTable"));
        element.click();
        element = driverIE.findElement(By.id("Actions"));
        element.click();
        ((JavascriptExecutor) driverIE).executeScript("callActionMethod('CTO_DOList_Cancel_Order_menuItemBtn')");
        //Thread.sleep(3000);
        driverIE.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//implicit wait time
        driverIE.switchTo().alert().accept();
        Thread.sleep(1000);
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com:20000/cbo/transactional/distributionorder/view/DistributionOrderList.jsflps?fotype=ORDER");
        driverIE.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//implicit wait time
        } while (3600 > x);
        driverIE.manage().deleteAllCookies();
        driverIE.close();
    }
   @AfterSuite
    public void tearDown() throws Exception {
    }
}
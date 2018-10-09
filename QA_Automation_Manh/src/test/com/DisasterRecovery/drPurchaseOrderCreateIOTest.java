/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.DisasterRecovery;

import TestingTools.Oracle.getIOOrderjdbcConnect;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.ClickAt;
import com.thoughtworks.selenium.webdriven.commands.WaitForPopup;
import javafx.animation.PauseTransition;
import net.sourceforge.htmlunit.corejs.javascript.ObjToIntMap;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import TestingTools.IO_2012.login_IO_QA2;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import TestingTools.Generic.autoScreenShot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by kwestberg on 4/17/2015.
 */

public class drPurchaseOrderCreateIOTest {
    private WebElement element;
    public static WebDriver driverIE = new ChromeDriver();
    //public static WebDriver driverIE = new InternetExplorerDriver();
    public static String parentHandle;
    private String WareHouse = "RID";


    @BeforeClass
    public void oneTimeSetUp() {
        // one-time initialization code
        //System.out.println("@BeforeClass - oneTimeSetUp");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driverIE;
        jsExecutor.executeScript("window.showModalDialog = window.open");
    }
    @Test
    public void drPurchaseOrderCreateIOTest() throws Exception, AWTException {
        String RegularOrder = getIOOrderjdbcConnect.getIOOrderjdbcConnect();//This is the Order String that we are sending to the SQL search in IO
        parentHandle = driverIE.getWindowHandle();
        Robot rb = new Robot();
        System.out.println(parentHandle + "par1");
        //Actions builder = new Actions(driverIE);
        driverIE.navigate().to("http://ctdayq2rp01.unfi.com:10000/ucl/login/jsp/universal_login.jsp");
        driverIE.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//implicit wait time
        parentHandle = driverIE.getWindowHandle();
        System.out.println(parentHandle + "par2");
        new autoScreenShot(WareHouse);
        login_IO_QA2 LIO = new login_IO_QA2(WareHouse);
        Thread.sleep(3000);
        driverIE.navigate().to("http://ctdayq2rp01.unfi.com:10000/uclAppInstanceServlet.serv?userName=E11E4CE9FE90124352959F5B635C5653&appInstance=replenishment");//Ken westberg user Replenishment Page
        Thread.sleep(300);// 30 seconds is a long time but it is absolutely necessary for the page load - or we can wait for page load event from browser
        driverIE.navigate().to("http://ctdayq2rp01.unfi.com:10100/io/order/list.jsp");
        //<input name="groupSelectionStore.quickFindArg2" class="panelFieldShort" id="groupSelectionStore.2" onkeypress="quickFindKeyPress('')" onfocus="javascript:quickFindArgOnFocus('groupSelectionStore.2', 'Dest Code');" onblur="javascript:quickFindArgOnBlur('groupSelectionStore.2', 'Dest Code');" type="text" value="Dest Code"/>
        element = driverIE.findElement(By.id("groupSelectionStore.2"));//selects the topmost element in the grid
        element.sendKeys("RID");
        Thread.sleep(500);
        element = driverIE.findElement(By.id("groupSelectionStore.1"));//selects the topmost element in the grid
        element.sendKeys(RegularOrder);// Regular Orders will never work on a Sundayt because there is no such Order on a Sunday - Business SOP
        //element.sendKeys("P-006020");//P-006020 - OLD SCHOOL SNACKS, INC.//P-006040 - CASCADE FRESH //P-006041 - CASCADE FRESH
        Thread.sleep(1000);
        new autoScreenShot(WareHouse);
        element.submit();
        Thread.sleep(2000);
        assertNotNull(driverIE.findElement(By.id("groupSelectionStore.2"))); //this is going to return the IE instance
        Thread.sleep(1000);//[[InternetExplorerDriver: internet explorer on WINDOWS (bb9226e8-41f9-46c1-a8f8-35c0c983e81c)] -> id: groupSelectionStore.1]
        element = driverIE.findElement(By.name("selections"));
        element.click();//Click the single check box on teh page
        Thread.sleep(2000);//Page will reload on the new selection criteria
        //((JavascriptExecutor) driverIE).executeScript("javascript:doApprove()");
        new autoScreenShot(WareHouse);
        Set<String> allWindows = driverIE.getWindowHandles();
        String window1=driverIE.getWindowHandle();
        //System.out.println(driverIE.getTitle());
        Set windowList = driverIE.getWindowHandles();
        //System.out.println(driverIE.getWindowHandle().toString());
        Iterator iterator = windowList.iterator();
        String window2 = null;
        while(iterator.hasNext())
        {
            //System.out.println("Entering while loop");
            ((JavascriptExecutor) driverIE).executeScript("javascript:doApprove()");
            System.out.println(driverIE.getWindowHandle().toString());
            Thread.sleep(2000);

            driverIE.switchTo().alert().accept();

            System.out.println(driverIE.getWindowHandle().toString());

            window2 = iterator.next().toString();
            if(!(window1.equals(window2))){
                driverIE.switchTo().window(window2);
                //System.out.println("Title of the page after - switchingTo: " + driverIE.getTitle());
                //System.out.println("Current url" + driverIE.getCurrentUrl());

                driverIE.manage().window().maximize();
            }
        }
        if(!allWindows.isEmpty()){
            for (String windowID : allWindows){
                if(driverIE.getPageSource().contains("You")){
                    try{
                        System.out.println(windowID);
                        //System.out.println(allWindows);
                        //WebElement okayButton =driverIE.findElement(By.id("Ok"));
                        //okayButton.click();
                        //WebElement okButton = driverIE.findElement(By.name("Ok"));
                        //okButton.click();
                        break;

                    }catch (NoSuchWindowException e){
                        e.printStackTrace();
                    }
                }
            }
            driverIE.switchTo().window(parentHandle);
            assertTrue(driverIE.getTitle().equals("Replenishment for Open Systems 2012"));
        }
        Thread.sleep(1000); //wait 5 seconds for the windows pop up on rthe approve of the Order to become a Purchase Order


    }

    @AfterTest
    public void closeSelenium() throws AWTException {
        //Shutdown the browser
        Robot rb = new Robot();

        try {
            Thread.sleep(7000);
            //((JavascriptExecutor) driverIE).executeScript("javascript:SignOut()");
            //((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
            System.out.println("Title of the page after - before we run the jscript confirmsignout: " +
                    driverIE.getTitle());
            ((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
            //driverIE.selectPopUp();

            driverIE.manage().deleteAllCookies();
            Thread.sleep(2000);
            //((JavascriptExecutor) driverIE).executeScript("javascript:fnLogout()");
            try {
                WebDriverWait wait = new WebDriverWait(driverIE, 2);
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driverIE.switchTo().alert();
                alert.accept();
                System.out.println("Title of the page after - switchingTo Int Try block after deleting cookies : " +
                        driverIE.getTitle());
                //rb.keyPress(KeyEvent.VK_ENTER);//Handle the Windows POP UP that cannot be handled by Selenium Web Driver
                //rb.keyRelease(KeyEvent.VK_ENTER);
                driverIE.switchTo().alert().accept();
            } catch (Exception e) {
                //exception handling
            }
            System.out.println("Title of the page after - switchingTo Closing out activities: " +
                    driverIE.getTitle());

            //rb.keyPress(KeyEvent.VK_ENTER);//Handle the Windows POP UP that cannot be handled by Selenium Web Driver
            //rb.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("Title of the page after - before we run the jscript confirmsignout: " +
                    driverIE.getTitle());
            //((JavascriptExecutor) driverIE).executeScript("javascript:fnLogout()");
            Thread.sleep(5000);
            driverIE.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
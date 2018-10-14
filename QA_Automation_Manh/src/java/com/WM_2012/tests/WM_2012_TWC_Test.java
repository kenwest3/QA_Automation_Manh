package com.WM_2012.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.String;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TestingTools.DOM_2012.login_DOM_East;
import TestingTools.MSSQL.connectToElmerFindUBSso;
import TestingTools.OpenVMSReader;
import TestingTools.WM_2012.login_WM_2012;
import TestingTools.Generic.autoScreenShot;
import net.sourceforge.htmlunit.corejs.javascript.NativeIterator;
import org.apache.commons.net.telnet.TelnetClient;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static org.apache.commons.net.telnet.puttyIntUBSsearchItem.puttyIntUBSsearchItem;
import static org.apache.commons.net.telnet.puttyIntUBSgetonHand.puttyIntUBSgetonHand;

/**
 * Created by kwestberg on 3/9/2015.
 */
public class WM_2012_TWC_Test {
    WebElement element;
    String Warehouse = "HVA";
    String Item = "126314";
    String SalesOrder = "19491338001";
    Integer x = 1;
    /**
     *
     */

    connectToElmerFindUBSso ubsSalesOrder = new connectToElmerFindUBSso(SalesOrder);

    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream() ;
    public OutputStream _writer = telCLi.getOutputStream();
    public static WebDriver driverIE = new InternetExplorerDriver();
    public static String parentHandle;
    OpenVMSReader VMSHandler;
    @BeforeSuite
    public void setUp() throws Exception {


    }

    private void WaitForText(String text) throws InterruptedException {
        String val = "";
        long end = System.currentTimeMillis() + 30000;
        while (System.currentTimeMillis() < end) {
            {

                while (VMSHandler.Queue.size() > 0) {
                    val = VMSHandler.Queue.get(0);
                    VMSHandler.Queue.remove(0);
                    if (val.toUpperCase().contains(text.toUpperCase()) || VMSHandler.Line.toUpperCase().contains(text.toUpperCase())) {
                        return;
                    }
                    VMSHandler.Line = "";
                }

                Thread.sleep(1000);
                if (System.currentTimeMillis() > end) {
                    //Thread.CurrentThread.Abort();
                    System.out.println("Ken I Did not find '" + text + "'");
                }
            }
        }
    }

    public void WriteLn(String text)
    {

        text = text + "\r\n";
        try {
            _writer.write(text.getBytes());
            _writer.flush();
        } catch (IOException ex)
        {
            System.out.println("Error writing data: " + ex.getMessage());
        }
    }

    @Test
    public void mainTest() throws Exception {
        //ubsSalesOrder.connectToElmerFindUBSso(SalesOrder);
        //System.out.println(ubsSalesOrder.connectToElmerFindUBSso(String.valueOf(ubsSalesOrder)));
        do{

            telCLi.connect("QAAS2", 23);
            _reader = telCLi.getInputStream();
            _writer = telCLi.getOutputStream();
            VMSHandler = new OpenVMSReader(_reader, _writer);
            VMSHandler.Start();
            WaitForText("Username");
            WriteLn("kwestberg");
            WaitForText("Password");
            WriteLn("123456");
            WaitForText("6. Sales & Marketing");
            WaitForText("Enter your selection");
            Thread.sleep(2000);
            WriteLn("6");
            WaitForText("Advertising Menu");
            WaitForText("Enter your selection");
            WriteLn("3");
            WaitForText("Order Entry / Maintenance");
            WaitForText("Enter your selection");
            WriteLn("1");
            WaitForText("1. CORP Cust-#");
            System.out.println("here at cust ");
            WriteLn("44624");//19491338001
            Thread.sleep(1000);
            WriteLn("N");//19491338001
            WriteLn("");
            WriteLn("");
            WriteLn("");
            WriteLn("");
            WriteLn("");
            WriteLn("");
            WriteLn("");
            WriteLn("TestNGAutomat");
            WriteLn("");
            WriteLn("");
            WriteLn("TestNG_DRTEST");
            WriteLn("");//1
            WriteLn("");
            WriteLn("");//3
            WriteLn("");
            WriteLn("");//5
            WriteLn("");
            WriteLn("");//7
            WriteLn("");
            Thread.sleep(2000);
            WriteLn("126314");
            Thread.sleep(1000);
            WriteLn("2");
            Thread.sleep(1000);
/*        String F12 = "\\033[24~";
        //Charset characterSet = Charset.forName("US-ASCII");
        String CTRL_P = "\\016"; //a1 b2 c3 d4 e5 f6 g7 h8 i9 j10 k11 l12 m13 n14 o15 p16
        String UPArrow = "\\033[A~";
        String LEFTArrow = "\\033[D~";
        //WriteLn(F12);*/
            _writer.write('\020');//This is Control P OMG that took forever     16       020        10        CTRL-P        DLE (Data link escape) http://www.unix-manuals.com/refs/misc/ascii-table.html
            _writer.flush();
            Thread.sleep(100);
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[B~").getBytes());
            _writer.flush();
            _writer.write('\015');
            _writer.flush();
            _writer.write('\020');//This is the second set of quitting the entry of sales order
            _writer.flush();
            Thread.sleep(100);
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write(new String("\u001B[D~").getBytes());
            _writer.flush();
            _writer.write('\015');//Press Return on th
            _writer.flush();
            WriteLn("");//Press RETURN to continue
            Thread.sleep(2000);
            WriteLn("99");
            Thread.sleep(2000);
            _writer.write('\020');//This is the third set of quitting the entry of sales order
            _writer.flush();
            Thread.sleep(100);
            WriteLn("99");
            Thread.sleep(120);
            WaitForText("Order #:");
            Thread.sleep(1000);
            WriteLn("99");
            WriteLn("");
            _writer.flush();
            Thread.sleep(4000);
/*            final String SOnumber;
            {
                final Matcher m = Pattern.compile("(?<!\\d)\\d{8}(?!\\d)").matcher(OpenVMSReader.orderNumber);
                if(m.find())
                    SOnumber = m.group(); // retrieve the matched substring
                else
                    SOnumber = null; // no match found
            }*/
            System.out.println("");
            //System.out.println("Ken here is the Order Number from the original buffer out " + SOnumber);
            Thread.sleep(6000);//Lets wait 60 seconds for the flat file to be processed by BIZTALK
            //WriteLn("sea biz_compl18/sin " + SOnumber);
            //WaitForText("|SalesOrder|");//dont move on until we see the Sales Order
            System.out.println("lo");
            x ++;
        } while (300 > x);

/*

            Lets go to the next step

*/
        driverIE.manage().deleteAllCookies();
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com/ucl/login/jsp/universal_login.jsp");
        driverIE.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//implicit wait time
        parentHandle = driverIE.getWindowHandle(); // get the current window handle
        System.out.println(parentHandle + "loginhandleparent");

        //driver.switchTo().window(parentHandle); // switch back to the original window

        /*
                    Call the Universal Log in here
         */

        login_WM_2012 WML = new login_WM_2012(Warehouse);
        Thread.sleep(3000);
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com:20000/wm/inventory/ui/ItemInventoryByLocnList.jsflps");
        Thread.sleep(3000);
        //element = driverIE.findElement(By.id("dataForm:DOList_entityListView:DistributionOrderlist1:field5value1"));
        Thread.sleep(2000);
        WebElement dropDownListBox = driverIE.findElement(By.id("dataForm:listView:filterId:field1value1"));
        dropDownListBox.click();
        //dataForm:listView:filterId:field1value1
        dropDownListBox.sendKeys("C");  // C for CasePick
        dropDownListBox.click();
        Assert.assertTrue(driverIE.getTitle().startsWith("Item Inventory by Location"));
        //dataForm:listView:filterId:locationLookUpId
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.sendKeys("9");
        element.sendKeys("*");
        Thread.sleep(1000);
        //dataForm:listView:filterId:filterIdapply     This is the APPLY button
        element = driverIE.findElement(By.id("dataForm:listView:filterId:filterIdapply"));
        element.click();
        Thread.sleep(3000);
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.clear();
        //element = driverIE.findElement(By.id("dataForm:listView:dataTable:0:dspLocn"));
        //String locn = element.getCssValue(By.id("dataForm:listView:dataTable:0:dspLocn").toString());
        //dataForm:listView:dataTable:0:location
        element = driverIE.findElement(By.id("dataForm:listView:dataTable:2:dspLocn"));//location 2 in TWC had only 1 LPN for the item
        String locn = element.getText();
        System.out.printf(locn);
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.sendKeys(locn);
        Thread.sleep(1000);
        new autoScreenShot(Warehouse);//take a screen shot
        Thread.sleep(1000);
        //dataForm:listView:dataTable:2:ItemBOMDetailsListEV_item_popup_button
        element = driverIE.findElement(By.id("dataForm:listView:dataTable:2:ItemBOMDetailsListEV_item_popup_button"));
        Item = element.getText();
        System.out.printf(Item);
        element = driverIE.findElement(By.id("checkAll_c2_dataForm:listView:dataTable"));
        element.click();
        new autoScreenShot(Warehouse);
        Thread.sleep(1000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
        Thread.sleep(3000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:SignOut()");
        /*
        go to UBS and find the item
         */
        puttyIntUBSsearchItem(Warehouse, Item);
        /*
        go to DOM for a Screen Shot
         */
        Thread.sleep(2000);
        driverIE.navigate().to("http://ctdayq2dm02.unfi.com:18000/ucl/login/jsp/universal_login.jsp");
        try {
            login_DOM_East DOML = new login_DOM_East().login_DOM_East();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        driverIE.navigate().to("http://ctdayq2dm02.unfi.com:19000/doms/dom/inventory/jsp/SupplyBalance.jsflps");
        Thread.sleep(2000);
        element = driverIE.findElement(By.id("dataForm:addItemButton"));
        element.click();
        Thread.sleep(2000);
        element = driverIE.findElement(By.id("dataForm:selectionTbl:newRow_1:skuIDKey"));
        element.sendKeys(Item);//this is the Item from the earlier WM                 dataForm:selectionTbl:newRow_1:facilityKey
        element = driverIE.findElement(By.id("dataForm:selectionTbl:newRow_1:facilityKey"));
        element.clear();
        element.sendKeys(Warehouse);
        element = driverIE.findElement(By.id("dataForm:getSupplyBalanceBtn"));
        element.click();
        Thread.sleep(3000);
        new autoScreenShot(Warehouse);//take a screen shot of the before data
        //javascript:confirmSignOut
        Thread.sleep(1000);
        element = driverIE.findElement(By.id("dataForm:supplyBalanceResultsTbl:0:ot_supplyBalance"));
        String Available = element.getText();//How much does the Warehouse have right now
        ((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
        Thread.sleep(2000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:SignOut()");
        /*
        come back from DOM
         */
         /*
        go to WM to make  the change
         */
        Thread.sleep(5000);
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com/ucl/login/jsp/universal_login.jsp");
        Thread.sleep(3000);
        element = driverIE.findElement(By.id("j_username"));
        element.sendKeys("kwestberg");
        element = driverIE.findElement(By.id("j_password"));
        element.sendKeys("123123");
        element.submit();
        Thread.sleep(3000);
        //WebDriver.getWindowHandles()                later on we are going to address multiple windows in QA
        //String theHandle =  new  login_WM_2012().WM_Login_Test(ctday);
        //driverIE.switchTo().window(String.valueOf(theHandle));
        //String parentHandle = driverIE.getWindowHandle();
        //System.out.println(parentHandle + "loginhandleparent");
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com:20000/wm/inventory/ui/ItemInventoryByLocnList.jsflps");
        Thread.sleep(3000);
        //element = driverIE.findElement(By.id("dataForm:DOList_entityListView:DistributionOrderlist1:field5value1"));
        WebElement dropDownListBox2 = driverIE.findElement(By.id("dataForm:listView:filterId:field1value1"));
        dropDownListBox2.click();
        //dataForm:listView:filterId:field1value1
        dropDownListBox2.sendKeys("C");  // C for CasePick
        dropDownListBox2.click();
        //dataForm:listView:filterId:locationLookUpId
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.sendKeys("9");
        element.sendKeys("*");
        Thread.sleep(1000);//yes 100
        //dataForm:listView:filterId:filterIdapply     This is the APPLY button
        element = driverIE.findElement(By.id("dataForm:listView:filterId:filterIdapply"));
        element.click();
        Thread.sleep(3000);
        new autoScreenShot(Warehouse);
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.clear();
        //element = driverIE.findElement(By.id("dataForm:listView:dataTable:0:dspLocn"));
        //String locn = element.getCssValue(By.id("dataForm:listView:dataTable:0:dspLocn").toString());
        //dataForm:listView:dataTable:0:location
        element = driverIE.findElement(By.id("dataForm:listView:dataTable:2:dspLocn"));//location 2 in TWC had only 1 LPN for the item
        //locn = element.getText();
        System.out.printf(locn);
        element = driverIE.findElement(By.id("dataForm:listView:filterId:locationLookUpId"));
        element.sendKeys(locn);
        Thread.sleep(1000);
        new autoScreenShot(Warehouse);//take a screen shot
        Thread.sleep(1000);
        //dataForm:listView:dataTable:2:ItemBOMDetailsListEV_item_popup_button
        element = driverIE.findElement(By.id("dataForm:listView:dataTable:2:ItemBOMDetailsListEV_item_popup_button"));
        System.out.printf(Item);//This variable was initialized earlier in WM
        element = driverIE.findElement(By.id("checkAll_c2_dataForm:listView:dataTable"));
        element.click();
        //callActionMethod('rm_100204000_menuItemBtn');
        ((JavascriptExecutor) driverIE).executeScript("callActionMethod('rm_100204000_menuItemBtn')");//This is the BUTTON/ACTION for Adjust Inventory
        Thread.sleep(3000);
        //((JavascriptExecutor) driver).executeScript("document.getElementById('" +element.getAttribute("id")+ "').click()");   we are going to need this in LAWSON
        element = driverIE.findElement(By.id("dataForm:NewQtyCases"));
        String strQTYCASES = element.getAttribute("VALUE");
        int QTYCASES = Integer.valueOf(strQTYCASES); //convert String to Integer
        if (QTYCASES > 1) {
            QTYCASES += +1;
        } else QTYCASES = 1;  //add 1 to the Integer
        String updQTYCases = String.valueOf(QTYCASES); // convert the Integer Back to String
        element.clear(); //clear the text box
        element.sendKeys(updQTYCases); //send thenew value to the text box
        //dataForm:adjustReasonSelect
        WebElement reasonDrop = driverIE.findElement(By.id("dataForm:adjustReasonSelect"));

        reasonDrop.click();
        reasonDrop.sendKeys("08 - Cycle Count");//08 - Cycle Count
        reasonDrop.click();
        //rm_154183000_menuItemBtn This is the Save Button in JavaSCript
        ((JavascriptExecutor) driverIE).executeScript("callActionMethod('rm_154183000_menuItemBtn')");//Execute the JAVASCRIPT save
        driverIE.navigate().to("http://ctdaywm" + Warehouse + "qa.unfi.com:20000/wm/inventory/ui/ItemInventoryByLocnList.jsflps?backClicked=true&stackId=Stack0");
        Thread.sleep(2000);
        new autoScreenShot(Warehouse);//take a Screen shot of the update
        //javascript:confirmSignOut
        Thread.sleep(3000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
        Thread.sleep(2000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:SignOut()");
        //put in some assertions
        // now we are testing the inventory change in DOM
        Thread.sleep(2000);
        driverIE.navigate().to("http://ctdayq2dm02.unfi.com:18000/ucl/login/jsp/universal_login.jsp");
        Thread.sleep(3000);
        element = driverIE.findElement(By.id("j_username"));
        element.sendKeys("kwestberg2");//log in as 2 for the EAST
        element = driverIE.findElement(By.id("j_password"));
        element.sendKeys("123456");
        element.submit();
        Thread.sleep(2000);
        driverIE.navigate().to("http://ctdayq2dm02.unfi.com:19000/doms/dom/inventory/jsp/SupplyBalance.jsflps");//DOM login page
        Thread.sleep(2000);
        element = driverIE.findElement(By.id("dataForm:addItemButton"));
        element.click();
        element = driverIE.findElement(By.id("dataForm:selectionTbl:newRow_1:skuIDKey"));
        element.sendKeys(Item);
        //dataForm:getSupplyBalanceBtn
        element = driverIE.findElement(By.id("dataForm:selectionTbl:newRow_1:facilityKey"));
        element.clear();
        element.sendKeys(Warehouse);
        element = driverIE.findElement(By.id("dataForm:getSupplyBalanceBtn"));
        element.click();
        Thread.sleep(2000);
        new autoScreenShot(Warehouse);
        Thread.sleep(2000);
        //dataForm:supplyBalanceResultsTbl:0:ot_supplyBalance
        element = driverIE.findElement(By.id("dataForm:supplyBalanceResultsTbl:0:ot_supplyBalance"));
        String newAvailable = element.getText();
        Thread.sleep(3000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:confirmSignOut()");
        Thread.sleep(2000);
        ((JavascriptExecutor) driverIE).executeScript("javascript:SignOut()");
        /*
        go to UBS to see the updated file in Biz Talk Folder
         */
        puttyIntUBSsearchItem(Warehouse, Item);
        Thread.sleep(6000);
        /*
        go to UBS to see the Item Updated
         */
        puttyIntUBSgetonHand(Warehouse, Item);
        Thread.sleep(3000);

        /*
        TO DO :
        1-assert (newAvailable <> Available) ; //run a check to see if the value has changed up or down
         */
        driverIE.manage().deleteAllCookies();
        driverIE.close();
    }

   @AfterSuite
    public void tearDown() throws Exception {

    }

}
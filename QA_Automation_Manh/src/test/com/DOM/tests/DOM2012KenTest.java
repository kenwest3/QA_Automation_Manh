package com.DOM.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * Created by kwestberg on 3/11/2015.
 */
public class DOM2012KenTest {
    private WebElement element;


    /*
        @Before
        public void setUp() throws Exception {

        }
    */
    @Test
    public void testTWC_test_TEST_Java() throws Exception {
        WebDriver driverIE = new InternetExplorerDriver();
        Actions builder = new Actions(driverIE);
        //login_WM_2012(kwestberg, password);
        element.submit(); Thread.sleep(3000);
        driverIE.navigate().to("http://ctdayqdm02.unfi.com:19000/cbo/transactional/purchaseorder/view/StoreOrderList.jsflps");Thread.sleep(3000);//go to PO page
        element = driverIE.findElement(By.id("dataForm:SOList_entityListView:salesOrderData:0:channelTypeCode"));//selects the topmost element in the grid
        /*
        Trying to double click in the next section
         */
        builder.doubleClick(element = driverIE.findElement(By.id("dataForm:SOList_entityListView:salesOrderData:0:channelTypeCode")));
        builder.moveToElement(element = driverIE.findElement(By.id("dataForm:SOList_entityListView:salesOrderData:0:channelTypeCode"))).click();
        builder.moveToElement(element = driverIE.findElement(By.id("dataForm:SOList_entityListView:salesOrderData:0:channelTypeCode"))).click();
        Thread.sleep(3000);
        driverIE.navigate().to("http://ctdayqdm02.unfi.com:19000/cbo/transactional/purchaseorder/view/StoreOrderList.jsflps#");Thread.sleep(3000);//logout here
    }
}

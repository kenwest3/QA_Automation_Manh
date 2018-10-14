/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
package TestingTools.WBS;
/**
 * Created by kwestberg on 5/13/2015.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import TestingTools.Generic.waitForText;
import TestingTools.Generic.writeLn;
import TestingTools.MSSQL.connectToElmerFindUBSso;
import TestingTools.MSSQL.connectToElmerFindWBSso;
import TestingTools.OpenVMSConnector;
import TestingTools.OpenVMSReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.net.telnet.TelnetClient;
import org.testng.annotations.Test;

public class getWBSitemQTY {
    WebElement element;
    String Warehouse = "AUB";
    String Item = "126314";
    String SalesOrder = "19491338001";
    Integer x = 1;
    /**
     *
     */

    connectToElmerFindWBSso wbsSalesOrder = new connectToElmerFindWBSso(SalesOrder);

    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream();
    public OutputStream _writer = telCLi.getOutputStream();
    //public static WebDriver driverIE = new InternetExplorerDriver();
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

    public void WriteLn(String text) {

        text = text + "\r\n";
        try {
            _writer.write(text.getBytes());
            _writer.flush();
        } catch (IOException ex) {
            System.out.println("Error writing data: " + ex.getMessage());
        }
    }

    @Test
    public void mainTest() throws Exception {
        //ubsSalesOrder.connectToElmerFindUBSso(SalesOrder);
        //System.out.println(ubsSalesOrder.connectToElmerFindUBSso(String.valueOf(ubsSalesOrder)));
        do {

            telCLi.connect("ctdaywbsqa2.unfi.com", 22);
            _reader = telCLi.getInputStream();
            _writer = telCLi.getOutputStream();
            VMSHandler = new OpenVMSReader(_reader, _writer);
            VMSHandler.Start();
            WaitForText("login as:");
            WriteLn("kwestberg");
            WaitForText("@ctdaywbsqa2.unfi.com's password:");
            WriteLn("lmnopkow3");
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
            x++;
        } while (300 > x);
    }
}
    /*
login as: kwestberg
        Sent username "kwestberg"
        kwestberg@ctdaywbsqa2.unfi.com's password:*/
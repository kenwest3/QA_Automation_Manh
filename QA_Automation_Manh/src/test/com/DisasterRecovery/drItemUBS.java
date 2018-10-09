/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.DisasterRecovery;

import TestingTools.MSSQL.connectToElmerFindUBSso;
import TestingTools.OpenVMSReader;
import TestingTools.Oracle.getWMitemCBO;
import org.apache.commons.net.telnet.TelnetClient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by kwestberg on 4/28/2015.
 */
public class drItemUBS {
    WebElement element;
    String Warehouse = "HVA";
    String WHnum = "18";
    String ItemNumber = "126314";
    /**
     *
     */

    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream();
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
        System.out.println("Iam here 1");
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
        WriteLn("1");
        WaitForText("30. Cus Prd Dpt VolDisc Audt Rpt");
        WaitForText("Enter your selection");
        WriteLn("10");
        WaitForText("20. Export UBS Item Data for");
        WaitForText("Enter Program Number:");
        WriteLn("1");
        WaitForText("CORP Prod#:");
        System.out.println("here at Prod");
        WriteLn("126314");//                    ICE TEA,OG2,REG,HLF&HLF
        Thread.sleep(2000);
        WriteLn("2");//19491338001
        Thread.sleep(2000);
        WriteLn("ICE TEA,OG2,REG,DR TEST");
        WriteLn("");
        _writer.write('\020');//This is Control P OMG that took forever     16       020        10        CTRL-P        DLE (Data link escape) http://www.unix-manuals.com/refs/misc/ascii-table.html
        _writer.flush();
        _writer.write(new String("\u001B[A~").getBytes());
        _writer.flush();
        _writer.write(new String("\u001B[A~").getBytes());
        _writer.flush();
        WaitForText("Enter Program Number:");
        _writer.write(new String("\u001B[A~").getBytes());
        _writer.flush();
        _writer.write(new String("\u001B[A~").getBytes());
        _writer.flush();
        Thread.sleep(2000);
        WriteLn("99");
        Thread.sleep(100);
        WriteLn("99");
        WaitForText("Program Terminated Successfully!");
        Thread.sleep(60);//Lets wait 60 seconds for the flat file to be processed by BIZTALK
        WriteLn("sea biz_compl" + WHnum + "/sin " + ItemNumber);
        Thread.sleep(120);
        WaitForText("|STEAZ  ICE TEA,OG2,REG,DR TEST|");//dont move on until we see the Sales Order
        /*
        Go to the WM Data base and get the new Description

        */
        String newWMDescription = new getWMitemCBO().getWMitemCBO(ItemNumber, Warehouse);
        System.out.println(newWMDescription);
        /*
        Go to the DOM Data Base and get the new Description
        */



    }//end of the Main Test
}//end of the class

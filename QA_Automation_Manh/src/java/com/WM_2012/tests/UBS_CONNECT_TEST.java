/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.WM_2012.tests;

import TestingTools.OpenVMSReader;
import de.mud.telnet.TelnetWrapper;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetOption;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by rbailey on 4/23/2015.
 */
public class UBS_CONNECT_TEST {


    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream() ;    //these are defaulting to the JAVA IO
    public OutputStream _writer = telCLi.getOutputStream();
    public static String parentHandle;
    OpenVMSReader VMSHandler;


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

    private void WaitFor(String text) {
        String val = "";
        String newval = "";
        int length = 0;
        byte[] data = new byte[1024];

        long end = System.currentTimeMillis() + 30000;
        try {
            while (System.currentTimeMillis() < end) {
                if (_reader.available() > 0) {
                    length = _reader.read(data, 0, 1024);
                    if (length > 0) {
                        newval = new String(data);
                        data = new byte[2048];
                        System.out.println(newval);
                        val += newval;
                        newval = "";
                        if (val.toLowerCase().contains(text.toLowerCase())) return;
                    }
                }
                Thread.sleep(500);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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

    //TelnetWrapper wrapper = new TelnetWrapper();
    @Test
    public void main() throws Exception {
        System.out.println("Iam here 1");
        telCLi.setReaderThread(true);
             telCLi.connect("QAAS2", 23);
        _reader = telCLi.getInputStream();
        _writer = telCLi.getOutputStream();
        //VMSHandler = new OpenVMSReader(_reader, _writer);
        //VMSHandler.Start();

        WaitFor("Username");

//        WaitForText("Username");
//
        WriteLn("kwestberg");
//
        WaitFor("Password");
        WriteLn("123456");
//
//
//        WaitForText("6. Sales & Marketing");
        WaitFor("Enter your selection");
//        //WaitForText("Contains Applications for the Sales and Marketing Department");
        Thread.sleep(3000);
        WriteLn("6");
//        WaitForText("Advertising Menu");
//        WaitForText("Enter your selection");
//        WriteLn("3");
//        WaitForText("Order Entry / Maintenance");
//        WaitForText("Enter your selection");
//        WriteLn("2");
        //WaitForText("Enter ORDER #");
        System.out.println("Done");
    }
}

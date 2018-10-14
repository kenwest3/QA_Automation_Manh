/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.Generic;

import TestingTools.OpenVMSReader;
import org.apache.commons.net.telnet.TelnetClient;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kwestberg on 5/13/2015.
 */
public class waitForText {

    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream() ;
    public OutputStream _writer = telCLi.getOutputStream();
    OpenVMSReader VMSHandler;
    public void WaitForText(String text) throws InterruptedException {
        String val = "";
        long end = System.currentTimeMillis() + 30000;
        _reader = telCLi.getInputStream();
        _writer = telCLi.getOutputStream();
        VMSHandler = new OpenVMSReader(_reader, _writer);
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

}

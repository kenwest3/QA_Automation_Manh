/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.Generic;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kwestberg on 5/13/2015.
 */
public class writeLn {
    TelnetClient telCLi = new TelnetClient();
    public InputStream _reader = telCLi.getInputStream() ;
    public OutputStream _writer = telCLi.getOutputStream();

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
}

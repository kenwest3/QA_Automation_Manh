package org.apache.commons.net.telnet;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by kwestberg on 3/12/2015.
 */
public class SyncPipe implements Runnable
{
    public SyncPipe(InputStream istrm, PrintStream ostrm) {
        istrm_ = istrm;
        ostrm_ = ostrm;
    }
    public void run() {
        try
        {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = istrm_.read(buffer)) != -1; )
            {
                ostrm_.write(buffer, 0, length);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private final OutputStream ostrm_;
    private final InputStream istrm_;
}
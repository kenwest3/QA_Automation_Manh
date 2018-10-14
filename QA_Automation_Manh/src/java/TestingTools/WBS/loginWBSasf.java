/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.WBS;


import oracle.net.jdbc.TNSAddress.Description;
import org.apache.commons.net.ssh.SSHClient;
import org.apache.commons.net.ssh.connection.Channel;
import org.apache.commons.net.ssh.connection.Session;
import org.apache.commons.net.ssh.util.Pipe;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwestberg on 5/27/2015.
 */
public class loginWBSasf implements Runnable {

    public SSHClient client = new SSHClient();

    @BeforeSuite
    public void setUp() throws Exception {


    }
    @Test(description = "logintoWBS")
    public void goTestWBS() throws IOException, InterruptedException {
        String sshuser =  "kwestberg" ;
        String sshpass = "lmnopkow3" ;
        String sshhost = "ctdaywbsqa2";
        String command = "ls";





        client.addHostKeyVerifier("ctdaywbsqa2", "f0:06:49:48:05:e9:46:7c:7a:b2:74:2a:43:1b:55:79");
        client.connect("ctdaywbsqa2");




        //org.apache.commons.net.ssh.util.Buffer buff = new org.apache.commons.net.ssh.util.Buffer();
        //buff.readBytes();
        try {
            //client.allocateDefaultPTY();

            client.authPassword( "kwestberg","lmnopkow3");
            Session session = client.startSession();
            Session.Shell shell = session.startShell();

            //client.getConnection().join();
            System.out.println("here in ssh session");
            //session.exec("ls");
            System.out.print(session.getOutputStream().toString());
            System.out.println("\n** exit status: " + session.isOpen());
            new Pipe("stdout", shell.getInputStream(), System.out) //
                    .bufSize(shell.getLocalMaxPacketSize()) //
                    .start();
            System.out.println("");
            System.out.println("here in ssh session");
            new Pipe("stderr", shell.getErrorStream(), System.err) //
                    .bufSize(shell.getLocalMaxPacketSize()) //
                    .start();
            //what goes here to send and receive the buffer
            System.out.println("here also");
            //BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Pipe.pipe(System.in, shell.getOutputStream(), shell.getRemoteMaxPacketSize(), true); // ---this is causing a hang
            Channel channel = session.exec("ls -l\n");
            channel.getOutputStream().equals(System.out);
            InputStream in = channel.getInputStream();
            System.out.println("here");
            Thread.sleep(6000);
        } finally {
            client.disconnect();
        }
    }
    /***
     * Reader thread.
     * Reads lines from the TelnetClient and echoes them
     * on the screen.
     ***/
//    @Override
    public void run()
    {

        /*InputStream instr = tc.getInputStream();


        try
        {
            byte[] buff = new byte[1024];
            int ret_read = 0;

            do
            {
                ret_read = instr.read(buff);
                if(ret_read > 0)
                {
                    System.out.print(new String(buff, 0, ret_read));
                }
            }
            while (ret_read >= 0);
        }
        catch (IOException e)
        {
            System.err.println("Exception while reading socket:" + e.getMessage());
        }

        try
        {
            tc.disconnect();
        }
        catch (IOException e)
        {
            System.err.println("Exception while closing telnet:" + e.getMessage());
        }
        */
    }
}

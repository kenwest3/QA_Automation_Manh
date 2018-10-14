/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.WBS;

/**
 * Created by kwestberg on 6/9/2015.
 *
 * @author 'Ken Westberg'
 *         selQAjavaAutomation
 *         UnknownHostKey: ctdaywbsqa2. RSA key fingerprint is f0:06:49:48:05:e9:46:7c:7a:b2:74:2a:43:1b:55:79
 */
import com.jcraft.jsch.*;
import com.jcraft.jsch.jcraft.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.*;

public class jschSSHmanager {

private static final Logger LOGGER = Logger.getLogger(jschSSHmanager.class.getName());
private JSch jschSSHChannel;
private String strUserName;
private String strConnectionIP;
private int intConnectionPort;
private String strPassword;
private Session sesConnection;
private int intTimeOut;

    private void doCommonConstructorActions(String userName,
                                            String password, String connectionIP, String knownHostsFileName)
    {
        jschSSHChannel = new JSch();

        try
        {
            jschSSHChannel.setKnownHosts(knownHostsFileName);
        }
        catch(JSchException jschX)
        {
            logError(jschX.getMessage());
        }

        strUserName = userName;
        strPassword = password;
        strConnectionIP = connectionIP;
    }

    public jschSSHmanager(String userName, String password,
                      String connectionIP, String knownHostsFileName)
    {
        doCommonConstructorActions(userName, password,
                connectionIP, knownHostsFileName);
        intConnectionPort = 22;
        intTimeOut = 60000;
    }

    public jschSSHmanager(String userName, String password, String connectionIP,
                      String knownHostsFileName, int connectionPort)
    {
        doCommonConstructorActions(userName, password, connectionIP,
                knownHostsFileName);
        intConnectionPort = connectionPort;
        intTimeOut = 60000;
    }

    public jschSSHmanager(String userName, String password, String connectionIP,
                      String knownHostsFileName, int connectionPort, int timeOutMilliseconds)
    {
        doCommonConstructorActions(userName, password, connectionIP,
                knownHostsFileName);
        intConnectionPort = connectionPort;
        intTimeOut = timeOutMilliseconds;
    }

    public String connect()
    {
        String errorMessage = null;

        try
        {
            sesConnection = jschSSHChannel.getSession(strUserName,
                    strConnectionIP, intConnectionPort);
            sesConnection.setPassword(strPassword);
            // UNCOMMENT THIS FOR TESTING PURPOSES, BUT DO NOT USE IN PRODUCTION
            sesConnection.setConfig("StrictHostKeyChecking", "no");
            sesConnection.connect(intTimeOut);
        }
        catch(JSchException jschX)
        {
            errorMessage = jschX.getMessage();
        }

        return errorMessage;
    }

    private String logError(String errorMessage)
    {
        if(errorMessage != null)
        {
            LOGGER.log(Level.SEVERE, "{0}:{1} - {2}",
                    new Object[]{strConnectionIP, intConnectionPort, errorMessage});
        }

        return errorMessage;
    }

    private String logWarning(String warnMessage)
    {
        if(warnMessage != null)
        {
            LOGGER.log(Level.WARNING, "{0}:{1} - {2}",
                    new Object[]{strConnectionIP, intConnectionPort, warnMessage});
        }

        return warnMessage;
    }

    public String sendCommand(String command)
    {
        StringBuilder outputBuffer = new StringBuilder();

        try
        {
            Channel channel = sesConnection.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();

            while(readByte != 0xffffffff)
            {
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }

            channel.disconnect();
        }
        catch(IOException ioX)
        {
            logWarning(ioX.getMessage());
            return null;
        }
        catch(JSchException jschX)
        {
            logWarning(jschX.getMessage());
            return null;
        }

        return outputBuffer.toString();
    }

    public void close()
    {
        sesConnection.disconnect();
    }
    @Test
    public static void testSendCommand()
    {
        System.out.println("sendCommand");

        /**
         * YOU MUST CHANGE THE FOLLOWING
         * FILE_NAME: A FILE IN THE DIRECTORY
         * USER: LOGIN USER NAME
         * PASSWORD: PASSWORD FOR THAT USER
         * HOST: IP ADDRESS OF THE SSH SERVER
         **/
        String command1 = "echo $TERM";
        String userName = "kwestberg";
        String password = "lmnopkow3";
        String connectionIP = "ctdaywbsqa2";
        jschSSHmanager instance = new jschSSHmanager(userName, password, connectionIP, "");
        String errorMessage = instance.connect();

        if(errorMessage != null)
        {
            System.out.println(errorMessage);

        }

        String expResult = "dumb\n";
        // call sendCommand for each command and the output
        //(without prompts) is returned
        String result = instance.sendCommand(command1);
        // close only after all commands are sent
        instance.close();
        Assert.assertEquals(expResult, result);
    }
}
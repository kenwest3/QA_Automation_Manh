/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.WBS;

/**
 * Created by kwestberg on 6/3/2015.
 *
 * @author 'Ken Westberg'
 *         selQAjavaAutomation
 */
import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class loginWBSjschCMD {
    /**      * @param args      */
    @BeforeSuite
    public void setUp() throws Exception {


    }
    @Test(description = "logintoWBSCMD")
    public static void kenGO() throws IOException {
        String host = "ctdaywbsqa2";
        String user = "kwestberg";
        String password = "lmnopkow3";
        String command2 = "ls";
        String command3 = "one";
        String command1 = "echo $TERM";
        Session session = null;
        try {
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            //jsch.setKnownHosts("C:\\Selenium_test_cases\\selQAjavaAutomation\\known_hosts.txt"); //BASE 64 error when using correct fingerprint?????
            session = jsch.getSession(user, host, 22);
            UserInfo ui = new MyUserInfo();
            session.setUserInfo(ui);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            //("f0:06:49:48:05:e9:46:7c:7a:b2:74:2a:43:1b:55:79");
            System.out.println("Connected Helleuuur 1");
            Channel channel = session.openChannel("exec");
            //((ChannelExec)channel).setCommand(command1);
            ((ChannelExec) channel).setPty(true);
            ((ChannelExec) channel).setPtyType("VT102");
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect(90000);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            byte[] tmp = new byte[1024];
            ((ChannelExec) channel).setCommand(command1);
            // ((ChannelExec)channel).setCommand(command3);
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }

                System.out.println("done");
                //channel.disconnect();  // this closes the jsch channel

                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            ((ChannelExec) channel).setCommand(command2);
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    System.out.print(new String(tmp, 0, i));
                }

                System.out.println("done");
                //channel.disconnect();  // this closes the jsch channel

                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            ((ChannelExec) channel).setCommand(command2);
            //((ChannelExec)channel).setCommand(command3);
            session.disconnect();
            System.out.println("DONE");
        } catch (JSchException e1) {
            e1.printStackTrace();
        }
        //((ChannelExec)channel).setCommand(command3);
        session.disconnect();
        System.out.println("DONE");
    }
}

/*
            ((ChannelExec)channel).setCommand(command2);
            //((ChannelExec)channel).setCommand(command3);
            channel.connect();
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 2048);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }channel.disconnect();

 */
/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package TestingTools.WBS;

import com.jcraft.jsch.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FilterInputStream;
import java.io.IOException;

/**
 * Created by kwestberg on 6/2/2015.
 *
 * @author 'Ken Westberg'
 *         selQAjavaAutomation
 */
public class loginWBSjsch {

    @BeforeSuite
    public void setUp() throws Exception {


    }
    @Test(description = "logintoWBSjsch")
    public void main(){
        String cmd = "ls\n";

        try{
            JSch jsch=new JSch();
            //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");
            String host="ctdaywbsqa2";
            String user="kwestberg";
            String passwd = "lmnopkow3";
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(passwd);
            session.setConfig("StrictHostKeyChecking", "no");
            //session.setConfig();

            UserInfo ui = new MyUserInfo(){
/*                public void showMessage(String message){
                    JOptionPane.showMessageDialog(null, message);
                }
                public boolean promptYesNo(String message){
                    Object[] options={ "yes", "no" };
                    int foo=JOptionPane.showOptionDialog(null,
                            message,
                            "Warning",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    return foo==0;
                }*/

                // If password is not given before the invocation of Session#connect(),
                // implement also following methods,
                //   * UserInfo#getPassword(),
                //   * UserInfo#promptPassword(String message) and
                //   * UIKeyboardInteractive#promptKeyboardInteractive()

            };

            session.setUserInfo(ui);

            // It must not be recommended, but if you want to skip host-key check,
            // invoke following,
            // session.setConfig("StrictHostKeyChecking", "no");

            //session.connect();
            session.connect();   // making a connection with timeout.

            Channel channel=session.openChannel("shell");
            //((ChannelExec)channel).setCommand(cmd);
            //channel.setInputStream(null);
            //((ChannelExec)channel).setErrStream(System.err);


            // Enable agent-forwarding.
            //((ChannelShell)channel).setAgentForwarding(true);

            channel.setInputStream(System.in);

      // a hack for MS-DOS prompt on Windows.
      channel.setInputStream(new FilterInputStream(System.in){
          public int read(byte[] b, int off, int len)throws IOException {
            return in.read(b, off, (len>1024?1024:len));
          }
        });


            channel.setOutputStream(System.out);

      // Choose the pty-type "vt102".
      ((ChannelShell)channel).setPtyType("vt102");
      /*
      // Set environment variable "LANG" as "ja_JP.eucJP".
      ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
      */

            //channel.connect();
            channel.connect(3*1000);
            while(channel.isConnected()){
                try {
                    System.out.println("");
                    System.out.println("one");
                    Thread.sleep(3000);
                    //channel.connect(1000);
                } catch (InterruptedException e){
                    e.printStackTrace(); System.out.println("[SSHClient] Thread interrupted");
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static abstract class MyUserInfo
            implements UserInfo, UIKeyboardInteractive{
        public String getPassword(){ return null; }
        public boolean promptYesNo(String str){ return false; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo){
            return null;
        }
    }

}

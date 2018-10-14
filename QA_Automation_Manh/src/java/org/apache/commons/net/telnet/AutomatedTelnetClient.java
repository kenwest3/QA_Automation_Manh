package org.apache.commons.net.telnet;

import java.io.InputStream;
import java.io.PrintStream;

public class AutomatedTelnetClient {
    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "$";

    public AutomatedTelnetClient(String server, String user, String password) {
        try {
            // Connect to the specified server
            telnet.connect(server, 23);

            // Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            // Log the user on
            readUntil("Username: ");
            write(user);
            readUntil("Password: ");
            write(password);
            Thread.sleep(10000);

            // Advance to a prompt
            //readUntil(prompt + "[c");
            readUntil("[c");
            //write("[?62;1c");
            //write( "CSI 61\"p");
            write ("\\Z");
            readUntil("[c");
            write( "ESC[?1;Psc");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void su(String password) {
        try {
            write("su");
            readUntil("Password: ");
            write(password);
            prompt = "$";
            readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) {
        try {
            write(command);
            return readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            AutomatedTelnetClient telnet = new AutomatedTelnetClient(
                    "QAAS2", "kwestberg", "123456");
            Thread.sleep(5000);
            System.out.println("trying to go somewhere...");
            telnet.sendCommand("[?61;0c");
            telnet.sendCommand("menu");
            telnet.sendCommand("ps -ef ");
            System.out.println("run command");
            telnet.sendCommand("ls ");
            System.out.println("run command 2");
            telnet.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


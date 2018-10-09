package test.com.WM_2012.tests;

import de.mud.telnet.TelnetWrapper;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kwestberg on 3/24/2015.
 */
public class CasePickCCstream {
    static String Warehouse = "TWC";
    static String Item = "123456";
    static final String lpn = "AUTOLPN32000";

    public static void Go(String host[], String[] login, String[] password, String[] prompt) throws IOException {

        //if(Go(().length != 4) throw new
          //      IllegalArgumentException("Usage: TelnetWrapper host login password prompt");


        //String host = args[0];
        //String login = args[1];
        //String password = args[2];
        //String prompt = args[3];

        Date now = new Date();
        String timestamp = now.getYear() + "-" +
                (now.getMonth() +1) + "-" + now.getDate() + "-" +
                now.getHours() + ":" + now.getMinutes() + ":" +
                now.getSeconds();
        TelnetWrapper telnet = new TelnetWrapper();

        try {


            telnet.connect(host[0], 23);
            telnet.login("user", "password");
            telnet.setPrompt("user@host");
            telnet.waitfor("Terminal type?");
            telnet.send("dumb");
            System.out.println(telnet.send("ls -l"));
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }

        /*
        String[] command =
                {
                        "cmd",
                };
        Process p = Runtime.getRuntime().exec(command);   // This is necessary for the overall in and out streams
        List<locationData> locations = getLocnBrcdjdbcConnectGIL.getLocnBRCD(Item, Warehouse);
        String line;
        Scanner scan = new Scanner(System.in);
*/

        List<String> commands = new ArrayList<String>();
        commands.add("putty");
        commands.add("-load");
        commands.add("QA2TWC");


        //Process process = Runtime.getRuntime ().exec ("/bin/bash");
        ProcessBuilder builder = new ProcessBuilder(commands);  // "putty -load QA2TWC"
        builder.redirectErrorStream(true);
        Process process = builder.start();

        OutputStream stdin = process.getOutputStream();
        InputStream stderr = process.getErrorStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        try {
            //Thread.sleep(4000);//wait for it to load

            System.out.println("ken Westberg zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");


            System.out.println(reader.readLine());
            System.out.println(reader.readLine());


            Thread.sleep(5000);

            /*
            while (scan.hasNext()) {
                System.out.println("Inside the loop");
                String input = scan.nextLine();
                if (input.trim().equals("exit")) {
                    // Putting 'exit' amongst the echo --EOF--s below doesn't work.
                    writer.write("exit\n");
                } else {
                    writer.write("((" + input + ") && echo --EOF--) || echo --EOF--\n");
                }
                writer.flush();

                line = reader.readLine();
                while (line != null && !line.trim().equals("--EOF--")) {
                    System.out.println("Stdout: " + line);
                    line = reader.readLine();
                }
                if (line == null) {
                    break;
                }
                writer.write("kwestber");
                writer.flush();
                Thread.sleep(400);
                writer.write("lmnopkow3");
                writer.flush();


            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

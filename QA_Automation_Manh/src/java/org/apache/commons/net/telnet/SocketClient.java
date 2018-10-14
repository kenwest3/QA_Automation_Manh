package org.apache.commons.net.telnet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A Simple Socket client that connects to our socket server
 * @author faheem
 *
 */
public class SocketClient {

    private String hostname;
    private int port;
    Socket socketClient;

    public SocketClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException{
        System.out.println("Attempting to connect to "+hostname+":"+port);
        socketClient = new Socket(hostname,port);
        System.out.println("Enter Your Selection:");
    }

    public void readResponse() throws IOException{
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        System.out.println("Response from server:");
        while ((userInput = stdIn.readLine()) != null) {
            System.out.println(userInput);
        }
    }

    public void writeResponse(String useroutput) throws IOException{
        String sendoutput = useroutput;
        BufferedWriter stdOut = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
        stdOut.write(sendoutput);
        stdOut.newLine();
        stdOut.flush();
    }

    public static void main(String arg[]){
        //Creating a SocketClient object
        SocketClient client = new SocketClient ("QAAS2",23);
        try {
            //trying to establish connection to the server
            client.connect();
            //if successful, read response from server
            client.readResponse();

        } catch (UnknownHostException e) {
            System.err.println("Host unknown. Cannot establish connection");
        } catch (IOException e) {
            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
        }
    }
}
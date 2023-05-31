package edu.sdccd.cisc191.template;

import javafx.application.Application;

import java.net.*;
import java.io.*;

/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

// MODULE 14

public class Client {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private static final Object lock = new Object();

    public static Response sendRequest(String id, String[] val) throws Exception {
        out.println(Request.toJSON(new Request(name, id, val)));
        return Response.fromJSON(in.readLine());
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static String name = "";

    public static void main(String[] args) {
        Client client = new Client();
        Thread FXThread = new Thread(() -> {
            Application.launch(FXApplication.class);
        });
        try {
            client.startConnection("127.0.0.1", 4444);
            name = Asker.answer("What is your name? ");
            FXThread.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Client
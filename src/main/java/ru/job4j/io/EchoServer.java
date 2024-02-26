package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    
    private static void checkMessage(ServerSocket server, String message, OutputStream output) throws IOException {
        if (message.contains("msg=Hello")) {
            output.write("Hello\r\n".getBytes());
        } else if (message.contains("msg=Exit")) {
            output.write("Close server\r\n".getBytes());
            server.close();
        } else {
            output.write("What\r\n".getBytes());
        }
    }

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                    BufferedReader input = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String firstHTTPMessage = input.readLine();
                    System.out.println(firstHTTPMessage);
                    checkMessage(server, firstHTTPMessage, output);

                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}

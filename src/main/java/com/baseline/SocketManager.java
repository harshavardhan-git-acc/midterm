package com.baseline;
import java.io.*;
import java.net.*;
public class SocketManager {
    public String receiveMessage(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(5000);
            try (Socket socket = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                return reader.readLine();
            }
        }
    }
    public void sendMessage(String host, int port, String message) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            writer.println(message);
        }
    }
}

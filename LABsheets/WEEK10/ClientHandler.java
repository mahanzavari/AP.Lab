import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String userName;
    private Socket socket;
    private PrintWriter printWriter;
    private InputStream inStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            OutputStream output = socket.getOutputStream();
            printWriter = new PrintWriter(output, true);
            inStream = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            printWriter.println("Welcome to the chat! please enter your name:");
            userName = in.readLine();
            ChatServer.broadcast(userName + " joined the chat!", this, "\u001B[32m");
            String userMessage;
            while ((userMessage = in.readLine()) != null) {
                if (userMessage.equals("#exit")){
                    ChatServer.broadcast(userName + " left the chat!", this, "\u001B[31m");
                    break;
                }
                ChatServer.broadcast(userName + ": " + userMessage, this);
            }
        } catch (IOException e) {
            ChatServer.broadcast(userName + " had an error: " + e.getMessage(), this, "\u001B[31m");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing the socket for user " + userName);
            }
        }
    }

    public void sendMessage(String message) {
        printWriter.println(message);
    }

    public void sendMessage(String message, String ansi) {
        printWriter.println(ansi + message + "\u001B[0m");
    }
}
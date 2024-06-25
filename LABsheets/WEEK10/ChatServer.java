import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static final int PORT = 3044;
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("\u001B[31m" + "******* Welcome to chatroom! *******" + "\u001B[0m");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandlers.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    public static void broadcast(String message, ClientHandler user) {
        System.out.println(message);
        for (ClientHandler clientHandler : clientHandlers){
            if (clientHandler != user){
                clientHandler.sendMessage(message);
            }
        }
    }

    public static void broadcast(String message, ClientHandler user, String ansi) {
        System.out.println(ansi + message + "\u001B[0m");
        for (ClientHandler clientHandler : clientHandlers){
            if (clientHandler != user){
                clientHandler.sendMessage(ansi + message + "\u001B[0m");
            }
        }
    }
}
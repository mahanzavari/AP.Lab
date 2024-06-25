import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SudokuClient_MultiThreaded {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter the 81 numbers of the Sudoku (comma-separated, row by row):");
            String input = scanner.nextLine();
            out.println(input);

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}

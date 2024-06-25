import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SudokuClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static final int N = 9;

    public static void main(String[] args) {
        int[][] grid = new int[N][N];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (9 lines of 9 space-separated numbers, 0 for empty cells):");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Do you want to check if the Sudoku is valid or solve it?");
        System.out.println("Enter 'check' to validate or 'solve' to solve:");
        String action = scanner.next();

        boolean isCheckOnly = action.equalsIgnoreCase("check");

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send the action to the server
            out.println(isCheckOnly);

            // Sending the Sudoku puzzle to the server
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    out.print(grid[i][j] + " ");
                }
                out.println();
            }

            // Receiving the result from the server
            System.out.println("\nServer response:\n");
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SudokuClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static final int N = 9;

    public static void main(String[] args) {
        int[][] grid = new int[N][N];

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter the Sudoku puzzle (9 lines of 9 space-separated numbers, 0 for empty cells):");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = scanner.nextInt();
                }
            }

            // Sending the Sudoku puzzle to the server
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    out.print(grid[i][j] + " ");
                }
                out.println();
            }

            // Receiving the solved Sudoku puzzle from the server
            System.out.println("\nThe solved Sudoku is:\n");
            for (int i = 0; i < N; i++) {
                String[] line = in.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    System.out.print(line[j] + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

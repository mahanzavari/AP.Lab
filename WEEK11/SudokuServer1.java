import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SudokuServer {

    private static final int PORT = 12345;
    private static final int N = 9;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sudoku Server is running...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    int[][] grid = new int[N][N];

                    // Reading the Sudoku puzzle from the client
                    for (int i = 0; i < N; i++) {
                        String[] line = in.readLine().split(" ");
                        for (int j = 0; j < N; j++) {
                            grid[i][j] = Integer.parseInt(line[j]);
                        }
                    }

                    if (solveSudoku(grid, 0, 0)) {
                        // Sending the solved Sudoku back to the client
                        for (int i = 0; i < N; i++) {
                            for (int j = 0; j < N; j++) {
                                out.print(grid[i][j] + " ");
                            }
                            out.println();
                        }
                    } else {
                        out.println("No solution exists");
                    }
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean solveSudoku(int[][] grid, int row, int col) {
        if (row == N - 1 && col == N) return true;
        if (col == N) {
            row++;
            col = 0;
        }
        if (grid[row][col] != 0) return solveSudoku(grid, row, col + 1);

        for (int num = 1; num <= N; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < N; x++) {
            if (grid[row][x] == num || grid[x][col] == num) return false;
        }

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) return false;
            }
        }
        return true;
    }
}

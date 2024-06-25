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
                    boolean isCheckOnly = Boolean.parseBoolean(in.readLine());

                    // Reading the Sudoku puzzle from the client
                    for (int i = 0; i < N; i++) {
                        String[] line = in.readLine().split(" ");
                        for (int j = 0; j < N; j++) {
                            grid[i][j] = Integer.parseInt(line[j]);
                        }
                    }

                    if (isCheckOnly) {
                        if (isValidSudoku(grid)) {
                            out.println("The provided Sudoku is valid.");
                        } else {
                            out.println("The provided Sudoku is invalid.");
                        }
                    } else {
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

    private static boolean isValidSudoku(int[][] grid) {
        for (int i = 0; i < N; i++) {
            boolean[] rowCheck = new boolean[N + 1];
            boolean[] colCheck = new boolean[N + 1];
            boolean[] boxCheck = new boolean[N + 1];
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0 && rowCheck[grid[i][j]]) return false;
                if (grid[j][i] != 0 && colCheck[grid[j][i]]) return false;

                int boxRow = 3 * (i / 3) + j / 3;
                int boxCol = 3 * (i % 3) + j % 3;
                if (grid[boxRow][boxCol] != 0 && boxCheck[grid[boxRow][boxCol]]) return false;

                rowCheck[grid[i][j]] = true;
                colCheck[grid[j][i]] = true;
                boxCheck[grid[boxRow][boxCol]] = true;
            }
        }
        return true;
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

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class SudokuClient_MultiThreaded {

    private static final int PORT = 12345;
    private static final int N = 9;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sudoku Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected...");
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String input;
                while ((input = in.readLine()) != null) {
                    String[] numbers = input.split(",");
                    if (numbers.length != 81) {
                        out.println("Invalid input. Please send exactly 81 numbers.");
                        continue;
                    }

                    int[][] grid = new int[N][N];
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            grid[i][j] = Integer.parseInt(numbers[i * N + j]);
                        }
                    }

                    if (isValidSudoku(grid)) {
                        if (solveSudoku(grid, 0, 0)) {
                            out.println("Sudoku Solved:");
                            for (int[] row : grid) {
                                out.println(formatRow(row));
                            }
                        } else {
                            out.println("No Solution exists for the given Sudoku.");
                        }
                    } else {
                        out.println("Invalid Sudoku.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean solveSudoku(int[][] grid, int row, int col) {
            if (row == N - 1 && col == N) return true;
            if (col == N) {
                row++;
                col = 0;
            }
            if (grid[row][col] != 0) return solveSudoku(grid, row, col + 1);

            for (int num = 1; num <= 9; num++) {
                if (isSafe(grid, row, col, num)) {
                    grid[row][col] = num;
                    if (solveSudoku(grid, row, col + 1)) return true;
                    grid[row][col] = 0;
                }
            }
            return false;
        }

        private boolean isSafe(int[][] grid, int row, int col, int num) {
            for (int x = 0; x < N; x++) if (grid[row][x] == num || grid[x][col] == num) return false;
            int startRow = row - row % 3, startCol = col - col % 3;
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) if (grid[i + startRow][j + startCol] == num) return false;
            return true;
        }

        private boolean isValidSudoku(int[][] grid) {
            for (int i = 0; i < N; i++) {
                boolean[] rowCheck = new boolean[N];
                boolean[] colCheck = new boolean[N];
                boolean[] boxCheck = new boolean[N];

                for (int j = 0; j < N; j++) {
                    if (grid[i][j] != 0 && rowCheck[grid[i][j] - 1]) return false;
                    rowCheck[grid[i][j] - 1] = true;

                    if (grid[j][i] != 0 && colCheck[grid[j][i] - 1]) return false;
                    colCheck[grid[j][i] - 1] = true;

                    int rowIndex = 3 * (i / 3) + j / 3;
                    int colIndex = 3 * (i % 3) + j % 3;
                    if (grid[rowIndex][colIndex] != 0 && boxCheck[grid[rowIndex][colIndex] - 1]) return false;
                    boxCheck[grid[rowIndex][colIndex] - 1] = true;
                }
            }
            return true;
        }

        private String formatRow(int[] row) {
            StringBuilder sb = new StringBuilder();
            for (int num : row) sb.append(num).append(",");
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
    }
}

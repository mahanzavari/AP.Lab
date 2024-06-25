import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {

    static int N = 9;

    static boolean solveSudoku(int grid[][], int row, int col) {
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

    static void print(int[][] grid) {
        System.out.println("*******************************");
        System.out.println("\nThe solved Sudoku is:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n*******************************");
    }

    static boolean isSafe(int[][] grid, int row, int col, int num) {
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

    static void readAndSolveSudoku(File file) {
        int[][] grid = new int[N][N];
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (scanner.hasNextInt()) {
                        grid[i][j] = scanner.nextInt();
                    } else {
                        throw new IllegalArgumentException("Invalid input format in file: " + file.getName());
                    }
                }
            }
            System.out.println("File " + file.getName() + " read successfully!");
            if (solveSudoku(grid, 0, 0)) {
                print(grid);
            } else {
                System.out.println("No solution exists for " + file.getName());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file.getAbsolutePath());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        File[] files = {
            new File(userHome + "/Desktop/easy.txt"),
            new File(userHome + "/Desktop/medium.txt"),
            new File(userHome + "/Desktop/hard.txt")
        };

        for (File file : files) {
            readAndSolveSudoku(file);
        }
    }
}

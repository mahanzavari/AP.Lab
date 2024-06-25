import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SudokuClientController {

    @FXML
    private GridPane gridPane;

    @FXML
    private Text responseText;

    @FXML
    public void initialize() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setPrefWidth(30);
                gridPane.add(textField, j, i);
            }
        }
    }

    @FXML
    private void handleCheckSudoku() {
        sendSudokuToServer(true);
    }

    @FXML
    private void handleSolveSudoku() {
        sendSudokuToServer(false);
    }

    private void sendSudokuToServer(boolean isCheckOnly) {
        int[][] grid = new int[9][9];
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    TextField textField = (TextField) getNodeByRowColumnIndex(i, j);
                    String text = textField.getText().trim();
                    grid[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                }
            }
        } catch (NumberFormatException e) {
            responseText.setText("Please enter valid numbers (0-9).");
            return;
        }

        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(isCheckOnly);

            for (int[] row : grid) {
                for (int num : row) {
                    out.print(num + " ");
                }
                out.println();
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line).append("\n");
            }

            responseText.setText(response.toString());

        } catch (IOException e) {
            responseText.setText("Error connecting to the server.");
            e.printStackTrace();
        }
    }

    private TextField getNodeByRowColumnIndex(final int row, final int column) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                return (TextField) node;
            }
        }
        return null;
    }
}

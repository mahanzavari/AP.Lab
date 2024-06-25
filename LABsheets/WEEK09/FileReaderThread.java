import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderThread extends Thread {
    private final String fileName;
    private final FileInfoTracker fileInfoTracker;

    public FileReaderThread(String fileName, FileInfoTracker fileInfoTracker) {
        this.fileName = fileName;
        this.fileInfoTracker = fileInfoTracker;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String currentWord;
            while ((currentWord = reader.readLine()) != null) {
                fileInfoTracker.updateInfo(currentWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

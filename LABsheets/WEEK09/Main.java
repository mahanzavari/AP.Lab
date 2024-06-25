public class Main {
    public static void main(String[] args) throws InterruptedException {
        FileInfoTracker fileInfoTracker = new FileInfoTracker();
        Thread[] threads = new Thread[20];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new FileReaderThread("./assets/file_" + (i + 1) + ".txt", fileInfoTracker);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join(); // Wait for all threads to finish
        }

        System.out.print("'" + fileInfoTracker.getLargestWord() + "'" + " is the largest word with the length of ");
        System.out.println(fileInfoTracker.getLargestLength());
        System.out.print("'" + fileInfoTracker.getSmallestWord() + "'" + " is the smallest word with the length of ");
        System.out.println(fileInfoTracker.getSmallestLength());
        System.out.println("Total count of words is: " + fileInfoTracker.getWordCount());
        System.out.println("Total count of distinct words is: " + fileInfoTracker.getDistinctWordCount());
        System.out.println("The average of word length is: " + fileInfoTracker.getAverageWordLength());

    }
}

import java.util.HashSet;

public class FileInfoTracker {
    private String smallestWord;
    private String largestWord;
    private int smallestLength;
    private int largestLength;
    private int wordCount;
    private int wordLengthCount;

    private HashSet<String> distinctWords;

    public FileInfoTracker(){
        smallestLength = Integer.MAX_VALUE;
        largestLength = 0;
        wordCount = wordLengthCount = 0;
        distinctWords = new HashSet<>();
    }

    public synchronized void updateInfo(String word){
        int len = word.length();

        wordCount++;
        wordLengthCount += len;
        distinctWords.add(word);

        if (len > largestLength){
            largestLength = len;
            largestWord = word;
        }

        if (len < smallestLength){
            smallestLength = len;
            smallestWord = word;
        }
    }

    public int getLargestLength() {
        return largestLength;
    }

    public int getSmallestLength() {
        return smallestLength;
    }

    public String getSmallestWord() {
        return smallestWord;
    }

    public String getLargestWord() {
        return largestWord;
    }

    public float getAverageWordLength(){
        return ((float) wordLengthCount) / wordCount;
    }

    public int getWordCount(){
        return wordCount;
    }

    public int getDistinctWordCount(){
        return distinctWords.size();
    }

    public HashSet<String> getDistinctWords() {
        return distinctWords;
    }
}

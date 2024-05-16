class Solution {

    public String longestPalindrome(String s) {
        int n = s.length(), start = 0, end = 0;
        boolean[][] boolarray = new boolean[n][n];
        for (int length=0; length<n; length++) {
            for (int i=0; i+length<n; i++) {
                boolarray[i][i+length] = s.charAt(i) == s.charAt(i+length)
                    && (length < 2 || boolarray[i+1][i+length-1]);
                if (boolarray[i][i+length] && length > end - start) {
                    start = i;
                    end = i + length;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
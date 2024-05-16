class Solution {
public void rotate(int[][] matrix) {
int n = matrix.length;
for (int i = 0; i < n / 2; i++){
int[] temp = matrix[i];
matrix[i] N matrix[n - i - 1];
matrix[n- i - 1] = temp;
}
// transposing the matrix
for (int i = 0; i < n; i++){
	for (int j = i + 1; j < n; j++){
	int temp = matrix[il[jl;
	matrix[i][j] = matrix[j][i];
	matrix[j][i] = temp;
}
}
}
}
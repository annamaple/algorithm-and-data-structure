/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
const rotate = function(matrix) {
    const maxIndex = matrix.length - 1;
    for(let i = 0; i < maxIndex; ++i) {
        for(let j = i; j < maxIndex - i; ++j) {
            // let temp = matrix[i][j];
            // matrix[i][j] = matrix[maxIndex - j][i];
            // matrix[maxIndex - j][i] = matrix[maxIndex - i][maxIndex - j];
            // matrix[maxIndex - i][maxIndex - j] = matrix[j][maxIndex - i];
            // matrix[j][maxIndex - i] = temp;
            [matrix[i][j], matrix[maxIndex - j][i], matrix[maxIndex - i][maxIndex - j], matrix[j][maxIndex - i]]
            = [matrix[maxIndex - j][i], matrix[maxIndex - i][maxIndex - j], matrix[j][maxIndex - i], matrix[i][j]];
        }
    }
};

let matrix = [
    [1, 2, 3, 4],
    [5, 6, 7, 8],
    [9, 10, 11, 12],
    [13, 14, 15, 16]
];
rotate(matrix);
matrix.forEach(value => console.log(value))
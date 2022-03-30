/**
 * @param {number[]} g
 * @param {number[]} s
 * @return {number}
 */
const findContentChildren = function (g, s) {
    g.sort((a, b) => a - b);
    s.sort((a, b) => a - b);
    let count = 0;
    // 小饼干给小胃口
    let index = 0;
    // 遍历饼干大小数组依次给学生
    for (let i of s) {
        if (index < g.length && i >= g[index]) {
            index++;
            count++;
        }
    }
    return count;
};



console.log(findContentChildren([10, 9, 8, 7], [5, 6, 7, 8]));
/**
 * @param {number[]} cost
 * @return {number}
 */
const minCostClimbingStairs = function(cost) {

    if (cost.length <= 1) return 0;
    let len = cost.length;
    let a = cost[0];
    let b = cost[1];
    let cur = 0;
    for (let i in cost) {
        if (i <= 1) {
            continue;
        }
        cur = cost[i] + Math.min(a, b);
        a = b;
        b = cur;
    }
    return Math.min(a, b);
};

console.log(minCostClimbingStairs([1, 100, 1, 1, 1, 100, 1, 1, 100, 1]));;
/**
 * @param {number[]} ratings
 * @return {number}
 */
const candy = function (ratings) {

    if (ratings == null || ratings.length === 0) {
        return 0;
    }
    let inc = 1, dec = 0, cur = 1, ret = 1;
    for (let i = 1; i < ratings.length; i++) {
        if (ratings[i] > ratings[i - 1]) {
            dec = 0;
            cur++;
            inc = cur;
            ret += inc;
        } else if (ratings[i] === ratings[i - 1]) {
            dec = 0;
            inc = 1;
            cur = 1;
            ret += 1;
        } else {
            dec++;
            dec = (dec === inc ? dec + 1 : dec);
            ret += dec;
            cur = 1;
        }
    }
    return ret;
}
console.log(candy([1, 2, 1, 2, 1]));
;

/**
 * @param {string} s
 * @param {string} t
 * @return {string}
 */
const findTheDifference = function(s, t) {
    let res = 0;
    for (let i = 0; i < s.length; i++) {
        res ^= s.charCodeAt(i);
    }
    for (let i = 0; i < t.length; i++) {
        res ^= t.charCodeAt(i);
    }
    return String.fromCharCode(res);
};

console.log(findTheDifference('sas', 'ssas'));

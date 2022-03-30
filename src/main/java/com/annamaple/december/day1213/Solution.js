/**
 * @param {String}s
 * @return {String}
 */
const getBinaryAndHexadecimal = function (s) {
    let res_prefix = '';
    let res_suffix = '';
    let num;
    try {
        num = Number.parseInt(s);
        if (num > Math.pow(2, 15) - 1 || num < - Math.pow(2, 15)) {
            return "NUM_ERROR";
        }
    } catch (e) {
        return "INPUT_ERROR"
    }
    if (num !== undefined) {
        for (let i = 0; i < 16; i++) {
            res_prefix = (num & 1) + res_prefix;
            num >>>= 1;
        }
    }
    for (let i = 0; i < 4; i++) {
        res_suffix += getHexFromStr(res_prefix.slice(4 * i, 4 * (i + 1)));
    }
    return res_prefix + ',' + res_suffix;

}

function getHexFromStr(str) {
    if (str.length !== 4) {
        throw new Error('INPUT_ERROR');
    }
    let num = 0;
    for (let i in str) {
        let n = Number.parseInt(str[i]);
        num += n * Math.pow(2, 4 - 1 - i);
    }
    return getHex(num);
}

function getHex(num) {
    if (num < 10) {
        return num;
    }
    switch (num) {
        case 10: return 'A';
        case 11: return 'B';
        case 12: return 'C';
        case 13: return 'D';
        case 14: return 'E';
        case 15: return 'F';
    }
}

console.log(getBinaryAndHexadecimal("-1"));
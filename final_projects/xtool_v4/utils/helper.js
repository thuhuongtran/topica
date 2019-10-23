const getNumberFromStr = (numberStr, defaultVal) => {
    let result = defaultVal;
    let numberTemp = parseInt(numberStr, 10);
    if (!isNaN(numberTemp)) {
        result = numberTemp;
    }

    return result;
};

module.exports = { getNumberFromStr };

const readline = require("readline");
const rl = readline.createInterface(process.stdin, process.stdout);

const checkConversion = (from, to) => {
    if (from === to) {
        // Если подстроки уже равны
        return 1;
    }

    const fromSet = new Set(from.split(''));
    const toSet = new Set(to.split(''));

    if (from.length !== to.length || (fromSet.size === 33 && toSet.size === 33)) {
        // Если длина подстрок не равна
        // Или количество уникальных букв в обеих подстроках равно 33
        return 0;
    }

    const symbolsMap = {};
    for (let i = 0; i < from.length; i++) {
        if (symbolsMap[from[i]] && symbolsMap[from[i]] !== to[i]) {
            // Если мы пытаемся заменить одну букву на две разных
            return 0;
        }

        symbolsMap[from[i]] = to[i]
    }

    return 1
}

rl.on('line', (line) => {
    const [ from, to ] = line.split(' ');
    console.log(String(checkConversion(from, to)))
    rl.close();
}).on('close', () => process.exit(0));


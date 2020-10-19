const readline = require("readline");
const rl = readline.createInterface(process.stdin, process.stdout);

const countIntervals = (vacancies) => {
    const timeLine = [];
    for (const vacancy of vacancies) {
        const [ start, end ] = vacancy.split(' ')
        // Добавляем информацию о начале и конце активности вакансии, и флаг,
        // свидетельствующий о том, является ли этот момент концом активности.
        // Флаг понадобится для сортировки и выяснения максимального количества вакансий
        timeLine.push([Number(start), false]);
        timeLine.push([Number(end), true])
    }

    // Сортируем входные данные по возрастанию, для равных по времени
    // первыми будут моменты начала существования вакансии
    timeLine.sort((a, b) => {
        if (a[0] === b[0]) {
            if (a[1] === b[1]) {
                return 0;
            }
            return a[1] ? 1 : -1;
        } else {
            return a[0] - b[0];
        }
    });

    let maxVacancyCount = 0;
    let currentVacancyCount = 0;
    let maxVacanciesPoints;

    for (let pointIndex = 0; pointIndex < timeLine.length; pointIndex ++) {
        // Если текущий момент - это начало активности вакансии, добавляем,
        // если конец - отнимаем
        currentVacancyCount += timeLine[pointIndex][1] ? -1 : 1;
        if (currentVacancyCount > maxVacancyCount) {
            maxVacancyCount = currentVacancyCount;
            // Предыдущий список максимальных, если он был, заменяется новым
            maxVacanciesPoints = [pointIndex];
        } else if (currentVacancyCount === maxVacancyCount) {
            // Если количество вакансий снижалось, а затем снова выросло,
            // интервалов с максимальным количеством вакансий
            // будет больше, чем 1, их индекс добавляется в массив
            maxVacanciesPoints.push(pointIndex);
        }
    }

    let totalTime = 0;
    for (const pointIndex of maxVacanciesPoints) {
        // Для интервалов с максимальным количеством вакансий – между открытием
        // и закрытием не будет других моментов, то есть
        // timeLine[pointIndex + 1] - это конец интервала

        // Добавляем 1, потому что начальное и конечное время включены в интервал
        totalTime += 1 + timeLine[pointIndex + 1][0] - timeLine[pointIndex][0];
    }

    return `${maxVacanciesPoints.length} ${totalTime}`;
}

let vacanciesCount = null;
const vacancies = [];
rl.on('line', (line) => {
    if (vacanciesCount === null) {
        vacanciesCount = Number(line);
        return;
    }

    vacancies.push(line);
    vacanciesCount--;
    if (vacanciesCount === 0) {
        console.log(countIntervals(vacancies));
        rl.close();
    }
}).on("close", () => process.exit(0));

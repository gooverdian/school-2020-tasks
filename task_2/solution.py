vacancies_count = int(input())
time_points = []
for moment in range(vacancies_count):
    start, end = input().split()
    # Добавляем информацию о начале и конце активности вакансии, и флаг, 
    # свидетельствующий о том, является ли этот момент концом активности.
    # Флаг понадобится для сортировки и выяснения максимального количества вакансий
    time_points.append([int(start), False])
    time_points.append([int(end), True])

# Учитывая особенности сортировки Python – для совпадающих по времени 
# моментов первым будет начало интервала, а вторым конец (False < True)
time_line = sorted(time_points)

max_vacancy_count = 0
current_vacancy_count = 0

for point_index in range(len(time_line)):
    # Если текущий момент - это начало активности вакансии, добавляем, 
    # если конец - отнимаем
    current_vacancy_count += -1 if time_line[point_index][1] else 1
    if current_vacancy_count > max_vacancy_count:
        max_vacancy_count = current_vacancy_count
        # Предыдущий список максимальных, если он был, заменяется новым
        max_vacancies_points = [point_index]
    elif current_vacancy_count == max_vacancy_count:
        # Если количество вакансий снижалось, а затем снова выросло, 
        # интервалов с максимальным количеством вакансий
        # будет больше, чем 1, их индекс добавляется в массив
        max_vacancies_points.append(point_index)

total_time = 0

for point_index in max_vacancies_points:
    # Для интервалов с максимальным количеством вакансий – между открытием 
    # и закрытием не будет других моментов, то есть
    # time_line[point_index + 1] - это конец интервала

    # Добавляем 1, потому что начальное и конечное время включены в интервал
    total_time += 1 + time_line[point_index + 1][0] - time_line[point_index][0]

print(len(max_vacancies_points), total_time)

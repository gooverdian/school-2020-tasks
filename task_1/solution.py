def check_conversion(str_from, str_to):
    if str_from == str_to:
        # Если подстроки уже равны
        return 1

    if len(str_from) != len(str_to) or len(set(str_from)) == len(set(str_to)) == 33:
        # Если длина подстрок не равна
        # Или количество уникальных букв в обеих подстроках равно 33
        return 0

    symbols_map = {}
    for symbol_from, symbol_to in zip(str_from, str_to):
        if symbols_map.get(symbol_from, symbol_to) != symbol_to:
            # Если мы пытаемся заменить одну букву на две разных
            return 0

        symbols_map.update({ symbol_from: symbol_to })

    return 1

str_from, str_to = input().split()
print(check_conversion(str_from, str_to))

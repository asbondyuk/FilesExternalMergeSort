#ToDo
1. Поднятие обработки ошибок (ошибки должны обрабатываться на наиболее высоком уровне логики)
2. Выделение интерфейсов Sorter/Merger
3. Автотесты

# FilesExternalMergeSort
тестовое задание Focus Start (CFT) февраль 2020


# Инструкция
Скачать исполняемый файл
out/artifacts/FilesExternalMergeSort_jar/FilesExternalMergeSort.jar

1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
2. тип данных (-s или -i), обязательный;
3. имя выходного файла, обязательное;
4. остальные параметры – имена входных файлов, не менее одного.


Запуск программы(на примере Ubuntu 18.04)
java -jar FilesExternalMergeSort.jar -d -i out.txt in1.txt in2.txt in5.txt in3.txt in4.txt

# Особенности реализации:
1. Программа устойчива к нарушению сортировки первоначальных файлов, т.к. происходит предварительная сортировка (слиянием)
   Потери данных возможны в случае нарушения типа данных в файле (например, при обработке чисел в строке файла
   содержится буква/строка - данная строка будет пропущена)

   Возможно использование на отсортированных файлах (при сортировке вместо FileSplitter + FilesMerger использовать только
   FilesMerger, пробрасывая список файлов сразу в него), но нужно сделать переключение режима
   
2. при настройке сортировке можно задать значение промежуточного массива (chunk). Например,
"-chunk100" (без кавычек) установит длину chunk в 100

3. размер chunk, на текущий момент, используется только при первоначальном разборе и сортировке (FileSplitter). 
Слияние промежуточных файлов (FilesMerger) происходит без учета chunk

4. Java 8 (OpenJDK Runtime Environment (build 1.8.0_212-b03))

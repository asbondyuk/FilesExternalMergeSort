package cft.focusstart.bondyuk.commandLineParser;

import cft.focusstart.bondyuk.settings.FileDataType;
import cft.focusstart.bondyuk.settings.SettingsImp;
import cft.focusstart.bondyuk.settings.SortOrder;

import java.util.ArrayList;

public class CommandLineParser {
    public static void getHelp() {
        System.out.println("Параметры программы задаются при запуске через аргументы командной строки, по порядку:\n" +
                "1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;\n" +
                "2. тип данных (-s или -i), обязательный;\n" +
                "3. имя выходного файла, обязательное;\n" +
                "4. остальные параметры – имена входных файлов, не менее одного.\n" +
                "Примеры запуска из командной строки для Windows:\n" +
                "sort-it.exe -i -a out.txt in.txt (для целых чисел по возрастанию)\n" +
                "sort-it.exe -s out.txt in1.txt in2.txt in3.txt (для строк по возрастанию)\n" +
                "sort-it.exe -d -s out.txt in1.txt in2.txt (для строк по убыванию)");
    }

    public static SettingsImp commandLineParse(String[] args) {
        if (args[0].equals("-help")) {
            getHelp();

            System.exit(0);
        }

        if (args.length < 3) {
            throw new IllegalArgumentException("Введены не все параметры. Пожалуйста, введите команду заново или воспользуйтесь -help");
        }

        SortOrder sortOrder = SortOrder.ASCENDING;
        FileDataType fileDataType = FileDataType.NOT_INSTALLED;
        ArrayList<String> filesList = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case ("-d"):
                    sortOrder = SortOrder.DESCENDING;
                    break;
                case ("-a"):
                    sortOrder = SortOrder.ASCENDING;
                    break;
                case ("-i"):
                    fileDataType = FileDataType.INTEGER;
                    break;
                case ("-s"):
                    fileDataType = FileDataType.STRING;
                    break;
                default:
                    filesList.add(arg);
                    break;
            }
        }

        return new SettingsImp(sortOrder, fileDataType, filesList);
    }
}

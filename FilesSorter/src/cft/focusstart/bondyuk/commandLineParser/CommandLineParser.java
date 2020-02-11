package cft.focusstart.bondyuk.commandLineParser;

import cft.focusstart.bondyuk.settings.DataType;
import cft.focusstart.bondyuk.settings.SettingsImp;
import cft.focusstart.bondyuk.settings.SortOrder;

import java.util.ArrayList;

public class CommandLineParser {
    public static SettingsImp commandLineParse(String[] args) {
        if (args[0].equals("-help")) {
            Helper.help();

            System.exit(0);
        }

        if (args.length < 3) {
            throw new IllegalArgumentException("Введены не все параметры. Пожалуйста, введите команду заново или воспользуйтесь -help");
        }

        SortOrder sortOrder = SortOrder.ASCENDING;
        DataType dataType = DataType.NOT_INSTALLED;
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
                    dataType = DataType.INTEGER;
                    break;
                case ("-s"):
                    dataType = DataType.STRING;
                    break;
                default:
                    filesList.add(arg);
                    break;
            }
        }

        return new SettingsImp(sortOrder, dataType, filesList);
    }
}
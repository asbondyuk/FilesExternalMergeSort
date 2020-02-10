package cft.focusstart.bondyuk.main;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.settings.SettingsImp;

public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"-a", "-i", "out.txt", "in1.txt", "in2.txt"};

        SettingsImp settings = CommandLineParser.commandLineParse(strings);

        System.out.println(settings.getOutputFileName());
        System.out.println(settings.getFilesList());

//        Settings settings = CommandLineParser.commandLineParse(args);
//        MergerSort mergerSort = settings.createSorter;
//        FilesHandler(settings.createFilesList, mergerSort);
    }
}

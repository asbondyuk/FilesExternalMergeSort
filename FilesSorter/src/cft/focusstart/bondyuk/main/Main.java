package cft.focusstart.bondyuk.main;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.commandLineParser.CommandLineParserImp;
import cft.focusstart.bondyuk.filesSorter.FilesSorter;
import cft.focusstart.bondyuk.settings.Settings;

public class Main {
    public static void main(String[] args) {
        String[] strings = new String[]{"-d", "-a", "out.txt", "in1.txt", "in2.txt", "in3.txt", "in4.txt", "-chunk3"};

        CommandLineParser commandLineParser = new CommandLineParserImp();
        Settings settings = commandLineParser.parseCommandLine(strings);
        FilesSorter filesSorter = new FilesSorter(settings);

        filesSorter.mergeSortFiles();
    }
}

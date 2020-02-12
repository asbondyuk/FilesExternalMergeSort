package cft.focusstart.bondyuk.main;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.commandLineParser.CommandLineParserImp;
import cft.focusstart.bondyuk.filesSorter.FilesSorter;
import cft.focusstart.bondyuk.settings.Settings;

public class Main {
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParserImp();
        Settings settings = commandLineParser.parseCommandLine(args);
        FilesSorter filesSorter = new FilesSorter(settings);

        filesSorter.mergeSortFiles();
    }
}

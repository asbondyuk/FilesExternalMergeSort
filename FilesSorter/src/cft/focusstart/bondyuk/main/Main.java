package cft.focusstart.bondyuk.main;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.filesSorter.FilesSorter;
import cft.focusstart.bondyuk.settings.Settings;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] strings = new String[]{"-a", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt", "in4.txt",};

        Settings settings = CommandLineParser.commandLineParse(strings);
        FilesSorter filesSorter = new FilesSorter(settings);

        filesSorter.mergeSortFiles();
    }
}

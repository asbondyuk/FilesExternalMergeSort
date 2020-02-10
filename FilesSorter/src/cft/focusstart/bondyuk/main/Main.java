package cft.focusstart.bondyuk.main;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.filesHandler.FilesHandler;
import cft.focusstart.bondyuk.settings.SettingsImp;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] strings = new String[]{"-a", "-i", "out.txt", "in4.txt", "in3.txt"};

        SettingsImp settings = CommandLineParser.commandLineParse(strings);
        FilesHandler filesHandler = new FilesHandler(settings);

        filesHandler.mergeSortFiles();
    }
}

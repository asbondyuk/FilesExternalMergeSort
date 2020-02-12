package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.commandLineParser.Helper;
import cft.focusstart.bondyuk.settings.Settings;

import java.io.IOException;

public class FilesSorter {
    private Settings settings;

    public FilesSorter(Settings settings) {
        this.settings = settings;
    }

    public void mergeSortFiles() {
        FileSplitter fileSplitter = new FileSplitter(settings);

        FilesMerger filesMerger = new FilesMerger(settings);

        try {
            filesMerger.mergeFiles(fileSplitter.splitFiles());
        } catch (IOException e) {
            System.out.println(e.getMessage());

            Helper.getHelpCommand();
        }
    }
}
package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.settings.Settings;

import java.io.IOException;

public class FilesSorter {
    private Settings settings;

    public FilesSorter(Settings settings) {
        this.settings = settings;
    }

    public void mergeSortFiles() throws IOException {
        FileSplitter fileSplitter = new FileSplitter(settings);

        FilesMerger filesMerger = new FilesMerger(settings);

        filesMerger.mergeFiles(fileSplitter.splitFiles());

        System.out.println("Сортировка выполнена");
    }
}
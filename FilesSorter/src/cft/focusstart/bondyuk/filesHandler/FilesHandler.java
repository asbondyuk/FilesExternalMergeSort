package cft.focusstart.bondyuk.filesHandler;

import cft.focusstart.bondyuk.settings.Settings;

import java.io.*;
import java.util.ArrayList;

public class FilesHandler {
    private Settings settings;

    public FilesHandler(Settings settings) {
        this.settings = settings;
    }

    public void mergeSortFiles() throws IOException {
        ArrayList<File> inputFiles = new ArrayList<>();
        ArrayList<File> outputFiles = new ArrayList<>();

        for (String fileName : settings.getFilesList()) {
            File file = new File(fileName);
            inputFiles.add(file);
        }

        for (File file : inputFiles) {
            outputFiles.addAll(FileSplitter.splitFile(file, settings));
        }

        File finalFile = FilesMerger.mergeFiles(settings, outputFiles);
        System.out.printf("Сортировка выполнена. Файл %s готов", finalFile.getName());
    }
}
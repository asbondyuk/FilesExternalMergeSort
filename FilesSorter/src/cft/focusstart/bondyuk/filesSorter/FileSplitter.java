package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.settings.DataType;
import cft.focusstart.bondyuk.settings.Settings;
import cft.focusstart.bondyuk.settings.SortingChunkMaxSize;

import java.io.*;
import java.util.ArrayList;

public class FileSplitter {
    private Settings settings;

    public FileSplitter(Settings settings) {
        this.settings = settings;
    }

    public ArrayList<File> splitFiles() {
        ArrayList<File> files = new ArrayList<>();

        for (String fileName : settings.getFilesNameList()) {
            files.addAll(splitFile(new File(fileName)));
        }

        return files;
    }

    private ArrayList<File> splitFile(File file) {
        ArrayList<File> tempFiles = new ArrayList<>();
        String[] chunk = new String[SortingChunkMaxSize.getSize()];

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int sizeCurrentChunk = 0;
            String currentLine;

            do {
                currentLine = bufferedReader.readLine();
                if (currentLine != null) {
                    if (sizeCurrentChunk == SortingChunkMaxSize.getSize()) {
                        tempFiles.add(createTempFile(chunk));
                        sizeCurrentChunk = 0;
                    } else {
                        chunk[sizeCurrentChunk] = currentLine;
                        ++sizeCurrentChunk;
                    }
                } else {
                    tempFiles.add(createTempFile(chunk));
                    break;
                }
            } while (true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return tempFiles;
    }

    private File createTempFile(String[] chunk) throws IOException {
        String tempFilePrefix = "sort-temp-file-";

        File newFile = File.createTempFile(tempFilePrefix, null);
        try (FileWriter fileWriter = new FileWriter(newFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            String[] correctChunk = DataValidator.deleteNull(chunk);

            if (settings.getDataType() == DataType.INTEGER) {
                Integer[] data = DataWrapper.getIntegerData(correctChunk);
                settings.getSorter().sort(data, settings.getSortComparator());

                for (Integer s : data) {
                    printWriter.println(s);
                }
            } else {
                String[] data = DataWrapper.getStringData(correctChunk);
                settings.getSorter().sort(data, settings.getSortComparator());

                for (String s : data) {
                    printWriter.println(s);
                }
            }
        }

        return newFile;
    }
}
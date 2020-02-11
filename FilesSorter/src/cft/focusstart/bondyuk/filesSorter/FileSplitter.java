package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.settings.Settings;

import java.io.*;
import java.util.ArrayList;

public class FileSplitter {
    static int maxItemsCount = 3;

    static ArrayList<File> splitFile(File file, Settings settings) {
        ArrayList<File> tempFiles = new ArrayList<>();
        Integer[] bufferSize = new Integer[maxItemsCount];
        String tempFilePrefix = "sort-temp-file-";
        String tempFilePostfix = null;

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int sizeCurrentChunk = 0;
            int sortStartIndex = 0;
            int sortEndIndex = bufferSize.length - 1;

            String currentLine = bufferedReader.readLine();

            while (currentLine != null) {
                if (sizeCurrentChunk == maxItemsCount) {
                    settings.getSorter().sort(bufferSize, sortStartIndex, sortEndIndex);

                    File currentFile = File.createTempFile(tempFilePrefix, tempFilePostfix);
                    try (FileWriter fileWriter = new FileWriter(currentFile);
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        tempFiles.add(currentFile);

                        for (int k = 0; k < sizeCurrentChunk; k++) {
                            printWriter.println(bufferSize[k]);
                        }
                    }

                    sizeCurrentChunk = 0;
                } else {
                    bufferSize[sizeCurrentChunk] = Integer.parseInt(currentLine);
                    ++sizeCurrentChunk;
                    currentLine = bufferedReader.readLine();
                }
            }

            if (sizeCurrentChunk > 0) {
                settings.getSorter().sort(bufferSize, sortStartIndex, sortEndIndex);

                File currentFile = File.createTempFile(tempFilePrefix, tempFilePostfix);
                try (FileWriter fileWriter = new FileWriter(currentFile);
                     PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    tempFiles.add(currentFile);

                    for (int k = 0; k < sizeCurrentChunk; k++) {
                        printWriter.println(bufferSize[k]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFiles;
    }
}
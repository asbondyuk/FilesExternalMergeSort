package cft.focusstart.bondyuk.filesHandler;

import cft.focusstart.bondyuk.settings.FileDataType;
import cft.focusstart.bondyuk.settings.Settings;
import cft.focusstart.bondyuk.settings.SettingsImp;
import cft.focusstart.bondyuk.settings.SortOrder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesHandler {
    static int maxItemsCount = 3;
    private Settings settings;
    private boolean isInteger;

    public FilesHandler(Settings settings) {
        this.settings = settings;
        this.isInteger = settings.getFileDataType().equals(FileDataType.INTEGER);
    }

    private ArrayList<String> splitFile(String fileName) {
        ArrayList<String> tempFilesNames = new ArrayList<>();
        Integer[] bufferSize = new Integer[maxItemsCount];

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String currentLine = bufferedReader.readLine();

            String tempFilePrefix;
            String tempFilePostfix;
            String tempFileName;

            int sizeCurrentChunk = 0;
            int fileCount = 0;

            while (currentLine != null) {
                if (sizeCurrentChunk == maxItemsCount) {
                    int sortStartIndex = 0;
                    int sortEndIndex = bufferSize.length - 1;
                    settings.getSorter().sort(bufferSize, sortStartIndex, sortEndIndex);

                    tempFilePrefix = fileName + "temp-file-" + fileCount;
                    tempFilePostfix = ".txt";
                    tempFileName = tempFilePrefix + tempFilePostfix;

                    try (FileWriter fileWriter = new FileWriter(tempFileName);
                         PrintWriter printWriter = new PrintWriter(fileWriter)) {
                        tempFilesNames.add(tempFileName);

                        for (int k = 0; k < sizeCurrentChunk; k++) {
                            printWriter.println(bufferSize[k]);
                        }
                    }

                    sizeCurrentChunk = 0;
                    ++fileCount;
                } else {
                    bufferSize[sizeCurrentChunk] = Integer.parseInt(currentLine);
                    ++sizeCurrentChunk;
                    currentLine = bufferedReader.readLine();
                }
            }

            if (sizeCurrentChunk > 0) {
                int sortStartIndex = 0;
                int sortEndIndex = bufferSize.length - 1;
                settings.getSorter().sort(bufferSize, sortStartIndex, sortEndIndex);

                tempFilePrefix = fileName + "temp-file-" + fileCount;
                tempFilePostfix = ".txt";
                tempFileName = tempFilePrefix + tempFilePostfix;

                try (FileWriter fileWriter = new FileWriter(tempFileName);
                     PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    tempFilesNames.add(tempFileName);

                    for (int k = 0; k < sizeCurrentChunk; k++) {
                        printWriter.println(bufferSize[k]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tempFilesNames;
    }

    private void combineFiles(String outputFileName, ArrayList<String> tempFilesNames) throws IOException {
        int tempFilesCount = tempFilesNames.size();
        int[] filesMaxNumbers = new int[tempFilesCount];
        BufferedReader[] bufferedReaders = new BufferedReader[tempFilesCount];

        for (int i = 0; i < tempFilesCount; i++) {
            bufferedReaders[i] = new BufferedReader(new FileReader(tempFilesNames.get(i)));
            String currentWriteLine = bufferedReaders[i].readLine();

            if (currentWriteLine != null) {
                filesMaxNumbers[i] = Integer.parseInt(currentWriteLine);
            }
        }

        try (FileWriter fileWriter = new FileWriter(outputFileName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            int writeCount = 0;

            while (writeCount < tempFilesCount * maxItemsCount) {
                int minNumber = filesMaxNumbers[0];
                int minNumberFileIndex = 0;

                for (int j = 0; j < tempFilesCount; j++) {
                    if (minNumber > filesMaxNumbers[j]) {
                        minNumber = filesMaxNumbers[j];
                        minNumberFileIndex = j;
                    }
                }

                printWriter.println(minNumber);

                // TODO (?) исправить максимальные значения
                String currentWriteLine = bufferedReaders[minNumberFileIndex].readLine();
                if (currentWriteLine != null) {
                    filesMaxNumbers[minNumberFileIndex] = Integer.parseInt(currentWriteLine);
                } else {
                    filesMaxNumbers[minNumberFileIndex] = Integer.MAX_VALUE;
                }

                ++writeCount;
            }

            for (int i = 0; i < tempFilesCount; ++i) {
                bufferedReaders[i].close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void externalSort(String fileName) throws IOException {
        combineFiles(settings.getOutputFileName(), splitFile(fileName));
    }

    public static void main(String[] args) {
        String fileName = "in4.txt";
        List<String> filesList = new ArrayList<>();
        filesList.add("out.txt");
        filesList.add("in44.txt");

        Settings settings = new SettingsImp(SortOrder.ASCENDING, FileDataType.INTEGER, filesList);
        FilesHandler filesHandler = new FilesHandler(settings);

        try {
            filesHandler.externalSort(fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

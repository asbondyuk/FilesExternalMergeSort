package cft.focusstart.bondyuk.filesHandler;

import cft.focusstart.bondyuk.settings.Settings;

import java.io.*;
import java.util.ArrayList;

// TODO Разнести по классам (упростить понимаение) + переименовать handler
public class FilesHandler {
    static int maxItemsCount = 3;
    private Settings settings;
    private ArrayList<File> tempFinalFiles;

    public FilesHandler(Settings settings) {
        this.settings = settings;
    }

    private ArrayList<File> splitFile(File file) {
        ArrayList<File> tempFiles = new ArrayList<>();
        Integer[] bufferSize = new Integer[maxItemsCount];
        String tempFilePrefix = "temp-file-";
        String tempFilePostfix = ".txt";

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

    private File combineFiles(String outputFileName, ArrayList<File> tempFiles) throws IOException {
        int tempFilesCount = tempFiles.size();
        int[] filesMaxNumbers = new int[tempFilesCount];
        BufferedReader[] bufferedReaders = new BufferedReader[tempFilesCount];

        for (int i = 0; i < tempFilesCount; i++) {
            bufferedReaders[i] = new BufferedReader(new FileReader(tempFiles.get(i)));
            String currentWriteLine = bufferedReaders[i].readLine();

            if (currentWriteLine != null) {
                filesMaxNumbers[i] = Integer.parseInt(currentWriteLine);
            }
        }

        File outputFile = new File(outputFileName);

        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            int bufferReadersCount = bufferedReaders.length;

            while (bufferReadersCount > 0) {
                if (settings.getSortDirection().getSortOrder() == 1) {
                    int minNumber = filesMaxNumbers[0];
                    int minNumberFileIndex = 0;

                    for (int j = 0; j < tempFilesCount; j++) {
                        if (minNumber > filesMaxNumbers[j]) {
                            minNumber = filesMaxNumbers[j];
                            minNumberFileIndex = j;
                        }
                    }

                    printWriter.println(minNumber);

                    String currentWriteLine = bufferedReaders[minNumberFileIndex].readLine();
                    if (currentWriteLine != null) {
                        filesMaxNumbers[minNumberFileIndex] = Integer.parseInt(currentWriteLine);
                    } else {
                        filesMaxNumbers[minNumberFileIndex] = Integer.MAX_VALUE;
                        --bufferReadersCount;
                    }
                } else {
                    int maxNumberNumber = filesMaxNumbers[0];
                    int maxNumberFileIndex = 0;

                    for (int j = 0; j < tempFilesCount; j++) {
                        if (maxNumberNumber < filesMaxNumbers[j]) {
                            maxNumberNumber = filesMaxNumbers[j];
                            maxNumberFileIndex = j;
                        }
                    }

                    printWriter.println(maxNumberNumber);

                    String currentWriteLine = bufferedReaders[maxNumberFileIndex].readLine();
                    if (currentWriteLine != null) {
                        filesMaxNumbers[maxNumberFileIndex] = Integer.parseInt(currentWriteLine);
                    } else {
                        filesMaxNumbers[maxNumberFileIndex] = Integer.MIN_VALUE;
                        --bufferReadersCount;
                    }
                }
            }

            for (int i = 0; i < tempFilesCount; ++i) {
                bufferedReaders[i].close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return outputFile;
    }

    public void mergeSortFiles() throws IOException {
        ArrayList<File> inputFiles = new ArrayList<>();
        ArrayList<File> outputFiles = new ArrayList<>();

        // TODO необходимость закрывать временные файлы
        for (String fileName : settings.getFilesList()) {
            File file = new File(fileName);
            inputFiles.add(file);
        }

        for (File file : inputFiles) {
            outputFiles.addAll(splitFile(file));
        }

        System.out.println(inputFiles);
        System.out.println(outputFiles);

        File finalFile = combineFiles(settings.getOutputFileName(), outputFiles);
    }
}

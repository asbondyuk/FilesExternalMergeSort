package cft.focusstart.bondyuk.filesHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class FilesHandler {
    static int maxItemsCount = 3;

    public static void externalSort(String fileName) {
        ArrayList<String> tempFilesNames = new ArrayList<>();
        String tempFilePrefix = fileName + "temp-file-";
        int[] bufferSize = new int[maxItemsCount];

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String currentLine = bufferedReader.readLine();
            int sizeCurrentChunk = 0;
            int fileCount = 0;

            while (currentLine != null) {
                if (sizeCurrentChunk == maxItemsCount) {
                    Arrays.sort(bufferSize);

                    String tempFileName = tempFilePrefix + fileCount + ".txt";

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
                Arrays.sort(bufferSize);

                String tempFileName = tempFilePrefix + fileCount + ".txt";

                try (FileWriter fileWriter = new FileWriter(tempFileName);
                     PrintWriter printWriter = new PrintWriter(fileWriter)) {
                    tempFilesNames.add(tempFileName);

                    for (int k = 0; k < sizeCurrentChunk; k++) {
                        printWriter.println(bufferSize[k]);
                    }
                }
            }

            // TODO (?) разнести на несколько методов
            int tempFilesCount = tempFilesNames.size();
            int[] filesMaxNumbers = new int[tempFilesCount];
            BufferedReader[] bufferedReaders = new BufferedReader[tempFilesCount];

            // собираем текущие начальные значения
            for (int i = 0; i < tempFilesCount; i++) {
                bufferedReaders[i] = new BufferedReader(new FileReader(tempFilePrefix + i + ".txt"));
                String currentWriteLine = bufferedReaders[i].readLine();

                if (currentWriteLine != null) {
                    filesMaxNumbers[i] = Integer.parseInt(currentWriteLine);
                }
            }

            System.out.println(Arrays.toString(filesMaxNumbers));

            // собираем итоговый файл
            String SortFinalFileName = "out.txt";
            try (FileWriter fileWriter = new FileWriter(SortFinalFileName);
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
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "in4.txt";
        externalSort(fileName);
    }
}

package cft.focusstart.bondyuk.filesHandler;

import cft.focusstart.bondyuk.settings.Settings;

import java.io.*;
import java.util.ArrayList;

public class FilesMerger {
    static File mergeFiles(Settings settings, ArrayList<File> tempFiles) throws IOException {
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

        File outputFile = new File(settings.getOutputFileName());

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
                tempFiles.get(i).deleteOnExit();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return outputFile;
    }
}
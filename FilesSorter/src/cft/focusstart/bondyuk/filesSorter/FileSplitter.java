package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.settings.DataType;
import cft.focusstart.bondyuk.sorter.comparators.AscendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.SortComparator;
import cft.focusstart.bondyuk.sorter.sorters.MergeSortGeneric;
import cft.focusstart.bondyuk.sorter.sorters.Sorter;

import java.io.*;
import java.util.ArrayList;

public class FileSplitter {
    static int maxItemsCount = 3;
    static Sorter sorter = new MergeSortGeneric();
    static SortComparator sortComparator = new AscendingSortComparator();
    static DataType dataType = DataType.INTEGER;

    private File createTempFile(String[] chunk) throws IOException {
        String tempFilePrefix = "sort-temp-file-";

        File newFile = File.createTempFile(tempFilePrefix, null);
        try (FileWriter fileWriter = new FileWriter(newFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            if (dataType == DataType.INTEGER) {
                Integer[] data = DataWrapper.getIntegerData(chunk);
                sorter.sort(data, sortComparator);

                for (Integer s : data) {
                    printWriter.println(s);
                }
            } else {
                String[] data = DataWrapper.getStringData(chunk);
                sorter.sort(data, sortComparator);

                for (String s : data) {
                    printWriter.println(s);
                }
            }
        }

        return newFile;
    }

    ArrayList<File> splitFile(File file) {
        ArrayList<File> tempFiles = new ArrayList<>();
        String[] chunk = new String[maxItemsCount];

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            int sizeCurrentChunk = 0;
            String currentLine;

            do {
                currentLine = bufferedReader.readLine();

                if (sizeCurrentChunk == maxItemsCount || currentLine == null) {
                    tempFiles.add(createTempFile(chunk));

                    sizeCurrentChunk = 0;

                    if (currentLine == null) {
                        break;
                    }
                } else {
                    chunk[sizeCurrentChunk] = currentLine;
                    ++sizeCurrentChunk;
                }
            } while (true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return tempFiles;
    }
}
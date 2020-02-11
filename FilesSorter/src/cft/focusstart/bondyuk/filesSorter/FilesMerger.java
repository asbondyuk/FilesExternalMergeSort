package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.settings.DataType;
import cft.focusstart.bondyuk.sorter.comparators.AscendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.DescendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.SortComparator;
import cft.focusstart.bondyuk.sorter.sorters.MergeSortGeneric;
import cft.focusstart.bondyuk.sorter.sorters.Sorter;

import java.io.*;
import java.util.ArrayList;

public class FilesMerger {
    static Sorter sorter = new MergeSortGeneric();
    static SortComparator sortComparator = new DescendingSortComparator();
    static DataType dataType = DataType.INTEGER;
    static String outputFileName = "out.txt";

    private BufferedReader getBufferedRead(File file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);

        return new BufferedReader(fileReader);

    }

    public File mergeFiles(ArrayList<File> tempFiles) throws IOException {
        ArrayList<String> filesMaxNumbers = new ArrayList<>();
        ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();

        BufferedReader bufferedReader;
        String currentWriteLine;

        for (int i = 0; i < tempFiles.size(); ++i) {
            bufferedReader = getBufferedRead(tempFiles.get(i));
            bufferedReaders.add(bufferedReader);

            currentWriteLine = bufferedReaders.get(i).readLine();

            filesMaxNumbers.add(currentWriteLine);
        }

        File outputFile = new File(outputFileName);

        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            String nextWriteItem;
            int nextWriteItemFileIndex;

            while (bufferedReaders.size() > 0) {
                System.out.println(filesMaxNumbers);

                nextWriteItem = filesMaxNumbers.get(0);
                nextWriteItemFileIndex = 0;

                String[] tmp = new String[2];

                for (int j = 0; j < bufferedReaders.size(); ++j) {
                    tmp[0] = nextWriteItem;
                    tmp[1] = filesMaxNumbers.get(j);

                    if (dataType == DataType.INTEGER) {
                        Integer one = DataWrapper.getInteger(tmp[0]);
                        Integer two = DataWrapper.getInteger(tmp[1]);

                        if (sortComparator.compare(one, two) > 0) {
                            nextWriteItem = filesMaxNumbers.get(j);
                            nextWriteItemFileIndex = j;
                        }
                    } else {
                        String one = DataWrapper.getString(tmp[0]);
                        String two = DataWrapper.getString(tmp[1]);

                        if (sortComparator.compare(one, two) > 0) {
                            nextWriteItem = filesMaxNumbers.get(j);
                            nextWriteItemFileIndex = j;
                        }
                    }
                }

                System.out.println(nextWriteItem);

                printWriter.println(nextWriteItem);

                currentWriteLine = bufferedReaders.get(nextWriteItemFileIndex).readLine();
                if (currentWriteLine != null) {
                    filesMaxNumbers.set(nextWriteItemFileIndex, currentWriteLine);
                } else {
                    filesMaxNumbers.remove(nextWriteItemFileIndex);
                    bufferedReaders.get(nextWriteItemFileIndex).close();
                    bufferedReaders.remove(nextWriteItemFileIndex);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return outputFile;
    }
}
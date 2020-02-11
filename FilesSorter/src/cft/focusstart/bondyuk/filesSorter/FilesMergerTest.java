package cft.focusstart.bondyuk.filesSorter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FilesMergerTest {
    public static void main(String[] args) throws IOException {
        FilesMerger filesMerger = new FilesMerger();

        ArrayList<File> files = new ArrayList<>();

        File file1 = new File("in1.txt");
        File file2 = new File("in2.txt");
        File file3 = new File("in3.txt");
        File file4 = new File("in4.txt");

        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file4);

        File file = filesMerger.mergeFiles(files);
    }
}

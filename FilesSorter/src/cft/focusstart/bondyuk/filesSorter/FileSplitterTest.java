package cft.focusstart.bondyuk.filesSorter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSplitterTest {
    public static void main(String[] args) throws IOException {
        FileSplitter fileSplitter = new FileSplitter();
        ArrayList<File> result = fileSplitter.splitFile(new File("in1.txt"));

        for (File file : result) {
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }

            System.out.println();
        }
    }
}

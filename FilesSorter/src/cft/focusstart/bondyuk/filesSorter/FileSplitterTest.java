package cft.focusstart.bondyuk.filesSorter;

import cft.focusstart.bondyuk.commandLineParser.CommandLineParser;
import cft.focusstart.bondyuk.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSplitterTest {    public static void main(String[] args) throws IOException {
        String[] strings = new String[]{"-a", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt", "in4.txt"};

        Settings settings = CommandLineParser.commandLineParse(strings);

        FileSplitter fileSplitter = new FileSplitter();
        ArrayList<File> result = fileSplitter.splitFile(new File("in1.txt"));

        System.out.println(result);

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

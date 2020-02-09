package cft.focusstart.bondyuk.commandLineParser;

public class CommandLineParserTest {
    public static void testLessArgument() {
        String[] strings = new String[]{"-a"};
        CommandLineParser.commandLineParse(strings);

        System.out.println("testLessArgument");
    }

    public static void testGetHelp() {
        System.out.println("testGetHelp");

        String[] strings = new String[]{"-help"};
        CommandLineParser.commandLineParse(strings);
    }

    public static void testNotInstalledFileDataType() {
        System.out.println("testNotInstalledFileDataType");

        String[] strings = new String[]{"-d", "out.txt", "in1.txt", "asd", "asdasd"};
        CommandLineParser.commandLineParse(strings);
    }

    public static void testNotInstalledOutFile() {
        System.out.println("testNotInstalledOutFile");

        String[] strings = new String[]{"-a", "-d"};
        CommandLineParser.commandLineParse(strings);
    }

    public static void testNotInstalledInFile() {
        System.out.println("testNotInstalledInFile");

        String[] strings = new String[]{"-a", "-d", "out.txt"};
        CommandLineParser.commandLineParse(strings);
    }

    public static void main(String[] args) {
        try {
            testLessArgument();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            testNotInstalledFileDataType();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            testNotInstalledOutFile();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            testNotInstalledInFile();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            testGetHelp();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}

package cft.focusstart.bondyuk.filesSorter;

public class DataWrapper {
    public static String[] getStringData(String[] data) {
        return data;
    }

    public static Integer[] getIntegerData(String[] data) throws NumberFormatException {
        Integer[] integers = new Integer[data.length];

        for (int i = 0; i < data.length; ++i) {
            integers[i] = Integer.parseInt(data[i]);
        }

        return integers;
    }
}

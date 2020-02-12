package cft.focusstart.bondyuk.filesSorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataValidator {
    public static String[] deleteNull(String[] strings) {
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        list.removeAll(Collections.singleton(null));
        return list.toArray(new String[0]);
    }

    public static String getString(String data) {
        return data;
    }

    public static Integer getInteger(String data) throws NumberFormatException {
        return Integer.parseInt(data);
    }

    public static String[] getStringData(String[] data) {
        return data;
    }

    public static Integer[] getIntegerData(String[] data) throws NumberFormatException {
        Integer[] integers = new Integer[data.length];

        for (int i = 0; i < data.length; ++i) {
            try {
                if (data[i] != null) {
                    integers[i] = Integer.parseInt(data[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Чет не то с: " + data[i]);
                System.out.println(Arrays.toString(data));
            } catch (NullPointerException e) {
                System.out.println("Вижу null " + data[i]);
            }
        }

        return integers;
    }
}
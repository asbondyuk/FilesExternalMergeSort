package cft.focusstart.bondyuk.settings.dataType;

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

    public static boolean isDigit(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
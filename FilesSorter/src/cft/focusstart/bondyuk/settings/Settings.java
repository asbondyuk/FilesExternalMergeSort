package cft.focusstart.bondyuk.settings;

import cft.focusstart.bondyuk.sorter.SortDirection;
import cft.focusstart.bondyuk.sorter.Sorter;

import java.util.List;

public interface Settings {

    FileDataType getFileDataType();

    SortDirection getSortDirection();

    Sorter getSorter();

    String getOutputFileName();

    List<String> getFilesList();
}

package cft.focusstart.bondyuk.settings;

import cft.focusstart.bondyuk.sorter.comparators.SortComparator;
import cft.focusstart.bondyuk.sorter.sorters.Sorter;

import java.util.List;

public interface Settings {
    DataType getDataType();

    SortComparator getSortComparator();

    Sorter getSorter();

    String getOutputFileName();

    List<String> getFilesList();
}
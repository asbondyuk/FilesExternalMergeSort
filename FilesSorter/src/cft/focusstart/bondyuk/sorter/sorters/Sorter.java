package cft.focusstart.bondyuk.sorter.sorters;

import cft.focusstart.bondyuk.sorter.comparators.SortComparator;

public interface Sorter {
    <T extends Comparable<T>> void sort(T[] array, SortComparator sortComparator);
}
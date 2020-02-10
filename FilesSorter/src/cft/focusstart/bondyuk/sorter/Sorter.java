package cft.focusstart.bondyuk.sorter;

public interface Sorter {
    <T extends Comparable<T>> void sort(T[] data, int startIndex, int endIndex, Comparator comparator);
}

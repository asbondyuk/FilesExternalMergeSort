package cft.focusstart.bondyuk.sorter;

public interface Sorter {
    <T extends Comparable<? super T>> void sort(T[] data, int startIndex, int endIndex);
}

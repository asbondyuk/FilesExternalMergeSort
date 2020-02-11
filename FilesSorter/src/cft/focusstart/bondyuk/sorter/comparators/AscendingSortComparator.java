package cft.focusstart.bondyuk.sorter.comparators;

public class AscendingSortComparator implements SortComparator {
    @Override
    public <T extends Comparable<T>> int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
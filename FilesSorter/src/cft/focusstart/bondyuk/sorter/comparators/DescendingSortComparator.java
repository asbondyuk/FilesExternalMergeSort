package cft.focusstart.bondyuk.sorter.comparators;

public class DescendingSortComparator implements SortComparator {
    @Override
    public <T extends Comparable<T>> int compare(T arg0, T arg1) {
        return -(arg0.compareTo(arg1));
    }
}
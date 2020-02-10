package cft.focusstart.bondyuk.sorter;

import cft.focusstart.bondyuk.settings.SortOrder;

public class ComparatorImp implements Comparator {
    private SortOrder sortOrder;

    public ComparatorImp(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int getSortOrder() {
        return sortOrder.equals(SortOrder.ASCENDING) ? 1 : -1;
    }
}

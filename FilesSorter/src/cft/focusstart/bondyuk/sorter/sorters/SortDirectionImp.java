package cft.focusstart.bondyuk.sorter.sorters;

import cft.focusstart.bondyuk.settings.SortOrder;

public class SortDirectionImp implements SortDirection {
    private SortOrder sortOrder;

    public SortDirectionImp(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int getSortOrder() {
        return sortOrder.equals(SortOrder.ASCENDING) ? 1 : -1;
    }
}

package cft.focusstart.bondyuk.sorter.sorters;

import cft.focusstart.bondyuk.sorter.comparators.AscendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.DescendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.SortComparator;

public class MergeSortGenericTest {
    public static void main(String[] args) {
        SortComparator descendingSortComparator = new DescendingSortComparator();
        SortComparator ascendingComparator = new AscendingSortComparator();

        Sorter descendingSorter = new MergeSortGeneric();
        Sorter ascendingSorter = new MergeSortGeneric();

        String[] arrayOfStrings = {"e", "b", "d", "a", "z", "h", "g", "j", "c"};

        descendingSorter.sort(arrayOfStrings, descendingSortComparator);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        ascendingSorter.sort(arrayOfStrings, ascendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        Integer[] arrayOfIntegers = {22, 10, 9, 8, 7, 5, 4, 3, 6, 1, 2, 3, 11};

        descendingSorter.sort(arrayOfIntegers, descendingSortComparator);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));

        ascendingSorter.sort(arrayOfIntegers, ascendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));
    }
}

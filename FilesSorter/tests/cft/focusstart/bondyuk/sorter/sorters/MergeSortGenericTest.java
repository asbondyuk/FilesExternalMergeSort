package cft.focusstart.bondyuk.sorter.sorters;

import cft.focusstart.bondyuk.sorter.comparators.AscendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.DescendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.SortComparator;

import java.util.Arrays;

public class MergeSortGenericTest {
    public static boolean testSortIntegerAscending() {
        SortComparator sortComparator = new AscendingSortComparator();
        Sorter sorter = new MergeSortGeneric();

        Integer[] array = {22, 1, 9, 8, 2, 11};
        sorter.sort(array, sortComparator);

        Integer[] expectedArray = {1, 2, 8, 9, 11, 22};

        return Arrays.equals(array, expectedArray);
    }

    public static boolean testSortIntegerDescending() {
        SortComparator sortComparator = new DescendingSortComparator();
        Sorter sorter = new MergeSortGeneric();

        Integer[] array = {22, 1, 9, 8, 2, 11};
        sorter.sort(array, sortComparator);

        Integer[] expectedArray = {22, 11, 9, 8, 2, 1};

        return Arrays.equals(array, expectedArray);
    }

    public static boolean testSortStringAscending() {
        SortComparator sortComparator = new AscendingSortComparator();
        Sorter sorter = new MergeSortGeneric();

        String[] array = {"e", "b", "c", "a", "f", "d"};
        sorter.sort(array, sortComparator);

        String[] expectedArray = {"a", "b", "c", "d", "e", "f"};

        return Arrays.equals(array, expectedArray);
    }

    public static boolean testSortStringDescending() {
        SortComparator sortComparator = new DescendingSortComparator();
        Sorter sorter = new MergeSortGeneric();

        String[] array = {"e", "b", "c", "a", "f", "d"};
        sorter.sort(array, sortComparator);

        String[] expectedArray = {"f", "e", "d", "c", "b", "a"};

        return Arrays.equals(array, expectedArray);
    }

    public static void main(String[] args) {
        System.out.println("Состояние - Название теста");
        System.out.println(MergeSortGenericTest.testSortIntegerAscending() + " - сортировка Integer/Asc");
        System.out.println(MergeSortGenericTest.testSortIntegerDescending() + " - сортировка Integer/Desc");
        System.out.println(MergeSortGenericTest.testSortStringAscending() + " - сортировка String/Asc");
        System.out.println(MergeSortGenericTest.testSortStringDescending() + " - сортировка String/Desc");
    }
}
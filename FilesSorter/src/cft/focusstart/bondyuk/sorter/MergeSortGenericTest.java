package cft.focusstart.bondyuk.sorter;

import cft.focusstart.bondyuk.settings.SortOrder;

public class MergeSortGenericTest {
    public static void main(String[] args) {
        Comparator descendingComparator = new ComparatorImp(SortOrder.DESCENDING);
        Comparator ascendingComparator = new ComparatorImp(SortOrder.ASCENDING);

        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};
        Sorter sorter = new MergeSortGeneric();

        sorter.sort(arrayOfStrings, 0, arrayOfStrings.length - 1, descendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        sorter.sort(arrayOfStrings, 0, arrayOfStrings.length - 1, ascendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        Integer[] arrayOfIntegers = {10, 9, 8, 7, 5, 4, 3, 6, 1, 2, 3};

        sorter.sort(arrayOfIntegers, 0, arrayOfIntegers.length - 1, descendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));

        sorter.sort(arrayOfIntegers, 0, arrayOfIntegers.length - 1, ascendingComparator);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));
    }
}

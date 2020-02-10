package cft.focusstart.bondyuk.sorter;

import cft.focusstart.bondyuk.settings.SortOrder;

public class MergeSortGenericTest {
    public static void main(String[] args) {
        SortDirection descendingSortDirection = new SortDirectionImp(SortOrder.DESCENDING);
        Sorter descendingSorter = new MergeSortGeneric(descendingSortDirection);

        SortDirection ascendingSortDirection = new SortDirectionImp(SortOrder.ASCENDING);
        Sorter ascendingSorter = new MergeSortGeneric(ascendingSortDirection);

        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};

        descendingSorter.sort(arrayOfStrings, 0, arrayOfStrings.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        ascendingSorter.sort(arrayOfStrings, 0, arrayOfStrings.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        Integer[] arrayOfIntegers = {10, 9, 8, 7, 5, 4, 3, 6, 1, 2, 3};

        descendingSorter.sort(arrayOfIntegers, 0, arrayOfIntegers.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));

        ascendingSorter.sort(arrayOfIntegers, 0, arrayOfIntegers.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));
    }
}

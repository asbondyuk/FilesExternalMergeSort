package cft.focusstart.bondyuk.sorter;

public class MergeSortGenericTest {
    public static void main(String[] args) {
        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};
        Sorter stringSorter = new MergeSortGeneric();
        stringSorter.sort(arrayOfStrings, 0, arrayOfStrings.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        Integer[] arrayOfIntegers = {10, 9, 8, 7, 5, 4, 3, 6, 1, 2, 3};
        Sorter integerSorter = new MergeSortGeneric();
        integerSorter.sort(arrayOfIntegers, 0, arrayOfIntegers.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfIntegers));
    }
}

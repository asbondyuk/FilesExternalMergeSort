package cft.focusstart.bondyuk.sorter;

public class MergeSortGeneric implements Sorter {
    private SortDirection sortDirection;

    public MergeSortGeneric(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;

            sort(array, startIndex, middleIndex);
            sort(array, middleIndex + 1, endIndex);

            merge(array, startIndex, middleIndex, endIndex, sortDirection);
        }
    }

    public <T extends Comparable<T>> void merge(T[] array, int start, int middle, int end, SortDirection sortDirection) {
        T[] leftArray = (T[]) new Comparable[middle - start + 1];
        T[] rightArray = (T[]) new Comparable[end - middle];

        if (leftArray.length >= 0) {
            System.arraycopy(array, start, leftArray, 0, leftArray.length);
        }

        for (int i = 0; i < rightArray.length; ++i) {
            rightArray[i] = array[middle + 1 + i];
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = start;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (sortDirection.getSortOrder() * leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
                array[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                array[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }

            currentIndex++;
        }

        while (leftIndex < leftArray.length) {
            array[currentIndex++] = leftArray[leftIndex++];
        }

        while (rightIndex < rightArray.length) {
            array[currentIndex++] = rightArray[rightIndex++];
        }
    }
}
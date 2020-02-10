package cft.focusstart.bondyuk.sorter;

public class MergeSortGeneric implements Sorter {
    @Override
    public <T extends Comparable<T>> void sort(T[] array, int startIndex, int endIndex, Comparator comparator) {
        if (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;

            sort(array, startIndex, middleIndex, comparator);
            sort(array, middleIndex + 1, endIndex, comparator);

            merge(array, startIndex, middleIndex, endIndex, comparator);
        }
    }

    public <T extends Comparable<T>> void merge(T[] array, int start, int middle, int end, Comparator comparator) {
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
            if (comparator.getSortOrder() * leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0) {
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
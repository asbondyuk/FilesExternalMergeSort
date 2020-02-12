package cft.focusstart.bondyuk.settings;

import cft.focusstart.bondyuk.settings.dataType.DataType;
import cft.focusstart.bondyuk.settings.chunks.ChunkMaxSize;
import cft.focusstart.bondyuk.sorter.comparators.AscendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.DescendingSortComparator;
import cft.focusstart.bondyuk.sorter.comparators.SortComparator;
import cft.focusstart.bondyuk.sorter.sorters.MergeSortGeneric;
import cft.focusstart.bondyuk.sorter.sorters.Sorter;
import org.w3c.dom.ranges.RangeException;

import java.util.List;

public class SettingsImp implements Settings {
    private DataType dataType;
    private SortComparator sortComparator;
    private Sorter sorter;
    private String outputFileName;
    private List<String> sortableFilesNameList;
    private ChunkMaxSize chunkMaxSize;

    public SettingsImp(SortOrder sortOrder, DataType dataType, List<String> filesList, ChunkMaxSize chunkMaxSize) {
        SettingsImp.validateSettings(dataType, filesList);

        this.dataType = dataType;
        this.sortComparator = sortOrder.equals(SortOrder.ASCENDING) ? new AscendingSortComparator() : new DescendingSortComparator();
        this.sorter = new MergeSortGeneric();
        this.outputFileName = filesList.get(0);
        this.sortableFilesNameList = filesList.subList(1, filesList.size());
        this.chunkMaxSize = chunkMaxSize;
    }

    public static void validateSettings(DataType dataType, List<String> filesList) {
        try {
            if (dataType.equals(DataType.NOT_INSTALLED)) {
                throw new IllegalArgumentException("Необходимо задать тип данных: \"-i\" для чисел или \"-s\" для строк (или воспользуйтесь \"-help\")");
            }

            if (filesList.size() < 1) {
                throw new IllegalArgumentException("Необходимо задать название выходного файла (или воспользуйтесь \"-help\")");
            }

            if (filesList.size() < 2) {
                throw new IllegalArgumentException("Необходимо задать название файла для сортировки (или воспользуйтесь \"-help\")");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    @Override
    public SortComparator getSortComparator() {
        return sortComparator;
    }

    @Override
    public Sorter getSorter() {
        return sorter;
    }

    @Override
    public String getOutputFileName() {
        return outputFileName;
    }

    @Override
    public List<String> getFilesNameList() {
        return sortableFilesNameList;
    }

    @Override
    public int getChunkMaxSize() {
        return chunkMaxSize.getSize();
    }
}
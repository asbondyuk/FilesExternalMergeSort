package cft.focusstart.bondyuk.settings;

import cft.focusstart.bondyuk.sorter.MergeSortGeneric;
import cft.focusstart.bondyuk.sorter.SortDirection;
import cft.focusstart.bondyuk.sorter.SortDirectionImp;
import cft.focusstart.bondyuk.sorter.Sorter;

import java.util.List;

public class SettingsImp implements Settings {
    private SortOrder sortOrder;
    private FileDataType fileDataType;
    private String outputFileName;
    private List<String> filesList;

    public SettingsImp(SortOrder sortOrder, FileDataType fileDataType, List<String> filesList) {
        if (fileDataType.equals(FileDataType.NOT_INSTALLED)) {
            throw new IllegalArgumentException("Необходимо задать тип данных: \"-i\" для чисел или \"-s\" для строк (или воспользуйтесь \"-help\")");
        }

        if (filesList.size() < 1) {
            throw new IllegalArgumentException("Необходимо задать название выходного файла (или воспользуйтесь \"-help\")");
        }

        if (filesList.size() < 2) {
            throw new IllegalArgumentException("Необходимо задать название файла для сортировки (или воспользуйтесь \"-help\")");
        }

        this.sortOrder = sortOrder;
        this.fileDataType = fileDataType;
        this.outputFileName = filesList.get(0);
        this.filesList = filesList.subList(1, filesList.size());
    }

    @Override
    public FileDataType getFileDataType() {
        return fileDataType;
    }

    @Override
    public SortDirection getSortDirection() {
        return new SortDirectionImp(sortOrder);
    }

    @Override
    public Sorter getSorter() {
        return new MergeSortGeneric(getSortDirection());
    }

    @Override
    public String getOutputFileName() {
        return outputFileName;
    }

    @Override
    public List<String> getFilesList() {
        return filesList;
    }
}

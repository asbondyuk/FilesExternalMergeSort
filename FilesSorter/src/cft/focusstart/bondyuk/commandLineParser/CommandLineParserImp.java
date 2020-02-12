package cft.focusstart.bondyuk.commandLineParser;

import cft.focusstart.bondyuk.settings.chunks.ChunkService;
import cft.focusstart.bondyuk.settings.dataType.DataType;
import cft.focusstart.bondyuk.settings.Settings;
import cft.focusstart.bondyuk.settings.SettingsImp;
import cft.focusstart.bondyuk.settings.SortOrder;
import cft.focusstart.bondyuk.settings.chunks.ChunkMaxSize;

import java.util.ArrayList;

public class CommandLineParserImp implements CommandLineParser {
    @Override
    public Settings parseCommandLine(String[] commandLine) {
        if (commandLine[0].equals("-help")) {
            Helper.help();

            System.exit(0);
        }

        if (commandLine.length < 3) {
            throw new IllegalArgumentException("Введены не все параметры. Пожалуйста, введите команду заново или воспользуйтесь -help");
        }

        SortOrder sortOrder = SortOrder.ASCENDING;
        DataType dataType = DataType.NOT_INSTALLED;
        ChunkMaxSize chunkMaxSize = new ChunkMaxSize();
        ArrayList<String> filesList = new ArrayList<>();

        for (String arg : commandLine) {
            if (ChunkService.isChunkCommand(arg)) {
                int size = ChunkService.getCount(arg);
                if (size > 0) {
                    chunkMaxSize = ChunkService.getSortingChunkMaxSize(size);
                }

                continue;
            }

            switch (arg) {
                case ("-d"):
                    sortOrder = SortOrder.DESCENDING;
                    break;
                case ("-a"):
                    sortOrder = SortOrder.ASCENDING;
                    break;
                case ("-i"):
                    dataType = DataType.INTEGER;
                    break;
                case ("-s"):
                    dataType = DataType.STRING;
                    break;
                default:
                    filesList.add(arg);
                    break;
            }
        }

        return new SettingsImp(sortOrder, dataType, filesList, chunkMaxSize);
    }
}
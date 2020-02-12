package cft.focusstart.bondyuk.settings.chunks;

import cft.focusstart.bondyuk.settings.dataType.DataValidator;
import cft.focusstart.bondyuk.settings.dataType.DataWrapper;

public class ChunkService {
    public static String chunkCommand = "-chunk";

    public static ChunkMaxSize getSortingChunkMaxSize(Integer chunkSize) {
        return (chunkSize > 0) ? new ChunkMaxSize(chunkSize) : new ChunkMaxSize();
    }

    public static boolean isChunkCommand(String command) {
        if (command.contains(chunkCommand)) {
            return DataValidator.isDigit(command.substring(chunkCommand.length()));
        }

        return false;
    }

    public static Integer getCount(String string) {
        return DataWrapper.getInteger(string.substring(chunkCommand.length()));
    }
}
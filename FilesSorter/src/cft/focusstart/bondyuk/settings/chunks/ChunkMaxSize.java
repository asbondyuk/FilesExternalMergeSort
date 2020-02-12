package cft.focusstart.bondyuk.settings.chunks;

public class ChunkMaxSize {
    public static final int defaultSize = 100000;

    private int size;

    public ChunkMaxSize(int size) {
        this.size = size;
    }

    public ChunkMaxSize() {
        size = ChunkMaxSize.defaultSize;
    }

    public int getSize() {
        return size;
    }
}
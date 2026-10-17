package mediaconverter.subsystem;

public class BitrateReader {

    public int readSourceBitrate(MediaMetadata metadata) {
        return metadata.sizeKb() * 8 / metadata.durationSeconds();
    }

    public int chooseTargetBitrate(int sourceBitrate, String targetFormat) {
        return switch (targetFormat.toLowerCase()) {
            case "wav" -> 1411;                                            // lossless
            case "mp3" -> Math.max(128, Math.min(sourceBitrate, 320));     // clamp 128..320
            default -> throw new IllegalArgumentException("Unknown format: " + targetFormat);
        };
    }
}
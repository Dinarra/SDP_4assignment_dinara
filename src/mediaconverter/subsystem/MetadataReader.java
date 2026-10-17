package mediaconverter.subsystem;

public class MetadataReader {

    public MediaMetadata read(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("File name must not be empty");
        }
        int dot = fileName.lastIndexOf('.');
        if (dot <= 0 || dot == fileName.length() - 1) {
            throw new IllegalArgumentException("File has no extension: " + fileName);
        }
        String format = fileName.substring(dot + 1).toLowerCase();
        int duration = 60 + Math.abs(fileName.hashCode() % 240); // simulated: 60..299 sec
        int sizeKb = duration * 40;                              // simulated size
        return new MediaMetadata(fileName, format, duration, sizeKb);
    }
}
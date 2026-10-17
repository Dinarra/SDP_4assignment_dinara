package mediaconverter.subsystem;

public class CodecFactory {

    public Codec createCodec(String format) {
        return switch (format.toLowerCase()) {
            case "mp3" -> new Mp3Codec();
            case "wav" -> new WavCodec();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
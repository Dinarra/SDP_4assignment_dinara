package mediaconverter.facade;

import mediaconverter.subsystem.AudioMixer;
import mediaconverter.subsystem.BitrateReader;
import mediaconverter.subsystem.Codec;
import mediaconverter.subsystem.CodecFactory;
import mediaconverter.subsystem.MediaMetadata;
import mediaconverter.subsystem.MetadataReader;

public class MediaConversionFacade {

    private final MetadataReader metadataReader;
    private final CodecFactory codecFactory;
    private final BitrateReader bitrateReader;
    private final AudioMixer audioMixer;

    public MediaConversionFacade() {
        this(new MetadataReader(), new CodecFactory(), new BitrateReader(), new AudioMixer());
    }

    public MediaConversionFacade(MetadataReader metadataReader, CodecFactory codecFactory,
                                 BitrateReader bitrateReader, AudioMixer audioMixer) {
        this.metadataReader = metadataReader;
        this.codecFactory = codecFactory;
        this.bitrateReader = bitrateReader;
        this.audioMixer = audioMixer;
    }

    public ConversionResult convert(String fileName, String targetFormat) {
        try {
            MediaMetadata metadata = metadataReader.read(fileName);
            Codec codec = codecFactory.createCodec(targetFormat);

            int sourceBitrate = bitrateReader.readSourceBitrate(metadata);
            int targetBitrate = bitrateReader.chooseTargetBitrate(sourceBitrate, targetFormat);

            byte[] rawAudio = audioMixer.extractAudio(metadata);
            byte[] normalizedAudio = audioMixer.normalize(rawAudio);
            byte[] encoded = codec.encode(normalizedAudio, targetBitrate);

            return new ConversionResult(
                    replaceExtension(fileName, targetFormat), encoded.length, targetBitrate);
        } catch (IllegalArgumentException e) {
            throw new ConversionException(
                    "Cannot convert '" + fileName + "' to " + targetFormat + ": " + e.getMessage(), e);
        }
    }

    private String replaceExtension(String fileName, String newFormat) {
        return fileName.substring(0, fileName.lastIndexOf('.') + 1) + newFormat.toLowerCase();
    }
}
package mediaconverter;

import mediaconverter.subsystem.MediaMetadata;
import mediaconverter.subsystem.MetadataReader;
import mediaconverter.subsystem.BitrateReader;

public class AdvancedClientDemo {

    public static void main(String[] args) {
        MetadataReader reader = new MetadataReader();
        BitrateReader bitrateReader = new BitrateReader();

        MediaMetadata metadata = reader.read("song.wav");
        System.out.println("Duration: " + metadata.durationSeconds() + " s");
        System.out.println("Source bitrate: " + bitrateReader.readSourceBitrate(metadata) + " kbps");
    }
}
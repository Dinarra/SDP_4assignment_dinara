package mediaconverter;

import mediaconverter.facade.ConversionException;
import mediaconverter.facade.ConversionResult;
import mediaconverter.facade.MediaConversionFacade;

public class Main {

    public static void main(String[] args) {
        MediaConversionFacade converter = new MediaConversionFacade();

        // 1) happy path
        ConversionResult result = converter.convert("song.wav", "mp3");
        System.out.println("Created: " + result.outputFileName()
                + " (" + result.outputSizeBytes() + " bytes, " + result.bitrateKbps() + " kbps)");

        // 2) unsupported format
        try {
            converter.convert("song.wav", "flac");
        } catch (ConversionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3) file without extension
        try {
            converter.convert("song", "mp3");
        } catch (ConversionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
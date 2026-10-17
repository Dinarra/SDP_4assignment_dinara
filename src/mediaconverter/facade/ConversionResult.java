package mediaconverter.facade;

public record ConversionResult(String outputFileName, int outputSizeBytes, int bitrateKbps) {
}
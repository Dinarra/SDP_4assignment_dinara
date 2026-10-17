package mediaconverter.subsystem;

public interface Codec {
    String getFormat();

    byte[] encode(byte[] audio, int bitrateKbps);
}
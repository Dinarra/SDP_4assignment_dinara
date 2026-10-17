package mediaconverter.subsystem;

import java.nio.charset.StandardCharsets;

public class WavCodec implements Codec {

    @Override
    public String getFormat() {
        return "wav";
    }

    @Override
    public byte[] encode(byte[] audio, int bitrateKbps) {
        byte[] header = "RIFF".getBytes(StandardCharsets.US_ASCII);
        byte[] result = new byte[header.length + audio.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(audio, 0, result, header.length, audio.length);
        return result;
    }
}
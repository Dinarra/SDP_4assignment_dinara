package mediaconverter.subsystem;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Mp3Codec implements Codec {

    @Override
    public String getFormat() {
        return "mp3";
    }

    @Override
    public byte[] encode(byte[] audio, int bitrateKbps) {
        byte[] header = "ID3".getBytes(StandardCharsets.US_ASCII);
        int compressedLength = Math.max(1, audio.length * bitrateKbps / 1411);
        byte[] body = Arrays.copyOf(audio, compressedLength);
        byte[] result = new byte[header.length + body.length];
        System.arraycopy(header, 0, result, 0, header.length);
        System.arraycopy(body, 0, result, header.length, body.length);
        return result;
    }
}
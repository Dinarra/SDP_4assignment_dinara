package mediaconverter.subsystem;

import java.util.Random;

public class AudioMixer {

    public byte[] extractAudio(MediaMetadata metadata) {
        byte[] audio = new byte[metadata.durationSeconds() * 100];
        Random random = new Random(metadata.fileName().hashCode());
        for (int i = 0; i < audio.length; i++) {
            audio[i] = (byte) (random.nextInt(100) - 50);   // simulated samples
        }
        return audio;
    }

    public byte[] normalize(byte[] audio) {
        int peak = 1;
        for (byte sample : audio) {
            peak = Math.max(peak, Math.abs(sample));
        }
        byte[] normalized = new byte[audio.length];
        for (int i = 0; i < audio.length; i++) {
            normalized[i] = (byte) (audio[i] * 127 / peak);
        }
        return normalized;
    }
}
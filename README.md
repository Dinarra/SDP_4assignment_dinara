# Facade Pattern: Media Converter

Individual Assignment #4 for the course ShP-2216 "Software Design Patterns".
Author: Dinara

## Description
Java implementation of the **Facade** structural design pattern.
`MediaConversionFacade` hides a complex audio conversion subsystem behind one
simple method:

```java
ConversionResult result = converter.convert("song.wav", "mp3");
```

## Project structure
```
src/mediaconverter/
├── Main.java                       client, uses only the facade
├── AdvancedClientDemo.java         client that bypasses the facade
├── facade/
│   ├── MediaConversionFacade.java  the Facade
│   ├── ConversionResult.java       result returned to the client
│   └── ConversionException.java    single error type of the facade
└── subsystem/
    ├── MediaMetadata.java          data about a media file
    ├── MetadataReader.java         reads and validates file metadata
    ├── BitrateReader.java          calculates source / target bitrate
    ├── AudioMixer.java             extracts and normalizes audio
    ├── Codec.java                  codec interface
    ├── Mp3Codec.java               MP3 encoder (simulated)
    ├── WavCodec.java               WAV encoder (simulated)
    └── CodecFactory.java           creates a codec by format name
```

## How it works
`convert()` runs the steps in the correct order:
1. read metadata
2. create codec
3. read and choose bitrate
4. extract audio
5. normalize audio
6. encode

Subsystem classes do not know about the facade and can still be used directly
(see `AdvancedClientDemo`). Media data is simulated.

## How to run
Requirements: JDK 17.
Open the project in IntelliJ IDEA and run `mediaconverter.Main`.

Expected output:
```
Created: song.mp3 (5423 bytes, 320 kbps)
Error: Cannot convert 'song.wav' to flac: Unsupported format: flac
Error: Cannot convert 'song' to mp3: File has no extension: song
```
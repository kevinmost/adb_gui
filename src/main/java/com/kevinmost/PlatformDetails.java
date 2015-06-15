package com.kevinmost;

import org.jetbrains.annotations.NotNull;
import sun.awt.OSInfo;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

public class PlatformDetails {
    private static PlatformDetails INSTANCE;

    public static @NotNull PlatformDetails get() {
        if (INSTANCE == null) {
            INSTANCE = new PlatformDetails();
        }
        return INSTANCE;
    }


    public final OSInfo.OSType os;
    public final URL osResourceFolder;

    private PlatformDetails() {
        os = OSInfo.getOSType();
        osResourceFolder = getOsResourceFolder();
    }

    private URL getOsResourceFolder() {
        return ClassLoader.getSystemClassLoader().getResource(getResourceDirectoryFromOsVersion());
    }

    private String getResourceDirectoryFromOsVersion() {
        switch (os) {
            case WINDOWS:
                return "windows";
            case MACOSX:
                return "mac";
            case LINUX:
                return "linux";
        }
        throw new IllegalStateException("Unknown OS; only Windows, Mac, and Linux supported");
    }

}

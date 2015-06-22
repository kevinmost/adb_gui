package com.kevinmost;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.awt.OSInfo;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

public class PlatformDetails {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformDetails.class);
    private static PlatformDetails INSTANCE;

    @NotNull
    public static PlatformDetails get() {
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
                break;
            default:
                LOGGER.warn("This OS is not supported, will try Linux ADB implementation anyway");
        }
        return "linux";
    }
}

import java.util.Locale;

public class PlatformDetails {
    private static PlatformDetails INSTANCE;

    public static PlatformDetails get() {
        if (INSTANCE == null) {
            INSTANCE = new PlatformDetails();
        }
        return INSTANCE;
    }


    public final OSType os;

    private PlatformDetails() {
        os = getOperatingSystemType();
    }

    public enum OSType {
        Windows("windows"),
        MacOS("mac"),
        Linux("linux"),
        ;

        public final String resourcePathRoot;

        OSType(String resourcePathRoot) {
            this.resourcePathRoot = resourcePathRoot;
        }
    }

    public static OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        final OSType os;
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            os = OSType.MacOS;
        } else if (OS.contains("win")) {
            os = OSType.Windows;
        } else if (OS.contains("nux")) {
            os = OSType.Linux;
        } else {
            throw new IllegalStateException("Can't determine OS version!");
        }
        return os;
    }
}

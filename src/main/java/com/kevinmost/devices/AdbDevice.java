package com.kevinmost.devices;

public class AdbDevice {
    /**
     * The device's identifier when using ADB. Android ID for devices, IP address for Genymotion, etc.
     */
    public String id;
    /**
     * The device's internal name. Ex: Nexus 6 -> "shamu", HTC Desire -> "bravo".
     */
    public String device;
    /**
     * THe device's readable name. Ex: Nexus 6 -> "Nexus_6", HTC Desire -> "HTC_Desire"
     */
    public String model;

    public String[] asOrderedArray() {
        return new String[]{id, device, model};
    }
}

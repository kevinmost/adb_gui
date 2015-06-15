package com.kevinmost.command;

import com.kevinmost.devices.AdbDevicesModel;

public class AdbDevicesCommand implements TerminalCommand {
    @Override
    public String getTerminalCommand(String... args) {
        return "adb devices -l";
    }
}

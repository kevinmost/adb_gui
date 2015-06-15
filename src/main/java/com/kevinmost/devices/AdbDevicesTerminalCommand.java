package com.kevinmost.devices;

import com.kevinmost.command.TerminalCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class AdbDevicesTerminalCommand implements TerminalCommand<List<AdbDevice>> {
  @Override
  @NotNull
  public List<AdbDevice> transformToResult(String stdOut, String stdErr) {
    final List<AdbDevice> returnList = new ArrayList<AdbDevice>();
    final String[] lines = stdOut.split("\n");
    for (String line : lines) {
      if (line.contains("    ")) { // All actual ADB devices contain these spaces between the ID and other fields
        returnList.add(getAdbDeviceFromString(line));
      }
    }
    return returnList;
  }

  @Override
  public String getTerminalCommand(String... args) {
    return "adb devices -l";
  }

  private static AdbDevice getAdbDeviceFromString(String str) {
    final String[] words = str.split("\\s+");
    final AdbDevice device = new AdbDevice();
    device.id = words[0];
    device.device = getFromLabel(str, "device:");
    device.model = getFromLabel(str, "model:");
    return device;
  }

  private static String getFromLabel(String str, String label) {
    final int beginIndex = str.indexOf(label) + label.length();
    final int endIndex = str.indexOf(' ', beginIndex);
    if (endIndex < 0) {
      return str.substring(beginIndex);
    }
    return str.substring(beginIndex, endIndex);
  }
}

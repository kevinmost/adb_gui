package com.kevinmost.devices;

import com.kevinmost.Controller;
import com.kevinmost.command.AdbDevicesCommand;
import com.kevinmost.command.CommandOutputTransformer;
import com.kevinmost.command.CommandPackage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdbDevicesController extends Controller<AdbDevicesModel, AdbDeviceView> {

  public static final int ADB_DEVICES_UPDATE_FREQUENCY_MS = 500;

  public AdbDevicesController(AdbDevicesModel model, AdbDeviceView view) {
    super(model, view);
    final Timer timer = new Timer(ADB_DEVICES_UPDATE_FREQUENCY_MS, getActionListener());
    timer.setRepeats(true);
    timer.start();
  }

  @Override
  protected ActionListener getActionListener() {
    return new ActionListener() {
      @Override
      public void actionPerformed(@NotNull ActionEvent e) {
        final List<AdbDevice> newModel = getDevices();
        model.setDevices(newModel);
        view.updateFromModel(newModel);
      }
    };
  }

  private static List<AdbDevice> getDevices() {
    final CommandOutputTransformer<List<AdbDevice>> transformer = new CommandOutputTransformer<List<AdbDevice>>() {
      @Override
      public List<AdbDevice> getResult(String stdOut, String stdErr) {
        final List<AdbDevice> returnList = new ArrayList<AdbDevice>();
        final String[] lines = stdOut.split("\n");
        for (String line : lines) {
          if (line.contains("    ")) { // All actual ADB devices contain these spaces between the ID and other fields
            returnList.add(getAdbDeviceFromString(line));
          }
        }
        return returnList;
      }
    };
    return CommandPackage.executeCommand(new AdbDevicesCommand(), transformer);
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

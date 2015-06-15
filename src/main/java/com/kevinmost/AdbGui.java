package com.kevinmost;

import com.kevinmost.devices.AdbDevice;
import com.kevinmost.devices.AdbDeviceView;
import com.kevinmost.devices.AdbDevicesController;
import com.kevinmost.devices.AdbDevicesModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AdbGui {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ignored) {
      // No need to crash just because we can't set the theme properly.
    }
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        AdbDevicesModel model = new AdbDevicesModel();
        final AdbDeviceView view = new AdbDeviceView();
        new AdbDevicesController(model, view);
      }
    });
  }
}

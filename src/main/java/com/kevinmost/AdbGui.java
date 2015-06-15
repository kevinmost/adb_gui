package com.kevinmost;

import com.kevinmost.devices.AdbDevicesView;
import com.kevinmost.devices.AdbDevicesController;
import com.kevinmost.devices.AdbDevicesModel;

import javax.swing.*;

public class AdbGui {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ignored) { /* No need to crash just because we can't set the theme properly. */ }

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        final AdbDevicesModel model = new AdbDevicesModel();
        final AdbDevicesView view = new AdbDevicesView();
        new AdbDevicesController(model, view);
      }
    });
  }
}

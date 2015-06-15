package com.kevinmost.devices;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class AdbDevicesView {
  public static final String TITLE = "ADB Devices";

  private final JFrame frame = new JFrame(TITLE);

  private final MigLayout contentLayout = new MigLayout();
  private final JPanel content = new JPanel(contentLayout, true);

  public AdbDevicesView() {
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.add(content, BorderLayout.CENTER);
  }

  public void updateFromModel(List<AdbDevice> devices) {
    content.removeAll();
    for (AdbDevice device : devices) {
      addRow(device);
    }
    if (!frame.isVisible()) {
      frame.setVisible(true);
      frame.pack();
    }
    frame.revalidate();
    frame.repaint();
  }

  private void addRow(AdbDevice device) {
    final String[] fields = device.asOrderedArray();
    for (int i = 0; i < fields.length; i++) {
      final Component column = new JLabel(fields[i]);
      // The last column has a "wrap" attribute to denote the end of a row.
      if (i == fields.length - 1) {
        content.add(column, "wrap");
      } else {
        content.add(column, "growx");
      }
    }
  }
}

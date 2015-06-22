package com.kevinmost.devices;

import com.kevinmost.AdbGuiView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdbDevicesView implements AdbGuiView<List<AdbDevice>, JMenu> {

    private final JMenu content = new JMenu("pls");

    @Override
    public void updateFromModel(List<AdbDevice> devices) {
        content.removeAll();
        for (AdbDevice device : devices) {
            addRow(device);
        }
    }

    private void addRow(AdbDevice device) {
        final String[] fields = device.asOrderedArray();
        for (String field : fields) {
            final Component deviceView = new JMenuItem(field);
            content.add(deviceView);
        }
    }

    @Override
    public void addToContainer(Container container) {
        container.add(content);
        container.invalidate();
        container.repaint();
    }

    @Override
    public JMenu getView() {
        return content;
    }
}

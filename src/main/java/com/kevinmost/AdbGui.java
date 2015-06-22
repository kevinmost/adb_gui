package com.kevinmost;

import com.kevinmost.devices.AdbDevicesView;
import com.kevinmost.devices.AdbDevicesController;
import com.kevinmost.devices.AdbDevicesModel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

public class AdbGui {

    private static final JFrame FRAME = new JFrame(AdbGui.class.getSimpleName());
    private static final MigLayout LAYOUT_MANAGER = new MigLayout();

    public static void main(String[] args) {
        FRAME.setLayout(LAYOUT_MANAGER);
        FRAME.setJMenuBar(new JMenuBar());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { /* No need to crash just because we can't set the theme properly. */ }

        invokeLater(new Runnable() {
            @Override
            public void run() {
                final AdbDevicesModel model = new AdbDevicesModel();
                final AdbDevicesView view = new AdbDevicesView();
                new AdbDevicesController(model, view);
                view.addToContainer(FRAME.getJMenuBar());
            }
        });

        FRAME.pack();
        FRAME.setVisible(true);
    }
}

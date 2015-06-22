package com.kevinmost.devices;

import com.kevinmost.Controller;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdbDevicesController extends Controller<AdbDevicesModel, AdbDevicesView> {

    public AdbDevicesController(AdbDevicesModel model, AdbDevicesView view) {
        super(model, view);
        view.getView().addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                getActionListener().actionPerformed(null);
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
    }

    @Override
    protected ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final List<AdbDevice> newModelData = getDevices();
                model.setDevices(newModelData);
                view.updateFromModel(newModelData);
            }
        };
    }

    private List<AdbDevice> getDevices() {
        return new AdbDevicesCommand().execute();
    }
}

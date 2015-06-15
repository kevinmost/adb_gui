package com.kevinmost.devices;

import com.kevinmost.Controller;
import com.kevinmost.command.CommandExecutor;
import com.kevinmost.command.TerminalCommand;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdbDevicesController extends Controller<AdbDevicesModel, AdbDevicesView> {

  public static final int ADB_DEVICES_UPDATE_FREQUENCY_MS = 500;

  public AdbDevicesController(AdbDevicesModel model, AdbDevicesView view) {
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
    final TerminalCommand<List<AdbDevice>> transformer = new AdbDevicesTerminalCommand();
    return CommandExecutor.executeCommand(transformer);
  }

}

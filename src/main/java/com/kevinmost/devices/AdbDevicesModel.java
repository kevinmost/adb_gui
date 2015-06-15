package com.kevinmost.devices;

import java.util.List;

public class AdbDevicesModel {
  private List<AdbDevice> devices;

  private int currentlySelectedIndex = -1;

  public List<AdbDevice> getDevices() {
    return devices;
  }

  public void setDevices(List<AdbDevice> devices) {
    this.devices = devices;
  }

  public int getCurrentlySelectedIndex() {
    return currentlySelectedIndex;
  }

  public void setCurrentlySelectedIndex(int currentlySelectedIndex) {
    this.currentlySelectedIndex = currentlySelectedIndex;
  }
}

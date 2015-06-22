package com.kevinmost;

import java.awt.*;

public interface AdbGuiView<T, C extends Component> {
    void updateFromModel(T data);
    void addToContainer(Container container);
    C getView();
}

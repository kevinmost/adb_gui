package com.kevinmost;

import java.awt.event.ActionListener;

public abstract class Controller<M, V extends AdbGuiView<?, ?>> {
    protected final M model;
    protected final V view;

    public Controller(M model, V view) {
        this.model = model;
        this.view = view;
    }

    protected abstract ActionListener getActionListener();
}

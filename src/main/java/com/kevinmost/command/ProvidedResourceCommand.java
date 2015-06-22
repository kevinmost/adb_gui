package com.kevinmost.command;

import com.kevinmost.PlatformDetails;

import java.io.File;

/**
 * A command that should be run out of the provided resources folder in this app, and thus requires a custom
 * implementation of {@link Command#execute(String...)}.
 * <p/>
 * ADB and fastboot commands are examples of commands that extend this class, since we bundle these binaries with the
 * app so that users don't need to have them downloaded.
 */
public abstract class ProvidedResourceCommand<RESULT> extends Command<RESULT> {
    @Override
    public RESULT execute(String... args) {
        final String resourceFolderPath = PlatformDetails.get().osResourceFolder.getPath() + File.separatorChar;
        return executeCommand(resourceFolderPath + getTerminalCommand(args));
    }
}

package com.kevinmost.command;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public abstract class Command<RESULT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Command.class);
    private static final Runtime RUNTIME = Runtime.getRuntime();

    /**
     * Defines the command that is executed for this TerminalCommand.
     * The {@param args} array should be treated as the user's space-delimited input from the
     * command-line.
     */
    protected abstract String getTerminalCommand(String... args);

    /**
     * This method is called on the result of {@link this#getTerminalCommand(String...)} to transform the result into a
     * usable data-object.
     */
    @NotNull
    protected abstract RESULT transformToResult(String stdOut, String stdErr);

    public RESULT execute(String... args) {
        return executeCommand(getTerminalCommand(args));
    }

    @Nullable
    final RESULT executeCommand(String command) {
        try {
            final Process result = RUNTIME.exec(command);
            final String stdOut = streamToString(result.getInputStream());
            final String stdErr = streamToString(result.getErrorStream());
            return transformToResult(stdOut, stdErr);
        } catch (IOException e) {
            LOGGER.error("Could not execute command " + getTerminalCommand(), e);
        }
        return null;
    }

    final String streamToString(InputStream stream) {
        try {
            return IOUtils.toString(stream, Charsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Could not convert stream to string ", e);
            return "";
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }
}

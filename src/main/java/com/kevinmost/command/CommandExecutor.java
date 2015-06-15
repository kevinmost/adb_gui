package com.kevinmost.command;

import com.kevinmost.PlatformDetails;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CommandExecutor {
    private static final Runtime RUNTIME = Runtime.getRuntime();

    public static <T> T executeCommand(TerminalCommand<T> command, String... args) {
        try {
            final String resourceFolderPath = PlatformDetails.get().osResourceFolder.getPath() + File.separatorChar + command.getTerminalCommand(args);
            final Process result = RUNTIME.exec(resourceFolderPath);
            final String stdOut = streamToString(result.getInputStream());
            final String stdErr = streamToString(result.getErrorStream());
            return command.transformToResult(stdOut, stdErr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String streamToString(InputStream stream) {
        try {
            return IOUtils.toString(stream, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }
}

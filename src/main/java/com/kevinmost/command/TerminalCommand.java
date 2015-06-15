package com.kevinmost.command;

import org.jetbrains.annotations.NotNull;

public interface TerminalCommand<RESULT> {
    /**
     * This method is called on the result of {@link this#getTerminalCommand(String...)} to transform the result into a
     * usable data-object.
     */
    @NotNull RESULT transformToResult(String stdOut, String stdErr);

    /**
     * Defines the command that is executed for this TerminalCommand.
     * The {@param args} array should be treated as the user's space-delimited input from the
     * command-line.
     */
    String getTerminalCommand(String... args);
}

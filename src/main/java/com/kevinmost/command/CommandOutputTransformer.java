package com.kevinmost.command;

public interface CommandOutputTransformer<RESULT> {
    RESULT getResult(String stdOut, String stdErr);
}

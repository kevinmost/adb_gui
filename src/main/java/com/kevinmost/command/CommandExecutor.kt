package com.kevinmost.command

import java.io.InputStreamReader

public fun <RESULT> executeCommand(
        command: TerminalCommand, transformer: CommandOutputTransformer<RESULT>, vararg args: String) : RESULT {
    val runtime = Runtime.getRuntime();
    // TODO: Why don't the varargs go into getTerminalCommand properly?
    val exec = runtime.exec(command.getTerminalCommand(args));
    val stdout : String = InputStreamReader(exec.getInputStream()).readText();
    val stderr : String = InputStreamReader(exec.getErrorStream()).readText();
    return transformer.getResult(stdout, stderr);
}

package com.alastairlundy.platformkit.shared;

import javax.naming.OperationNotSupportedException;
import java.io.File;
import java.io.IOException;

public class CommandRunner {

    public static String runCmdCommand(String command) throws IOException, OperationNotSupportedException {
        if(PlatformAnalyzer.isWindows()){
            String sysDir = System.getenv("WINDIR\\system32");

            String fullPath = sysDir + File.pathSeparator + "cmd.exe";

            return ProcessRunner.runProcess(fullPath, new String[]{command});
        }
        throw new OperationNotSupportedException();
    }

    public static String runWindowsPowershellCommand(String command) throws IOException, OperationNotSupportedException {
       if(PlatformAnalyzer.isWindows()){
           String sysDir = System.getenv("WINDIR\\system32\\WindowsPowerShell\\v1.0\\");

           String fullPath = sysDir + File.pathSeparator + "cmd.exe";

           return ProcessRunner.runProcess(fullPath, new String[]{command});
       }
       throw new OperationNotSupportedException();
    }

    public static String runCommandOnMac(String commandName, String[] arguments) throws IOException {
        return ProcessRunner.runProcess("/usr/bin/" + commandName, arguments);
    }

    /**
     *
     * @param commandName
     * @param arguments
     * @return
     * @throws IOException
     */
    public static String runCommandOnLinux(String commandName, String[] arguments) throws IOException {
        return ProcessRunner.runProcess("/usr/bin/" + commandName, arguments);
    }

    public static String runCommandOnFreeBsd(String commandName, String[] arguments) throws IOException {
        return runCommandOnLinux(commandName, arguments);
    }
}

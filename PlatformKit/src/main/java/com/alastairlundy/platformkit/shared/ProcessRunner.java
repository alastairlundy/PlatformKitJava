package com.alastairlundy.platformkit.shared;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class ProcessRunner {

    /**
     *
     * @param pathToExecutable
     * @param arguments
     * @return
     * @throws IOException
     */
    public static String runProcess(String pathToExecutable, String[] arguments) throws IOException {
        String[] array = new String[arguments.length + 1];
        array[0] = pathToExecutable;

        System.arraycopy(arguments, 0, array, 1, arguments.length);

        ProcessBuilder processBuilder = new ProcessBuilder(array);

        Process process = processBuilder.start();

        // Get input stream from the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader.lines().toString();
    }

    /**
     *
     * @param pathToExecutable
     * @param arguments
     * @param appendExeIfNeeded
     * @return
     * @throws IOException
     */
    public static String runProcessOnWindows(String pathToExecutable, String[] arguments, boolean appendExeIfNeeded) throws IOException {
        if(!pathToExecutable.toLowerCase().endsWith(".exe") && appendExeIfNeeded){
            pathToExecutable += ".exe";
        }
        return runProcess(pathToExecutable, arguments);
    }

    /**
     *
     * @param url
     * @param allowNonSecureHttp
     * @throws OperationNotSupportedException
     * @throws IOException
     */
    public static void openUrlInBrowser(String url, boolean allowNonSecureHttp) throws OperationNotSupportedException, IOException {
        if ((!url.startsWith("https://") || !url.startsWith("www.")) && (!url.startsWith("file://")))
        {
            if (allowNonSecureHttp)
            {
                url = "http://" + url;
            }
            else
            {
                url = "https://" + url;
            }
        }
        else if (url.startsWith("http://") && !allowNonSecureHttp)
        {
            url = url.replace("http://", "https://");
        }

        if (PlatformAnalyzer.isWindows())
        {
            CommandRunner.runCmdCommand("/c start " + url.replace("&", "^&"));
        }
        if (PlatformAnalyzer.isLinux())
        {
            CommandRunner.runCommandOnLinux("xdg-open " + url, new String[]{""});
        }
        if (PlatformAnalyzer.isMac())
        {
            CommandRunner.runCommandOnMac("open", new String[]{url});
        }
        if(PlatformAnalyzer.isFreeBSD()){
            CommandRunner.runCommandOnFreeBsd("xdg-open" + url, new String[]{""});
        }
    }
}

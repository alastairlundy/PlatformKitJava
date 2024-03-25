package com.alastairlundy.platformkit.internal.exceptions.windows;

public class WindowsVersionDetectionException extends Exception{

    public WindowsVersionDetectionException(){
        super("Failed to detect the version of Windows currently running.");
    }
}

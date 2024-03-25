package com.alastairlundy.platformkit.internal.exceptions.linux;

public class LinuxVersionDetectionException extends Exception{

    LinuxVersionDetectionException(){
        super("Failed to detect the version of Linux running on this computer.");
    }
}

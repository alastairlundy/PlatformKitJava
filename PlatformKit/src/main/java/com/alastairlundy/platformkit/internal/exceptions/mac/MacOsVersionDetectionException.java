package com.alastairlundy.platformkit.internal.exceptions.mac;

public class MacOsVersionDetectionException extends Exception{

    public MacOsVersionDetectionException(){
        super("Failed to detect the version of macOS running on this computer.");
    }
}

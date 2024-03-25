package com.alastairlundy.platformkit.internal.exceptions;

public class OperatingSystemDetectionException extends Exception{

    OperatingSystemDetectionException(){
        super("Failed to detect details about the Operating System running on this device.");
    }
}

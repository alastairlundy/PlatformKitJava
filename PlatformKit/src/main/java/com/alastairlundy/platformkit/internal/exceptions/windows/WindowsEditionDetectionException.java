package com.alastairlundy.platformkit.internal.exceptions.windows;

public class WindowsEditionDetectionException  extends Exception{

    WindowsEditionDetectionException(){
        super("Failed to detect the Edition of Windows running on this computer.");
    }
}

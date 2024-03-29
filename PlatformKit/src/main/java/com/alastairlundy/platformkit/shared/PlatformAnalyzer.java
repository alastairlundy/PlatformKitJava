/*
      PlatformKit Java

      Copyright (c) Alastair Lundy 2024

      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
      */

package com.alastairlundy.platformkit.shared;

import com.alastairlundy.platformkit.platforms.linux.LinuxAnalyzer;
import com.alastairlundy.platformkit.platforms.mac.MacOsAnalyzer;
import com.alastairlundy.platformkit.platforms.windows.WindowsAnalyzer;
import com.alastairlundy.utilityjar.Version;
import jdk.jshell.spi.ExecutionControl;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

public class PlatformAnalyzer {

    protected LinuxAnalyzer linuxAnalyzer;
    protected WindowsAnalyzer windowsAnalyzer;
    protected MacOsAnalyzer macOsAnalyzer;

    PlatformAnalyzer(){
        linuxAnalyzer = new LinuxAnalyzer();
        windowsAnalyzer = new WindowsAnalyzer();
        macOsAnalyzer = new MacOsAnalyzer();
    }


    protected static String getOsName(){
        return System.getProperty("os.name");
    }

    public static boolean isWindows(){
        return getOsName().toLowerCase().contains("windows");
    }

    public static boolean isMac(){
        return getOsName().toLowerCase().contains("macos") || getOsName().toLowerCase().contains("os x") || getOsName().toLowerCase().contains("darwin");
    }

    public static boolean isLinux(){
        return getOsName().toLowerCase().contains("linux");
    }

    public static boolean isFreeBSD(){
        return getOsName().toLowerCase().contains("freebsd");
    }

    public Version detectOSVersion() throws IOException, OperationNotSupportedException {
        if(isWindows()){
            return windowsAnalyzer.getWindowsVersion();
        }
        if(isMac()){
            return macOsAnalyzer.getMacOsVersion();
        }
        if(isLinux()){
            return linuxAnalyzer.getLinuxDistributionVersion();
        }
//        if(isFreeBSD()){
//            throw new ExecutionControl.NotImplementedException();
//        }
        throw new OperationNotSupportedException();
    }
}

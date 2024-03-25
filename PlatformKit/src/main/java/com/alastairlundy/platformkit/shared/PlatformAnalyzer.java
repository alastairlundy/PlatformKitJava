/*
      PlatformKit Java

      Copyright (c) Alastair Lundy 2024

      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
      */

package com.alastairlundy.platformkit.shared;

import com.alastairlundy.platformkit.platforms.linux.LinuxAnalyzer;
import com.alastairlundy.platformkit.platforms.windows.WindowsAnalyzer;
import com.alastairlundy.utilityjar.Version;

import java.io.IOException;

public class PlatformAnalyzer {

    protected LinuxAnalyzer linuxAnalyzer;
    protected WindowsAnalyzer windowsAnalyzer;

    PlatformAnalyzer(){
        linuxAnalyzer = new LinuxAnalyzer();
        windowsAnalyzer = new WindowsAnalyzer();
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

    public Version detectOSVersion() throws IOException {
        if(isWindows()){
            return windowsAnalyzer.getWindowsVersion();
        }
        if(isMac()){

        }
        if(isLinux()){

        }
    }
}

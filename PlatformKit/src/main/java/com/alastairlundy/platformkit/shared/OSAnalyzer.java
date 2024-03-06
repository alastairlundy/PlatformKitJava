/*
      PlatformKit Java

      Copyright (c) Alastair Lundy 2024

      This Source Code Form is subject to the terms of the Mozilla Public
      License, v. 2.0. If a copy of the MPL was not distributed with this
      file, You can obtain one at http://mozilla.org/MPL/2.0/.
      */

package com.alastairlundy.platformkit.shared;

public class OSAnalyzer {

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

    }

    public Version detectOSVersion(){

    }
}

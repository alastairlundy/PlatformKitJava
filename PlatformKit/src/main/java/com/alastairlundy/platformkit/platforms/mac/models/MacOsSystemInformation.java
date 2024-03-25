package com.alastairlundy.platformkit.platforms.mac.models;

import com.alastairlundy.platformkit.platforms.mac.enums.MacProcessorType;
import com.alastairlundy.utilityjar.Version;

public class MacOsSystemInformation {

    protected Version macOsVersion;

    protected Version darwinVersion;

    protected Version xnuVersion;

    protected String macOsBuildNumber;

    protected MacProcessorType processorType;

    public Version getMacOsVersion() {
        return macOsVersion;
    }

    public void setMacOsVersion(Version macOsVersion) {
        this.macOsVersion = macOsVersion;
    }

    public Version getDarwinVersion() {
        return darwinVersion;
    }

    public void setDarwinVersion(Version darwinVersion) {
        this.darwinVersion = darwinVersion;
    }

    public Version getXnuVersion() {
        return xnuVersion;
    }

    public void setXnuVersion(Version xnuVersion) {
        this.xnuVersion = xnuVersion;
    }

    public String getMacOsBuildNumber() {
        return macOsBuildNumber;
    }

    public void setMacOsBuildNumber(String macOsBuildNumber) {
        this.macOsBuildNumber = macOsBuildNumber;
    }

    public MacProcessorType getProcessorType() {
        return processorType;
    }

    public void setProcessorType(MacProcessorType processorType) {
        this.processorType = processorType;
    }
}

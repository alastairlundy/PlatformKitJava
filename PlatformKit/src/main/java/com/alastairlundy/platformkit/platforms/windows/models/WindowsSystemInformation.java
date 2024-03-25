package com.alastairlundy.platformkit.platforms.windows.models;

import com.alastairlundy.platformkit.shared.models.NetworkCard;

public class WindowsSystemInformation {

    protected String hostName;
    protected String osName;
    protected String osVersion;
    protected String osManufacturer;
    protected String osConfiguration;
    protected String osBuildType;
    protected String registeredOwner;
    protected String registeredOrganization;

    protected String productId;

    protected String systemManufacturer;
    protected String systemModel;
    protected String systemType;
    protected String[] processors;
    protected String biosVersion;
    protected String windowsDirectory;
    protected String systemDirectory;

    protected String bootDevice;
    protected String systemLocale;
    protected String inputLocale;

    protected String domain;

    protected String logonServer;

    protected String[] hotfixesInstalled;
    protected String[] pageFileLocations;

    protected NetworkCard[] networkCards;

    protected HyperVRequirements hyperVRequirements;

    protected int totalPhysicalMemoryMB;
    protected int availablePhysicalMemoryMB;

    protected int virtualMemoryMaxSizeMB;
    protected int virtualMemoryAvailableSizeMB;
    protected int virtualMemoryInUseMB;

    public int getTotalPhysicalMemoryMB() {
        return totalPhysicalMemoryMB;
    }

    public void setTotalPhysicalMemoryMB(int totalPhysicalMemoryMB) {
        this.totalPhysicalMemoryMB = totalPhysicalMemoryMB;
    }

    public int getAvailablePhysicalMemoryMB() {
        return availablePhysicalMemoryMB;
    }

    public void setAvailablePhysicalMemoryMB(int availablePhysicalMemoryMB) {
        this.availablePhysicalMemoryMB = availablePhysicalMemoryMB;
    }

    public int getVirtualMemoryMaxSizeMB() {
        return virtualMemoryMaxSizeMB;
    }

    public void setVirtualMemoryMaxSizeMB(int virtualMemoryMaxSizeMB) {
        this.virtualMemoryMaxSizeMB = virtualMemoryMaxSizeMB;
    }

    public int getVirtualMemoryAvailableSizeMB() {
        return virtualMemoryAvailableSizeMB;
    }

    public void setVirtualMemoryAvailableSizeMB(int virtualMemoryAvailableSizeMB) {
        this.virtualMemoryAvailableSizeMB = virtualMemoryAvailableSizeMB;
    }

    public int getVirtualMemoryInUseMB() {
        return virtualMemoryInUseMB;
    }

    public void setVirtualMemoryInUseMB(int virtualMemoryInUseMB) {
        this.virtualMemoryInUseMB = virtualMemoryInUseMB;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsManufacturer() {
        return osManufacturer;
    }

    public void setOsManufacturer(String osManufacturer) {
        this.osManufacturer = osManufacturer;
    }

    public String getOsConfiguration() {
        return osConfiguration;
    }

    public void setOsConfiguration(String osConfiguration) {
        this.osConfiguration = osConfiguration;
    }

    public String getOsBuildType() {
        return osBuildType;
    }

    public void setOsBuildType(String osBuildType) {
        this.osBuildType = osBuildType;
    }

    public String getRegisteredOwner() {
        return registeredOwner;
    }

    public void setRegisteredOwner(String registeredOwner) {
        this.registeredOwner = registeredOwner;
    }

    public String getRegisteredOrganization() {
        return registeredOrganization;
    }

    public void setRegisteredOrganization(String registeredOrganization) {
        this.registeredOrganization = registeredOrganization;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSystemManufacturer() {
        return systemManufacturer;
    }

    public void setSystemManufacturer(String systemManufacturer) {
        this.systemManufacturer = systemManufacturer;
    }

    public String getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String[] getProcessors() {
        return processors;
    }

    public void setProcessors(String[] processors) {
        this.processors = processors;
    }

    public String getBiosVersion() {
        return biosVersion;
    }

    public void setBiosVersion(String biosVersion) {
        this.biosVersion = biosVersion;
    }

    public String getWindowsDirectory() {
        return windowsDirectory;
    }

    public void setWindowsDirectory(String windowsDirectory) {
        this.windowsDirectory = windowsDirectory;
    }

    public String getSystemDirectory() {
        return systemDirectory;
    }

    public void setSystemDirectory(String systemDirectory) {
        this.systemDirectory = systemDirectory;
    }

    public String getBootDevice() {
        return bootDevice;
    }

    public void setBootDevice(String bootDevice) {
        this.bootDevice = bootDevice;
    }

    public String getSystemLocale() {
        return systemLocale;
    }

    public void setSystemLocale(String systemLocale) {
        this.systemLocale = systemLocale;
    }

    public String getInputLocale() {
        return inputLocale;
    }

    public void setInputLocale(String inputLocale) {
        this.inputLocale = inputLocale;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogonServer() {
        return logonServer;
    }

    public void setLogonServer(String logonServer) {
        this.logonServer = logonServer;
    }

    public String[] getHotfixesInstalled() {
        return hotfixesInstalled;
    }

    public void setHotfixesInstalled(String[] hotfixesInstalled) {
        this.hotfixesInstalled = hotfixesInstalled;
    }

    public String[] getPageFileLocations() {
        return pageFileLocations;
    }

    public void setPageFileLocations(String[] pageFileLocations) {
        this.pageFileLocations = pageFileLocations;
    }

    public NetworkCard[] getNetworkCards() {
        return networkCards;
    }

    public void setNetworkCards(NetworkCard[] networkCards) {
        this.networkCards = networkCards;
    }

    public HyperVRequirements getHyperVRequirements() {
        return hyperVRequirements;
    }

    public void setHyperVRequirements(HyperVRequirements hyperVRequirements) {
        this.hyperVRequirements = hyperVRequirements;
    }
}

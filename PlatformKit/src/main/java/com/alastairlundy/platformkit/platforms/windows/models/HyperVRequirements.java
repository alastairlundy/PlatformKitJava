package com.alastairlundy.platformkit.platforms.windows.models;

public class HyperVRequirements {

    protected boolean vmMonitorModeExtensions;

    protected boolean virtualizationEnabledInFirmware;

    protected boolean secondLevelAddressTranslation;

    protected boolean dataExecutionPreventionAvailable;

    public boolean isVmMonitorModeExtensions() {
        return vmMonitorModeExtensions;
    }

    public void setVmMonitorModeExtensions(boolean vmMonitorModeExtensions) {
        this.vmMonitorModeExtensions = vmMonitorModeExtensions;
    }

    public boolean isVirtualizationEnabledInFirmware() {
        return virtualizationEnabledInFirmware;
    }

    public void setVirtualizationEnabledInFirmware(boolean virtualizationEnabledInFirmware) {
        this.virtualizationEnabledInFirmware = virtualizationEnabledInFirmware;
    }

    public boolean isSecondLevelAddressTranslation() {
        return secondLevelAddressTranslation;
    }

    public void setSecondLevelAddressTranslation(boolean secondLevelAddressTranslation) {
        this.secondLevelAddressTranslation = secondLevelAddressTranslation;
    }

    public boolean isDataExecutionPreventionAvailable() {
        return dataExecutionPreventionAvailable;
    }

    public void setDataExecutionPreventionAvailable(boolean dataExecutionPreventionAvailable) {
        this.dataExecutionPreventionAvailable = dataExecutionPreventionAvailable;
    }
}

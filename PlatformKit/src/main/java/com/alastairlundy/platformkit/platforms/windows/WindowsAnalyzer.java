package com.alastairlundy.platformkit.platforms.windows;

import com.alastairlundy.platformkit.internal.exceptions.windows.WindowsVersionDetectionException;
import com.alastairlundy.platformkit.platforms.windows.enums.WindowsEdition;
import com.alastairlundy.platformkit.platforms.windows.enums.WindowsVersion;
import com.alastairlundy.platformkit.platforms.windows.models.HyperVRequirements;
import com.alastairlundy.platformkit.platforms.windows.models.WindowsSystemInformation;

import com.alastairlundy.platformkit.shared.CommandRunner;
import com.alastairlundy.platformkit.shared.PlatformAnalyzer;
import com.alastairlundy.platformkit.shared.ProcessRunner;
import com.alastairlundy.platformkit.shared.models.NetworkCard;

import com.alastairlundy.utilityjar.Version;
import com.alastairlundy.utilityjar.helpers.CharHelper;
import com.alastairlundy.utilityjar.helpers.StringHelper;

import javax.naming.OperationNotSupportedException;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class WindowsAnalyzer {

    public WindowsEdition getWindowsEdition() throws OperationNotSupportedException {
        if(PlatformAnalyzer.isWindows()){

        }
        throw new OperationNotSupportedException();
    }

    /**
     *
     * @return
     * @throws OperationNotSupportedException
     * @throws IOException
     */
    public WindowsSystemInformation getWindowsSystemInformation() throws OperationNotSupportedException, IOException {
        WindowsSystemInformation windowsSystemInformation = new WindowsSystemInformation();
        HyperVRequirements hyperVRequirements = new HyperVRequirements();

        ArrayList<String> processors = new ArrayList<>();
        ArrayList<NetworkCard> networkCards = new ArrayList<>();
        ArrayList<String> ipAddresses = new ArrayList<>();

        if (!PlatformAnalyzer.isWindows())
        {
            throw new OperationNotSupportedException();
        }

        var desc = CommandRunner.runWindowsPowershellCommand("systeminfo");
        var array = StringHelper.split(desc, CharHelper.getOsAgnosticNewLineChar().toCharArray());

        NetworkCard lastNetworkCard = null;

        boolean wasLastLineProcLine = false;
        boolean wasLastLineNetworkLine = false;

        int networkCardNumber = 0;

        for (var index = 0; index < array.length; index++) {
            var nextLine = "";

            array[index] = array[index].replace(" ", StringHelper.getEmptyString());

            if (index < (array.length - 1))
            {
                nextLine = array[index + 1].replace("  ", StringHelper.getEmptyString());
            }
            else
            {
                nextLine = array[index].replace("  ", StringHelper.getEmptyString());
            }

            if (nextLine.toLowerCase().contains("host name:"))
            {
                windowsSystemInformation.setHostName(nextLine.replace("Host Name:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("os name:"))
            {
                windowsSystemInformation.setOsName(nextLine.replace("OS Name:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("os version:") && !nextLine.toLowerCase().contains("bios"))
            {
                windowsSystemInformation.setOsVersion(nextLine.replace("OS Version:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("os manufacturer:"))
            {
                windowsSystemInformation.setOsManufacturer(nextLine.replace("OS Manufacturer:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("os configuration:"))
            {
                windowsSystemInformation.setOsConfiguration(nextLine.replace("OS Configuration:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("os build type:"))
            {
                windowsSystemInformation.setOsBuildType(nextLine.replace("OS Build Type:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("registered owner:"))
            {
                windowsSystemInformation.setRegisteredOrganization(nextLine.replace("Registered Owner:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("registered organization:"))
            {
                windowsSystemInformation.setRegisteredOrganization(nextLine.replace("Registered Organization:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("product id:"))
            {
                windowsSystemInformation.setProductId(nextLine.replace("Product ID:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("system manufacturer:"))
            {
                windowsSystemInformation.setSystemManufacturer(nextLine.replace("System Manufacturer:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("system model:"))
            {
                windowsSystemInformation.setSystemModel(nextLine.replace("System Model:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("system type:"))
            {
                windowsSystemInformation.setSystemType(nextLine.replace("System Type:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("processor(s):"))
            {
                processors.add(nextLine.replace("Processor(s):", StringHelper.getEmptyString()));

                wasLastLineProcLine = true;
                wasLastLineNetworkLine = false;
            }
            else if (nextLine.toLowerCase().contains("bios version:"))
            {
                windowsSystemInformation.setBiosVersion(nextLine.replace("BIOS Version:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("windows directory:"))
            {
                windowsSystemInformation.setWindowsDirectory(nextLine.replace("Windows Directory:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("system directory:"))
            {
                windowsSystemInformation.setSystemDirectory(nextLine.replace("System Directory:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("boot device:"))
            {
                windowsSystemInformation.setBootDevice(nextLine.replace("Boot Device:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("system locale:"))
            {
                windowsSystemInformation.setSystemLocale(nextLine.replace("System Locale:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("input locale:"))
            {
                windowsSystemInformation.setInputLocale(nextLine.replace("Input Locale:", StringHelper.getEmptyString()));
            }
            else if (nextLine.toLowerCase().contains("memory:"))
            {
                nextLine = nextLine.replace(",", StringHelper.getEmptyString()).replace(" MB", StringHelper.getEmptyString());

                if (nextLine.toLowerCase().contains("total physical memory:"))
                {
                    nextLine = nextLine.replace("Total Physical Memory:", StringHelper.getEmptyString());
                    windowsSystemInformation.setTotalPhysicalMemoryMB(Integer.parseInt(nextLine));
                }
                else if (nextLine.toLowerCase().contains("available physical memory"))
                {
                    nextLine = nextLine.replace("Available Physical Memory:", StringHelper.getEmptyString());
                    windowsSystemInformation.setAvailablePhysicalMemoryMB(Integer.parseInt(nextLine));
                }
                if (nextLine.toLowerCase().contains("virtual memory: max size:"))
                {
                    nextLine = nextLine.replace("Virtual Memory: Max Size:", StringHelper.getEmptyString());
                    windowsSystemInformation.setVirtualMemoryMaxSizeMB(Integer.parseInt(nextLine));
                }
                else if (nextLine.toLowerCase().contains("virtual memory: available:"))
                {
                    nextLine = nextLine.replace("Virtual Memory: Available:", StringHelper.getEmptyString());
                    windowsSystemInformation.setVirtualMemoryAvailableSizeMB(Integer.parseInt(nextLine));
                }
                else if (nextLine.toLowerCase().contains("virtual memory: in use:"))
                {
                    nextLine = nextLine.replace("Virtual Memory: In Use:", StringHelper.getEmptyString());
                    windowsSystemInformation.setVirtualMemoryInUseMB(Integer.parseInt(nextLine));
                }
                else if (nextLine.toLowerCase().contains("page file location(s):"))
                {
                    wasLastLineNetworkLine = false;
                    wasLastLineProcLine = false;

                    ArrayList<String> locations = new ArrayList<>();

                    locations.add(nextLine.replace("Page File Location(s):", StringHelper.getEmptyString()));

                    int locationNumber = 1;

                    while (!array[index + 1 + locationNumber].toLowerCase().contains("domain"))
                    {
                        locations.add(array[index + 1 + locationNumber]);
                        locationNumber++;
                    }

                    windowsSystemInformation.setPageFileLocations((String[]) locations.toArray());
                }
                else if (nextLine.toLowerCase().contains("domain:"))
                {
                    wasLastLineNetworkLine = false;
                    wasLastLineProcLine = false;

                    windowsSystemInformation.setDomain(nextLine.replace("Domain:", StringHelper.getEmptyString()));
                }
                else if (nextLine.toLowerCase().contains("logon server:"))
                {
                    wasLastLineNetworkLine = false;
                    wasLastLineProcLine = false;

                    windowsSystemInformation.setLogonServer(nextLine.replace("Logon Server:", StringHelper.getEmptyString()));
                }
                else if (nextLine.toLowerCase().contains("hotfix(s):"))
                {
                    wasLastLineNetworkLine = false;
                    wasLastLineProcLine = false;

                    ArrayList<String> hotfixes = new ArrayList<>();

                    int hotfixCount = 0;
                    while (array[index + 2 + hotfixCount].contains("[") && array[index + 2 + hotfixCount].contains("]:"))
                    {
                        hotfixes.add(array[index + 2 + hotfixCount].replace("  ", StringHelper.getEmptyString()));
                        hotfixCount++;
                    }

                    windowsSystemInformation.setHotfixesInstalled((String[]) hotfixes.toArray());
                }
                else if (nextLine.toLowerCase().contains("network card(s):"))
                {
                    if (networkCardNumber > 0)
                    {
                        if (lastNetworkCard != null)  networkCards.get(networkCardNumber - 1).setIpAddresses((String[]) ipAddresses.toArray());
                        ipAddresses.clear();
                    }

                    wasLastLineProcLine = false;

                    NetworkCard networkCard = new NetworkCard();
                    networkCard.setName(array[index + 2].replace("  ", StringHelper.getEmptyString()));

                    networkCards.add(networkCard);
                    lastNetworkCard = networkCard;

                    wasLastLineNetworkLine = true;
                    networkCardNumber++;
                }
                else if (nextLine.toLowerCase().contains("connection name:"))
                {
                    wasLastLineProcLine = false;

                    if (networkCards.contains(lastNetworkCard))
                    {
                        var position = getNetworkCardPositionInWindowsSysInfo(networkCards, lastNetworkCard);
                        networkCards.get(position).setConnectionName(nextLine.replace("Connection Name:", StringHelper.getEmptyString()).replace("  ", StringHelper.getEmptyString()));
                    }
                }
                else if (nextLine.toLowerCase().contains("dhcp enabled:"))
                {
                    wasLastLineProcLine = false;

                    var position = getNetworkCardPositionInWindowsSysInfo(networkCards, lastNetworkCard);
                    networkCards.get(position).setDhcpEnabled(array[index + 4].toLowerCase().contains("yes"));
                }
                else if (nextLine.toLowerCase().contains("dhcp server:"))
                {
                    var position = getNetworkCardPositionInWindowsSysInfo(networkCards, lastNetworkCard);
                    networkCards.get(position).setDhcpServer(nextLine.replace("DHCP Server:", StringHelper.getEmptyString()).replace("  ", StringHelper.getEmptyString()));
                }
                else if (nextLine.toLowerCase().contains("[") && nextLine.toLowerCase().contains("]"))
                {
                    String compare = nextLine.replace("[", StringHelper.getEmptyString()).replace("]:", StringHelper.getEmptyString());

                    int dotCounter = 0;
                    for (char c : compare.toCharArray())
                    {
                        if (c == '.' || c == ':')
                        {
                            dotCounter++;
                        }
                    }

                    if (dotCounter >= 3 && wasLastLineNetworkLine)
                    {
                        ipAddresses.add(nextLine);
                    }
                    else if (wasLastLineProcLine)
                    {
                        processors.add(nextLine);
                    }
                }

                else if (nextLine.toLowerCase().contains("hyper-v requirements:"))
                {
                    hyperVRequirements.setVmMonitorModeExtensions(nextLine.replace("Hyper-V Requirements:", StringHelper.getEmptyString())
                            .replace("VM Monitor Mode Extensions: ", StringHelper.getEmptyString()).contains("Yes"));
                }
                else if (nextLine.toLowerCase().contains("virtualization enabled in firmware:"))
                {
                    hyperVRequirements.setVirtualizationEnabledInFirmware(nextLine.replace("Virtualization Enabled In Firmware:", StringHelper.getEmptyString()).contains("Yes"));
                }
                else if (nextLine.toLowerCase().contains("second level address translation:"))
                {
                    hyperVRequirements.setSecondLevelAddressTranslation(nextLine.replace("Second Level Address Translation:", StringHelper.getEmptyString()).contains("Yes"));
                }
                else if (nextLine.toLowerCase().contains("data execution prevention available:"))
                {
                    wasLastLineNetworkLine = false;
                    wasLastLineProcLine = false;

                    hyperVRequirements.setDataExecutionPreventionAvailable(nextLine.replace("Data Execution Prevention Available:", StringHelper.getEmptyString()).contains("Yes"));
                    break;
                }
            }


            if (networkCardNumber == 1)
            {
                if (lastNetworkCard != null && ipAddresses != null && (long) networkCards.size() > 0)
                    networkCards.get(networkCardNumber - 1).setIpAddresses((String[]) ipAddresses.toArray());

                ipAddresses.clear();
            }
        }

        windowsSystemInformation.setNetworkCards((NetworkCard[]) networkCards.toArray());
        windowsSystemInformation.setProcessors((String[]) processors.toArray());
        windowsSystemInformation.setHyperVRequirements(hyperVRequirements);

        return windowsSystemInformation;
    }

    protected int getNetworkCardPositionInWindowsSysInfo(ArrayList<NetworkCard> networkCards, NetworkCard lastNetworkCard) {
        for (int position = 0; position < networkCards.size(); position++) {
            if (networkCards.get(position).equals(lastNetworkCard)) {
                return position;
            }
        }

        throw new InvalidParameterException();
    }

    /**
     * Gets the value of a registry key in the Windows registry.
     * @param query
     * @param value
     * @return
     * @throws OperationNotSupportedException
     * @throws IOException
     * @throws IllegalArgumentException Thrown if the result of the query is null;
     */
    public String getWindowsRegistryValue(String query, String value) throws OperationNotSupportedException, IOException, IllegalArgumentException {
        if(PlatformAnalyzer.isWindows()){
            String result = CommandRunner.runCmdCommand("REG QUERY " + query + " /v " + value);

            if (result != null)
            {
                return result.replace(value, StringHelper.getEmptyString())
                        .replace("REG_SZ", StringHelper.getEmptyString())
                        .replace(" ", StringHelper.getEmptyString());
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        throw new OperationNotSupportedException();
    }

    /**
     * Gets a property/value in a WMI Class from WMI.
     * @param property
     * @param wmiClass
     * @return
     * @throws OperationNotSupportedException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public String getWmiValue(String property, String wmiClass) throws OperationNotSupportedException, IOException, IllegalArgumentException {
        if (PlatformAnalyzer.isWindows()) {
            String result = CommandRunner.runWindowsPowershellCommand("Get-CimInstance -Class "
                    + wmiClass + " -Property " + property);

            var arr = StringHelper.split(result, CharHelper.getOsAgnosticNewLineChar().toCharArray());

            for (var str : arr) {
                if (str.toLowerCase().startsWith(property.toLowerCase())) {
                    return str
                            .replace(" : ", StringHelper.getEmptyString())
                            .replace(property, StringHelper.getEmptyString())
                            .replace(" ", StringHelper.getEmptyString());

                }
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     *
     * @param windowsVersion
     * @return
     * @throws OperationNotSupportedException
     * @throws WindowsVersionDetectionException
     */
    public boolean isWindows10(WindowsVersion windowsVersion) throws OperationNotSupportedException, WindowsVersionDetectionException {
        switch (windowsVersion){
            case Win10_v1507, Win10_v1511, Win10_v1607, Win10_Server2016, Win10_v1703, Win10_v1709, Win10_Server_v1709, Win10_v1803, Win10_v1809, Win10_Server2019, Win10_v1903, Win10_v1909, Win10_v2004, Win10_20H2, Win10_21H1, Win10_21H2, Win10_22H2, Win10_Server2022, Win10_InsiderPreview -> {
                return true;
            }
            case NotDetected -> throw new WindowsVersionDetectionException();
            default -> {
                if(PlatformAnalyzer.isWindows()){
                    return false;
                }
                else{
                    throw new OperationNotSupportedException();
                }
            }
        }
    }

    /**
     *
     * @return Checks whether the detected version of Windows is Windows 10.
     * @throws IOException
     * @throws WindowsVersionDetectionException
     * @throws OperationNotSupportedException
     */
    public boolean isWindows10() throws IOException, WindowsVersionDetectionException, OperationNotSupportedException {
        return isWindows10(getWindowsVersionToEnum(getWindowsVersion()));
    }

    /**
     *
     * @return Checks whether the detected version of Windows is Windows 11.
     * @throws IOException
     * @throws WindowsVersionDetectionException
     * @throws OperationNotSupportedException
     */
    public boolean isWindows11() throws IOException, WindowsVersionDetectionException, OperationNotSupportedException {
        return isWindows11(getWindowsVersionToEnum(getWindowsVersion()));
    }

    /**
     *
     * @param windowsVersion
     * @return
     * @throws WindowsVersionDetectionException
     * @throws OperationNotSupportedException
     */
    public boolean isWindows11(WindowsVersion windowsVersion) throws WindowsVersionDetectionException, OperationNotSupportedException {
        switch (windowsVersion){
            case Win11_21H1, Win11_22H2, Win11_23H2, Win11_InsiderPreview -> {
                return true;
            }
            case NotDetected -> throw new WindowsVersionDetectionException();
            default -> {
                if(PlatformAnalyzer.isWindows()){
                    return false;
                }
                else{
                    throw new OperationNotSupportedException();
                }
            }
        }
    }

    /**
     * Converts the specified version input to an enum corresponding to a Windows version.
     * @param input
     * @return
     */
    public WindowsVersion getWindowsVersionToEnum(Version input) {
        switch (input.getBuild()){
            case 10240 -> {
                return WindowsVersion.Win10_v1507;
            }
            case 10586 -> {
                return WindowsVersion.Win10_v1511;
            }
            case 14393 -> {
                return WindowsVersion.Win10_v1607;
            }
            case 15063 -> {
                return WindowsVersion.Win10_v1703;
            }
            case 16299 -> {
                return WindowsVersion.Win10_v1709;
            }
            case 17134 -> {
                return WindowsVersion.Win10_v1803;
            }
            case 17763 -> {
                return WindowsVersion.Win10_v1809;
            }
            case 18362 -> {
                return WindowsVersion.Win10_v1903;
            }
            case 18363 -> {
                return WindowsVersion.Win10_v1909;
            }
            case 19041 -> {
                return WindowsVersion.Win10_v2004;
            }
            case 19042 -> {
                return WindowsVersion.Win10_20H2;
            }
            case 19043 -> {
                return WindowsVersion.Win10_21H1;
            }
            case 19044 -> {
                return WindowsVersion.Win10_21H2;
            }
            case 19045 -> {
                return WindowsVersion.Win10_22H2;
            }
            case 20348 -> {
                return WindowsVersion.Win10_Server2022;
            }
            case 22000 -> {
                return WindowsVersion.Win11_21H1;
            }
            case 22621 -> {
                return WindowsVersion.Win11_22H2;
            }
            case 22631 -> {
                return WindowsVersion.Win11_23H2;
            }

            default -> {
                if(input.getBuild() > 10240 && input.getBuild() < 22000){
                    return WindowsVersion.Win10_InsiderPreview;
                }
                else if(input.getBuild() > 22631){
                    return WindowsVersion.Win11_InsiderPreview;
                }
                else if(input.getBuild() < 9601){
                    return WindowsVersion.NotSupported;
                }
                else{
                    return WindowsVersion.NotDetected;
                }
            }
        }
    }

    public Version getWindowsVersionFromEnum(WindowsVersion windowsVersion) throws WindowsVersionDetectionException {
        Version version;

        switch (windowsVersion) {
            case Win10_v1507 -> version = new Version(10, 0, 10240);
            case Win10_v1511 -> version = new Version(10, 0, 10586);
            case Win10_v1607, Win10_Server2016 -> version = new Version(10, 0, 14393);
            case Win10_v1703 -> version = new Version(10,0,15063);
            case Win10_v1709, Win10_Server_v1709 -> version = new Version(10,0,16299);
            case Win10_v1803 -> version = new Version(10,0,17134);
            case Win10_v1809, Win10_Server2019 -> version = new Version(10,0,17763);
            case Win10_v1903 -> version = new Version(10,0,18362);
            case Win10_v1909 -> version = new Version(10,0,18363);
            case Win10_v2004 -> version = new Version(10,0,19041);
            case Win10_20H2 -> version = new Version(10,0,19042);
            case Win10_21H1 -> version = new Version(10,0,19043);
            case Win10_21H2 -> version = new Version(10,0,19044);
            case Win10_22H2 -> version = new Version(10,0,19045);
            case Win10_Server2022 -> version = new Version(10,0,20348);
            case Win11_21H1 -> version = new Version(10,0,22000);
            case Win11_22H2 -> version = new Version(10,0,22621);
            case Win11_23H2 -> version = new Version(10,0,22631);
            default -> throw new WindowsVersionDetectionException();
        }

        return version;
    }

    /**
     * Detects Windows Version and returns it as a System.Version
     * @return
     * @throws IOException
     */
    public Version getWindowsVersion() throws IOException {
       String result = ProcessRunner.runProcessOnWindows("ver", new String[]{""}, false);

        result = result.replace("Microsoft Windows", "");
        result = result.replace("Version", "");

        result = StringHelper.remove(result, new char[]{'[', ']' });

        return Version.parse(result);
    }
}

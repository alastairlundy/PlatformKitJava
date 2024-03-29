package com.alastairlundy.platformkit.platforms.mac;

import com.alastairlundy.platformkit.internal.exceptions.mac.MacOsVersionDetectionException;
import com.alastairlundy.platformkit.platforms.mac.enums.MacOsVersion;

import com.alastairlundy.platformkit.platforms.mac.enums.MacProcessorType;
import com.alastairlundy.platformkit.platforms.mac.enums.MacSystemProfilerDataType;
import com.alastairlundy.platformkit.platforms.mac.models.MacOsSystemInformation;
import com.alastairlundy.platformkit.shared.CommandRunner;
import com.alastairlundy.platformkit.shared.PlatformAnalyzer;
import com.alastairlundy.utilityjar.Version;
import com.alastairlundy.utilityjar.helpers.CharHelper;
import com.alastairlundy.utilityjar.helpers.StringHelper;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;

public class MacOsAnalyzer {

    /**
     * Converts a macOS version to a macOS version enum.
     * @param input
     * @return
     * @throws MacOsVersionDetectionException
     * @throws OperationNotSupportedException Thrown if run on a platform that isn't macOS.
     */
    public MacOsVersion getMacOsVersionToEnum(Version input) throws MacOsVersionDetectionException, OperationNotSupportedException {
        if (PlatformAnalyzer.isMac())
        {
            if (input.getMajor() == 10)
            {
                switch (input.getMinor()) {
                    case 0:
                        //return MacOsVersion.v10_0_Cheetah;
                        return MacOsVersion.NotSupported;
                    case 1:
                        // return MacOsVersion.v10_1_Puma;
                        return MacOsVersion.NotSupported;
                    case 2:
                        // return MacOsVersion.v10_2_Jaguar;
                        return MacOsVersion.NotSupported;
                    case 3:
                        // return MacOsVersion.v10_3_Panther;
                        return MacOsVersion.NotSupported;
                    case 4:
                        // return MacOsVersion.v10_4_Tiger;
                        return MacOsVersion.NotSupported;
                    case 5:
                        // return MacOsVersion.v10_5_Leopard;
                        return MacOsVersion.NotSupported;
                    case 6:
                        //return MacOsVersion.v10_6_SnowLeopard;
                        return MacOsVersion.NotSupported;
                    case 7:
                        //return MacOsVersion.v10_7_Lion;
                        return MacOsVersion.NotSupported;
                    case 8:
                        //return MacOsVersion.v10_8_MountainLion;
                        return MacOsVersion.NotSupported;
                    case 9:
                        return MacOsVersion.NotSupported;
                    case 10:
                        return MacOsVersion.NotSupported;
                    case 11:
                        return MacOsVersion.NotSupported;
                    case 12:
                        return MacOsVersion.v10_12_Sierra;
                    case 13:
                        return MacOsVersion.v10_13_HighSierra;
                    case 14:
                        return MacOsVersion.v10_14_Mojave;
                    case 15:
                        return MacOsVersion.v10_15_Catalina;
                    //This is for compatibility reasons.
                    case 16:
                        return MacOsVersion.v11_BigSur;
                }
            }

            if (input.getMajor() == 11) return MacOsVersion.v11_BigSur;
            if (input.getMajor() == 12) return MacOsVersion.v12_Monterey;
            if (input.getMajor() == 13) return MacOsVersion.v13_Ventura;
            if (input.getMajor() == 14) return MacOsVersion.v14_Sonoma;

            throw new MacOsVersionDetectionException();
        }
        else
        {
            throw new OperationNotSupportedException();
        }
    }

    /**
     * Converts a macOS version enum to a macOS version as a Version object.
     * @param macOsVersion
     * @return
     * @throws OperationNotSupportedException
     * @throws MacOsVersionDetectionException
     * @throws IllegalArgumentException
     */
    public Version getMacOsVersionFromEnum(MacOsVersion macOsVersion) throws OperationNotSupportedException, MacOsVersionDetectionException, IllegalArgumentException {
        switch (macOsVersion)
        {
            case v10_12_Sierra:
                return new Version(10, 12);
            case v10_13_HighSierra:
                return new Version(10, 13);
            case v10_14_Mojave:
                return new Version(10, 14);
            case v10_15_Catalina:
                return new Version(10, 15);
            case v11_BigSur:
                return new Version(11, 0);
            case v12_Monterey:
                return new Version(12, 0);
            case v13_Ventura:
                return new Version(13, 0);
            case v14_Sonoma:
                return new Version(14, 0);
            case NotSupported:
                throw new OperationNotSupportedException();
            case NotDetected:
                throw new MacOsVersionDetectionException();
            default:
                throw new IllegalArgumentException();
        }
    }


    /**
     * 
     * @param macOsVersion
     * @return
     * @throws OperationNotSupportedException
     * @throws MacOsVersionDetectionException
     * @throws IOException
     */
    public boolean isAtLeastVersion(MacOsVersion macOsVersion) throws OperationNotSupportedException, MacOsVersionDetectionException, IOException {
        return isAtLeastVersion(getMacOsVersionFromEnum(macOsVersion));
    }

    /**
     * Checks to see whether the specified version of macOS is the same or newer than the installed version of macOS.
     * @param macOsVersion
     * @return
     * @throws OperationNotSupportedException Thrown if run on a platform that isn't macOS.
     * @throws MacOsVersionDetectionException
     * @throws IOException
     */
    public boolean isAtLeastVersion(Version macOsVersion) throws OperationNotSupportedException, MacOsVersionDetectionException, IOException {
       return getMacOsVersion().isAtLeastVersion(macOsVersion);
    }

    public MacOsSystemInformation getMacSystemInformation() throws IOException, OperationNotSupportedException {
        MacOsSystemInformation macOsSystemInformation = new MacOsSystemInformation();

        macOsSystemInformation.setMacOsBuildNumber(getMacOsBuildNumber());
        macOsSystemInformation.setMacOsVersion(getMacOsVersion());
        macOsSystemInformation.setProcessorType(getMacProcessorType());
        macOsSystemInformation.setDarwinVersion(getDarwinVersion());

        //TODO: FINISH

        return macOsSystemInformation;
    }

    /**
     *
     * @return
     */
    public boolean isAppleSiliconMac(){
        return getMacProcessorType() == MacProcessorType.AppleSilicon;
    }

    /**
     *
     * @return
     */
    public MacProcessorType getMacProcessorType() {
        if(PlatformAnalyzer.isMac()){
            String arch = System.getProperty("os.arch");

            if(arch.toLowerCase().contains("arm") || arch.toLowerCase().contains("aarch")){
                return MacProcessorType.AppleSilicon;
            }
            if(arch.toLowerCase().contains("x86") || arch.toLowerCase().contains("x64")){
                return MacProcessorType.Intel;
            }

            return MacProcessorType.NotDetected;
        }

        return MacProcessorType.NotSupported;
    }

//    public Version getXnuVersion(){
//
//    }


    /**
     * Detects the macOS version and returns it as a System.Version object.
     * @return
     * @throws IOException
     * @throws OperationNotSupportedException
     */
    public Version getMacOsVersion() throws IOException, OperationNotSupportedException {
        if(PlatformAnalyzer.isMac()){
            var version = getMacSwVersInfo()[1].replace("ProductVersion:", StringHelper.getEmptyString()).replace(" ", StringHelper.getEmptyString());

            return Version.parse(version);
        }
        throw new OperationNotSupportedException();
    }

    /**
     *
     * @return
     * @throws OperationNotSupportedException
     */
    public Version getDarwinVersion() throws OperationNotSupportedException, IOException {
        if(PlatformAnalyzer.isMac()){
            return Version.parse(getMacSystemProfilerInfo(MacSystemProfilerDataType.SoftwareDataType, "Kernel Version: Darwin"));
        }
        throw new OperationNotSupportedException();
    }

    /**
     *
     * @param macSystemProfilerDataType
     * @param key
     * @return
     * @throws OperationNotSupportedException
     */
    public String getMacSystemProfilerInfo(MacSystemProfilerDataType macSystemProfilerDataType, String key) throws OperationNotSupportedException, IOException {
        if(PlatformAnalyzer.isMac()){
            String info = CommandRunner.runCommandOnMac("system_profiler", new String[]{"SP", macSystemProfilerDataType.toString()});

            String[] array = StringHelper.split(info, CharHelper.getOsAgnosticNewLineChar());

            for(String str : array){
                if(str.toLowerCase().contains(key.toLowerCase())){
                    return str.replace(key, StringHelper.getEmptyString()).replace(":", StringHelper.getEmptyString());
                }
            }

            throw new IllegalArgumentException();
        }

        throw new OperationNotSupportedException();
    }

    /**
     *  Detects the Build Number of the installed version of macOS.
     * @return
     * @throws IOException
     * @throws OperationNotSupportedException
     */
    public String getMacOsBuildNumber() throws IOException, OperationNotSupportedException {
        if(PlatformAnalyzer.isMac()){
            return getMacSwVersInfo()[2].toLowerCase().replace("BuildVersion:", StringHelper.getEmptyString().replace(" ", StringHelper.getEmptyString()));
        }
        throw new OperationNotSupportedException();
    }

    /**
     * @return
     * @throws IOException
     */
    protected String[] getMacSwVersInfo() throws IOException {
        return StringHelper.split(CommandRunner.runCommandOnMac("sw_vers", new String[]{""}), CharHelper.getOsAgnosticNewLineChar());
    }
}
